package com.springboot.pizzaexpress.controller;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.bean.Cart;
import com.springboot.pizzaexpress.bean.Shop;
import com.springboot.pizzaexpress.bean.User;
import com.springboot.pizzaexpress.model.CartModel;
import com.springboot.pizzaexpress.model.ItemWrapModel;
import com.springboot.pizzaexpress.model.ResponseModel;
import com.springboot.pizzaexpress.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Cart")
@Api("购物车api")
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/addToCart",method = RequestMethod.POST)
    public ResponseModel addToCart (@RequestBody CartModel cartModel,HttpSession session){
        ResponseModel responseModel = new ResponseModel();
        User u = (User)session.getAttribute("userInfo");
        if(u==null){
            responseModel.setStatus("500");
            responseModel.setMessage("用户未登录！");

        }
        else{
            int userId = u.getUserId();
            int shopId = cartModel.getShop().getShopId();
            List<ItemWrapModel> itemsList = cartModel.getItems();
            JSONArray array = JSONArray.fromObject(itemsList);
            String items = array.toString();
            if(cartService.findCartItems(shopId,userId)!=null){
                cartService.modifyCart(userId,shopId,items);
            }
            else {
                cartService.insertToCart(userId, shopId, items);

            }
            responseModel.setStatus("200");
            responseModel.setMessage("添加成功！");
            responseModel.setModel(cartModel);
        }
        return responseModel;
    }

    @RequestMapping(value = "/showCart",method = RequestMethod.GET)
    public ResponseModel showCart(@RequestParam("shopId") int shopId, HttpSession session){
        ResponseModel responseModel = new ResponseModel();
        User u = (User) session.getAttribute("userInfo");
        List<ItemWrapModel> list = new ArrayList<>();
        ItemWrapModel itemWrapModel;
        if(u==null){
            responseModel.setStatus("500");
            responseModel.setMessage("用户未登录！");

        }
        else {
            int userId = u.getUserId();
            Cart cart = cartService.findCartItems(shopId,userId);
            String items = cart.getItems();
            JSONArray array = JSONArray.fromObject(items);
            for(Object o:array) {
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
        return responseModel;
    }
}
