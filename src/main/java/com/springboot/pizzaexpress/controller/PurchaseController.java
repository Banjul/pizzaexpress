package com.springboot.pizzaexpress.controller;

/**
 * Created by sts on 2019/3/10.
 */

import com.springboot.pizzaexpress.bean.Purchase;
import com.springboot.pizzaexpress.dao.PurchaseDao;
import com.springboot.pizzaexpress.dao.ShopDao;
import com.springboot.pizzaexpress.service.PurchaseService;
import com.springboot.pizzaexpress.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.persistence.criteria.Order;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value ="/purchase")
@Api("进货api")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private PurchaseDao purchaseDao;

    @Autowired
    private ShopService shopService;

//    @ApiOperation(value="查询最新20条订单")
//    @ApiImplicitParam(name = "params", value = "包含formula名称,shopId的json", dataType = "JSON")
//    @RequestMapping(value = "/getpurchasebyformula",method = RequestMethod.POST)
//    public String getLastTwentyOrders(@RequestBody Map<String, Object> params) {
//        String shopid = params.get("shopID").toString();
//        int shopId = Integer.parseInt(shopid);
//        String formula = params.get("Formula").toString();
//        return purchaseService.getPurchaseByFormula(shopId,formula);
//    }

    @ApiOperation(value="购买原料")
    @ApiImplicitParam(name = "params", value = "", dataType = "JSON")
    @RequestMapping(value = "/addpurchaseformula",method = RequestMethod.POST)
    public void addPurchaseFormula(@RequestBody Map<String, Object> params) {
        String shopid = params.get("shopID").toString();
        int shopId = Integer.parseInt(shopid);
        String formulaName = params.get("formulaName").toString();
        String purchaseTime = params.get("purchaseTime").toString();
        String purchaseCountString = params.get("purchaseCount").toString();
        int purchaseCount = Integer.parseInt(purchaseCountString);
        String purchaseManufacture  = params.get("purchaseManufacture").toString();

        int result = purchaseDao.insertPurchase(shopId,formulaName,purchaseTime,purchaseCount,purchaseManufacture);
        if (result == 1)
            shopService.updateFormulaCount(shopId,formulaName,purchaseCount);
    }

    @ApiOperation(value="原料追溯")
    @ApiImplicitParam(name = "params", value = "", dataType = "JSON")
    @RequestMapping(value = "/getpurchasebyformula",method = RequestMethod.POST)
    public String getPurchaseByFormula(@RequestBody Map<String, Object> params) {
        String shopid = params.get("shopID").toString();
        int shopId = Integer.parseInt(shopid);
        String formulaName = params.get("formulaName").toString();

        return purchaseService.getPurchaseByFormula(shopId,formulaName);
    }


}
