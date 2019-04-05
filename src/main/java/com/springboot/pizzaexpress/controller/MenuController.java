package com.springboot.pizzaexpress.controller;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.bean.Menu;
import com.springboot.pizzaexpress.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping(value ="/menu")
@Api("菜单管理api")
public class MenuController {

    @Autowired
    private MenuService menuService;



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
}
