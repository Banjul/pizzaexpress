package com.springboot.pizzaexpress.controller;

/**
 * Created by sts on 2019/3/2.
 */

import com.springboot.pizzaexpress.bean.Item;
import com.springboot.pizzaexpress.bean.Menu;
import com.springboot.pizzaexpress.bean.Shop;
import com.springboot.pizzaexpress.model.ItemBean;
import com.springboot.pizzaexpress.model.ItemWrapModel;
import com.springboot.pizzaexpress.model.MenuModel;
import com.springboot.pizzaexpress.model.ResponseModel;
import com.springboot.pizzaexpress.service.ItemService;
import com.springboot.pizzaexpress.service.MenuService;
import com.springboot.pizzaexpress.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/menu")
@Api("菜单api")
public class MenuController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ShopService shopService;


    @RequestMapping(value = "/showMenu",method = RequestMethod.POST)
    public ResponseModel showMenu(@RequestBody MenuModel menuModel){
        ResponseModel responseModel = new ResponseModel();
        //获取shop_id
        int shopId = menuModel.getShop().getShopId();
        //查找菜单表记录
        Menu menu = menuService.findByShopId(shopId);

        //items属性
        String items = menu.getItems();
        //转为jsonArray
        JSONArray jsonArray = JSONArray.fromObject(items);
        System.err.println(jsonArray);
        List<ItemWrapModel> list = new ArrayList<>();
        ItemWrapModel itemWrapModel = new ItemWrapModel();
        ItemBean itemBean;

        for(Object o :jsonArray){
            System.err.println(o);
            JSONObject obj = JSONObject.fromObject(o);
            itemBean = (ItemBean) JSONObject.toBean(obj,ItemBean.class);
            //获取itemID
            int itemId = itemBean.getItemId();
            System.err.println(itemId);
            int count = itemBean.getCount();
            System.err.println(count);
            //查找item具体信息
            Item i = itemService.findByItemId(itemId);
            itemWrapModel.setItem(i);
            itemWrapModel.setCount(count);

            list.add(itemWrapModel);
        }
        menuModel.setItems(list);
        menuModel.setMenuId(menu.getMenu_id());

        responseModel.setStatus("200");
        responseModel.setMessage("success");
        responseModel.setModel(menuModel);

        return responseModel;
    }
}
