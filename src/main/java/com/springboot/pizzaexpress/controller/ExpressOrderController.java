package com.springboot.pizzaexpress.controller;

/**
 * Created by sts on 2019/3/2.
 */

import com.springboot.pizzaexpress.service.ExpressOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@RestController
@RequestMapping(value ="/expressorder")
@Api("配送单api")
public class ExpressOrderController {

    @Autowired
    private ExpressOrderService expressOrderService;

    @ApiOperation(value="获取当前配送中订单列表信息")
    @ApiImplicitParam(name = "params", value = "", dataType = "JSON")
    @RequestMapping(value = "/getexpresscontent",method = RequestMethod.POST)
    public String getExpressContent(@RequestBody Map<String, Object> params) {
        String deliver = params.get("deliverId").toString();
        int deliverId = Integer.parseInt(deliver);

        return expressOrderService.getExpressContent(deliverId);

    }

}
