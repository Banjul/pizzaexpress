package com.springboot.pizzaexpress.controller;

/**
 * Created by sts on 2019/3/2.
 */

import com.springboot.pizzaexpress.bean.Item;
import com.springboot.pizzaexpress.bean.Menu;
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
@RequestMapping(value ="/menu")
@Api("菜单管理api")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ShopService shopService;



    @ApiOperation(value = "查看商店成品库存", notes = "需要，开始时间，结束时间")
    @ApiImplicitParam(name = "params", value = "包含开始时间，结束时间,shopId,deliverID,order_id的json", dataType = "JSON")
    @RequestMapping(value = "/getmenubyshopid", method = RequestMethod.POST)
    public String getMenuByShopId(@RequestBody Map<String, Object> params) {
        String shopid = params.get("shopID").toString();
        int shopId = Integer.parseInt(shopid);
        return menuService.getMenuByShopId(shopId);
    }

    @ApiOperation(value = "更改商店成品库存", notes = "需要，开始时间，结束时间")
    @ApiImplicitParam(name = "params", value = "包含开始时间，结束时间,shopId,deliverID,order_id的json", dataType = "JSON")
    @RequestMapping(value = "/updatemenubyshopid", method = RequestMethod.POST)
    public String updateMenuByShopId (@RequestBody Map<String, Object> params) {
        String shopid = params.get("shopID").toString();
        int shopId = Integer.parseInt(shopid);
        String itemid = params.get("itemID").toString();
        int itemId = Integer.parseInt(itemid);
        String itemcount = params.get("count").toString();
        int itemCount = Integer.parseInt(itemcount);

        return menuService.updateMenuByShopId(shopId,itemId,itemCount);
    }


    @ApiOperation(value = "根据名字搜索成品", notes = "需要，商店id，披萨名字")
    @ApiImplicitParam(name = "params", value = "包含shopId,itemName", dataType = "JSON")
    @RequestMapping(value = "/getmenubyitemname", method = RequestMethod.POST)
    public String getMenuByItemName(@RequestBody Map<String, Object> params) {
        String shopid = params.get("shopID").toString();
        int shopId = Integer.parseInt(shopid);
        String itemName = params.get("pizzaName").toString();
        return menuService.getMenuByItemName(shopId, itemName);

    }


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
        ItemBean itemBean;

        for(Object o :jsonArray){
            ItemWrapModel itemWrapModel = new ItemWrapModel();
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
        menuModel.setMenuId(menu.getMenuId());

        responseModel.setStatus("200");
        responseModel.setMessage("success");
        responseModel.setModel(menuModel);

        return responseModel;
    }

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public ResponseModel showMenu(@RequestBody Map<String,Object> params){
        ResponseModel responseModel = new ResponseModel();
        String keywords = params.get("keywords").toString();
        int shopId = (int)params.get("shopId");
        Menu menu = menuService.findByShopId(shopId);
        String items = menu.getItems();
        JSONArray jsonArray = JSONArray.fromObject(items);
        System.err.println(jsonArray);
        List<ItemWrapModel> list = new ArrayList<>();
        ItemBean itemBean;

        for(Object o :jsonArray){
            ItemWrapModel itemWrapModel = new ItemWrapModel();
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
            if(i.getItemName().contains(keywords)) {
                itemWrapModel.setItem(i);
                itemWrapModel.setCount(count);

                list.add(itemWrapModel);
            }
        }
        if(list.size()==0) {
            responseModel.setStatus("500");
            responseModel.setMessage("未查找到相关内容！");
        }else {
            responseModel.setStatus("200");
            responseModel.setMessage("查找成功！");
            responseModel.setModel(list);
        }
        return responseModel;
    }
}
