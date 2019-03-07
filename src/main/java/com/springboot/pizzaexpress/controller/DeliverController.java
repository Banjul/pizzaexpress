package com.springboot.pizzaexpress.controller;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.bean.Deliver;
import com.springboot.pizzaexpress.service.DeliverService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value ="/deliver")
@Api("配送员api")
public class DeliverController {
    @Autowired
    private  DeliverService deliverService;

    @ApiOperation(value="查询工厂所有配送员")
    @ApiImplicitParam(name = "params", value = "包含shopid的json", dataType = "JSON")
    @RequestMapping(value = "/getalldeliver",method = RequestMethod.POST)
    public String getLastTwentyOrders(@RequestBody Map<String, Object> params){
        String shopID = params.get("shopID").toString();
        int shopId = Integer.parseInt(shopID);
        return deliverService.getAllDeliversByShop(shopId);
    }

    @ApiOperation(value = "根据时间查询订单", notes = "需要，开始时间，结束时间")
    @ApiImplicitParam(name = "params", value = "包含，deliverName,shopId,deliverID的Json", dataType = "JSON")
    @RequestMapping(value = "/getdeliverbyselect", method = RequestMethod.POST)
    public String getOrdersByDeliver( @RequestBody Map<String, Object> params){
        String shopid = params.get("shopID").toString();
        int shopId = Integer.parseInt(shopid);
        String deliverId = params.get("deliverID").toString();
        String deliverName=params.get("deliverName").toString();
        System.err.println(deliverId);
        if (deliverId.equals("-1") )
            return deliverService.queryDeliverByName(deliverName,shopId);
        else{
            int deliverID = Integer.parseInt(deliverId);
            return deliverService.queryDeliverById(deliverID,shopId);
        }



    }

}
