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
@RequestMapping("/order")
@Api("订单api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value="查询最新20条订单")
    @RequestMapping(value = "/getlasttwentyorders",method = RequestMethod.GET)
    public List getLastTwentyOrders(HttpSession session){
        Shop shop=(Shop) session.getAttribute("shopAdmin");
        int shopId = shop.getShop_id();
        return orderService.getLastTwentyOrders(shopId);
    }

    @ApiOperation(value = "根据时间查询订单", notes = "需要，开始时间，结束时间")
    @ApiImplicitParam(name = "params", value = "包含开始时间，结束时间的json", dataType = "JSON")
    @RequestMapping(value = "/queryorderbytime", method = RequestMethod.POST)
    public List getOrdersByDeliver(HttpSession session, @RequestBody Map<String, Object> params){
        Shop shop=(Shop) session.getAttribute("shopAdmin");
        int shopId = shop.getShop_id();
        String start_time = params.get("requestStartTime").toString();
        String end_time = params.get("requestEndTime").toString();
        return orderService.queryOrderByTimeAndShop(start_time,end_time,shopId);
    }

}
