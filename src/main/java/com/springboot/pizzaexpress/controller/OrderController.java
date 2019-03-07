package com.springboot.pizzaexpress.controller;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.bean.Shop;
import com.springboot.pizzaexpress.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value ="/order")
@Api("订单api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value="查询最新20条订单")
    @ApiImplicitParam(name = "params", value = "包含开始时间，结束时间,shopId,deliverID,order_id的json", dataType = "JSON")
    @RequestMapping(value = "/getlasttwentyorders",method = RequestMethod.POST)
    public String getLastTwentyOrders(@RequestBody Map<String, Object> params){
        String shopID = params.get("shopID").toString();
        int shopId = Integer.parseInt(shopID);
        return orderService.getLastTwentyOrders(shopId);
    }

    @ApiOperation(value = "根据时间查询订单", notes = "需要，开始时间，结束时间")
    @ApiImplicitParam(name = "params", value = "包含开始时间，结束时间,shopId,deliverID,order_id的json", dataType = "JSON")
    @RequestMapping(value = "/getorderbyselect", method = RequestMethod.POST)
    public String getOrdersByDeliver( @RequestBody Map<String, Object> params){
        String shopid = params.get("shopID").toString();
        int shopId = Integer.parseInt(shopid);
        String deliverId = params.get("deliverid").toString();
        String orderId=params.get("orderid").toString();
        String start_time = params.get("startTime").toString();
        String end_time = params.get("endTime").toString();
        System.err.println(deliverId);
        if (deliverId.equals("-1") ){
            if(orderId.equals("-1")){
                return orderService.queryOrderByTimeAndShop(start_time,end_time,shopId);
            }
            int orderID = Integer.parseInt(orderId);
            System.err.println(orderId);
            if (start_time == null) {
                return orderService.queryOrderByOrderId(orderID,shopId);
            }
            System.err.println(orderId);
            System.err.println(start_time);
            return orderService.queryOrderByOrderIdAndTime(orderID,shopId,start_time,end_time);
        }
        else{
            int deliverID = Integer.parseInt(deliverId);
            if (start_time==null) {
                return orderService.getOrderByDeliver(shopId,deliverID);
            }
            else
                return orderService.queryOrderByDeliverAndTime(deliverID,shopId,start_time,end_time);
        }

    }


//    public Map<String,Object>

}
