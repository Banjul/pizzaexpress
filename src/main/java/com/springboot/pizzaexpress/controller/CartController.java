package com.springboot.pizzaexpress.controller;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.bean.Cart;
import com.springboot.pizzaexpress.bean.Item;
import com.springboot.pizzaexpress.bean.Shop;
import com.springboot.pizzaexpress.bean.User;
import com.springboot.pizzaexpress.model.CartModel;
import com.springboot.pizzaexpress.model.ItemWrapModel;
import com.springboot.pizzaexpress.model.ResponseModel;
import com.springboot.pizzaexpress.model.ShopModel;
import com.springboot.pizzaexpress.service.CartService;
import com.springboot.pizzaexpress.service.ItemService;
import com.springboot.pizzaexpress.service.ShopService;
import io.swagger.annotations.Api;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value ="/cart")
@Api("购物车api")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ShopService shopService;

    @RequestMapping(value = "/addToCart", method = RequestMethod.POST)
    public ResponseModel addToCart(@RequestBody CartModel cartModel, HttpSession session) {
        ResponseModel responseModel = new ResponseModel();
        User u = (User) session.getAttribute("userInfo");
        if (u == null) {
            responseModel.setStatus("500");
            responseModel.setMessage("用户未登录!");

        } else {
            int userId = u.getUserId();
            int shopId = cartModel.getShop().getShopId();
            List<ItemWrapModel> itemsList = new ArrayList<>();
            for (ItemWrapModel i : cartModel.getItems()) {
                Item item = i.getItem();
                int itemId = item.getItemId();
                item = itemService.findByItemId(itemId);
                i.setItem(item);
                itemsList.add(i);
            }
            JSONArray array = JSONArray.fromObject(itemsList);
            String items = array.toString();
            if (cartService.findCartItems(shopId, userId) != null) {
                cartService.modifyCart(userId, shopId, items);
            } else {
                cartService.insertToCart(userId, shopId, items);

            }
            responseModel.setStatus("200");
            responseModel.setMessage("添加成功！");

            responseModel.setModel(cartModel);
        }
        return responseModel;
    }

    @RequestMapping(value = "/showCart", method = RequestMethod.GET)
    public ResponseModel showCart(@RequestParam("shopId") int shopId, HttpSession session) {
        ResponseModel responseModel = new ResponseModel();
        User u = (User) session.getAttribute("userInfo");
        List<ItemWrapModel> list = new ArrayList<>();
        ItemWrapModel itemWrapModel;
        if (u == null) {
            responseModel.setStatus("500");
            responseModel.setMessage("用户未登录!");

        } else {
            int userId = u.getUserId();
            Cart cart = cartService.findCartItems(shopId, userId);
            if(cart==null){
                responseModel.setStatus("500");
                responseModel.setMessage("购物车为空！");

            }else {
                String items = cart.getItems();
                JSONArray array = JSONArray.fromObject(items);
                for (Object o : array) {
                    JSONObject obj = JSONObject.fromObject(o);
                    itemWrapModel = (ItemWrapModel) JSONObject.toBean(obj, ItemWrapModel.class);
                    list.add(itemWrapModel);
                }
                responseModel.setStatus("200");
                responseModel.setMessage("查询成功！");
                CartModel cartModel = new CartModel();
                cartModel.setCartId(cart.getCartId());
                cartModel.setItems(list);
                responseModel.setModel(cartModel);
            }
        }
        return responseModel;
    }


    @RequestMapping(value = "/clearCart",method = RequestMethod.POST)
    public ResponseModel clearCart(@RequestBody Map<String,Object> param, HttpSession session){
        ResponseModel responseModel = new ResponseModel();
        int shopId = (int)param.get("shopId");
        User u = (User) session.getAttribute("userInfo");
        List<ItemWrapModel> list = new ArrayList<>();
        ItemWrapModel itemWrapModel;
        if(u==null){
            responseModel.setStatus("500");
            responseModel.setMessage("用户未登录!");

        }
        else {
            int userId = u.getUserId();
            cartService.clearCart(userId,shopId);
            responseModel.setStatus("200");
            responseModel.setMessage("清空购物车成功！");
        }
        return responseModel;
    }


    @RequestMapping(value = "/showAllCart", method = RequestMethod.GET)
    public ResponseModel showAllCart(HttpSession session) {
        ResponseModel responseModel = new ResponseModel();
        User u = (User) session.getAttribute("userInfo");
        ItemWrapModel itemWrapModel;
        if (u == null) {
            responseModel.setStatus("500");
            responseModel.setMessage("用户未登录!");

        } else {
            int userId = u.getUserId();
            List<Cart> cartList = cartService.findAllCart(userId);
            List<CartModel> list1 = new ArrayList<>();
            for(Cart cart:cartList) {
                String items = cart.getItems();
                JSONArray array = JSONArray.fromObject(items);
                List<ItemWrapModel> list = new ArrayList<>();
                for (Object o : array) {
                    JSONObject obj = JSONObject.fromObject(o);
                    itemWrapModel = (ItemWrapModel) JSONObject.toBean(obj, ItemWrapModel.class);
                    list.add(itemWrapModel);
                }
                CartModel cartModel = new CartModel();
                cartModel.setItems(list);
                cartModel.setCartId(cart.getCartId());
                ShopModel shopModel = new ShopModel();
                Shop shop =shopService.findByShopId(cart.getShopId());
                shopModel.setShopName(shop.getShopName());
                shopModel.setPicUrl(shop.getPicUrl());
                shopModel.setShopId(cart.getShopId());
                shopModel.setPosString(shop.getPosString());
                cartModel.setShop(shopModel);
                list1.add(cartModel);
            }
            responseModel.setStatus("200");
            responseModel.setMessage("查询成功！");
            responseModel.setModel(list1);
        }
        return responseModel;
    }


}
