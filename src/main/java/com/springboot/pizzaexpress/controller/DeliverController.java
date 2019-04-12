package com.springboot.pizzaexpress.controller;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.bean.Deliver;
import com.springboot.pizzaexpress.model.UserModel;
import com.springboot.pizzaexpress.service.DeliverService;
import com.springboot.pizzaexpress.service.ExpressOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value ="/deliver")
@Api("配送员api")
public class DeliverController {
    @Autowired
    private  DeliverService deliverService;

    @Autowired
    private ExpressOrderService expressOrderService;

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

    @ApiOperation(value = "配送员登录", notes = "")
//    @ApiImplicitParam(name = "params", value = "", dataType = "JSON")
    @RequestMapping(value = "/deliverlogin", method = RequestMethod.POST)
    public Map<String,Object> deliverLogin(@RequestBody Deliver deliverModel, HttpSession session) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        System.err.println(deliverModel);

        int account = deliverModel.getDeliverId();
        String password =deliverModel.getPassword();
        System.err.println(account);
        System.err.println(password);

//        int account = Integer.parseInt(Account);
        Deliver deliver = deliverService.deliverLogin(account,password);
        System.err.println(deliver);

        if ( deliver!=null ) {
            session.setAttribute("deliver",deliver);
            System.out.println(session);
            resultMap.put("status", 200);
            resultMap.put("message", "登录成功");
            resultMap.put("account", deliver.getDeliverId());
            resultMap.put("shopID",deliver.getShopId());
            resultMap.put("deliverId",deliver.getDeliverId());
        }
        else {
            resultMap.put("status", 500);
            resultMap.put("message", "账号密码错误");
        }
        return resultMap;
    }

//    @ApiOperation(value = "配送员出发", notes = "")
//    @ApiImplicitParam(name = "params", value = "", dataType = "JSON")
//    @RequestMapping(value = "/deliversetout", method = RequestMethod.POST)
//    public void deliverSetOut (@RequestBody Map<String, Object> params) {
//        String deliverID = params.get("deliverId").toString();
//        int deliverId = Integer.parseInt(deliverID);
//
//        String newStatus = "正在配送";
//        deliverService.updateDeliverStatus(deliverId,newStatus);
//    }

//    @ApiOperation(value = "配送员全部送达", notes = "")
//    @ApiImplicitParam(name = "params", value = "", dataType = "JSON")
//    @RequestMapping(value = "/deliverfree", method = RequestMethod.POST)
//    public void deliverFree (@RequestBody Map<String, Object> params) {
//        String deliverID = params.get("deliverId").toString();
//        int deliverId = Integer.parseInt(deliverID);
//        String newStatus = "空闲";
//        String newExpressStatus = "已完成";
//        String oldExpressStatus = "正在配送";
//        deliverService.updateDeliverStatus(deliverId,newStatus);
//        expressOrderService.updateExpressStatus(deliverId,newExpressStatus,oldExpressStatus);
//    }

    @ApiOperation(value = "配送员某一订单送达", notes = "")
    @ApiImplicitParam(name = "params", value = "", dataType = "JSON")
    @RequestMapping(value = "/deliverfinishoneorder", method = RequestMethod.POST)
    public void deliverFinishOneOrder (@RequestBody Map<String, Object> params) {

        String orderID = params.get("pizzaOrderId").toString();
        String expressID = params.get("expressOrderId").toString();
        String deliverID = params.get("deliverId").toString();
        int deliverId = Integer.parseInt(deliverID);
        int expressId = Integer.parseInt(expressID);
        int orderId = Integer.parseInt(orderID);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String finishTime = sdf.format(new Date());
        String newStatus = "已送达";

        deliverService.deliverFinishOneOrder(deliverId,expressId,orderId,newStatus,finishTime);
    }

}
