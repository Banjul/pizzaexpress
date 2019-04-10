package com.springboot.pizzaexpress.controller;

/**
 * Created by sts on 2019/3/2.
 */


import com.springboot.pizzaexpress.bean.Shop;
import com.springboot.pizzaexpress.bean.User;
import com.springboot.pizzaexpress.bean.PizzaOrder;
import com.springboot.pizzaexpress.dao.OrderDao;
import com.springboot.pizzaexpress.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import com.springboot.pizzaexpress.bean.Item;
import com.springboot.pizzaexpress.model.ItemWrapModel;
import com.springboot.pizzaexpress.model.PizzaOrderModel;
import com.springboot.pizzaexpress.model.ResponseModel;
import com.springboot.pizzaexpress.model.ShopModel;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.persistence.criteria.Order;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value ="/order")
@Api("订单api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private DeliverService deliverService;
    @Autowired
    private ItemService itemService;



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
        String deliverId = params.get("deliverID").toString();
        String orderId=params.get("orderID").toString();
        String start_time = params.get("startTime").toString();
        String end_time = params.get("endTime").toString();
        System.err.println(deliverId);
        if (deliverId.equals("-1") ){
            if(orderId.equals("-1")){
                return orderService.queryOrderByTimeAndShop(start_time,end_time,shopId);
            }

            int orderID = Integer.parseInt(orderId);
            if (start_time.equals("-1")) {
                return orderService.queryOrderByOrderId(orderID);
            }
            return orderService.queryOrderByOrderIdAndTime(orderID,shopId,start_time,end_time);
        }
        else{
            int deliverID = Integer.parseInt(deliverId);
            if (start_time.equals("-1")) {
                return orderService.getOrderByDeliver(shopId,deliverID);
            }
            return orderService.queryOrderByDeliverAndTime(deliverID,shopId,start_time,end_time);
        }

    }

//    @ApiOperation(value = "分配订单给配送员", notes = "需要，开始时间，结束时间")
//    @ApiImplicitParam(name = "params", value = "包含，deliverName,shopId,deliverID的Json", dataType = "JSON")
//    @RequestMapping(value = "/allocateordertodeliver", method = RequestMethod.POST)
//    public String allocateOrderToDeliver( @RequestBody Map<String, Object> params){
//        String shopid = params.get("shopID").toString();
//        int shopId = Integer.parseInt(shopid);
//        String orderID = params.get("orderID").toString();
//        int orderId = Integer.parseInt(orderID);
//
//        deliverService.allocateOrderToDeliver(shopId,orderId);
//        return null;
//    }
//    @ApiOperation(value="查询最新20条订单")
//    @ApiImplicitParam(name = "params", value = "包含开始时间，结束时间,shopId,deliverID,order_id的json", dataType = "JSON")
//    @RequestMapping(value = "/getcancelorder",method = RequestMethod.POST)
//    public String get(@RequestBody Map<String, Object> params){
//        String orderID = params.get("orderId").toString();
//        int orderId = Integer.parseInt(orderID);
//        PizzaOrder cancelOrder = orderService.queryOrderByOrderId(orderId);
////        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////        DateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date orderStartTime = cancelOrder.getStartTime();
//
//        long diff = new Date().getTime() - orderStartTime.getTime();
//        long time = 10*60*1000;
//
//        if (diff > time) {
//            return "超时无法修改";
//        }
//        else {
//            double price = cancelOrder.getPrice();
//            int userId = cancelOrder.getUserId();
//            User user = userService.queryUserByUserId(userId);
//            double userOldMoney = user.getMoney();
//            double userMoney = userOldMoney + price;
//            userService.updateUserMoney(userId,userMoney);
//            return orderService.deleteOrderByOrderId(orderId);
//        }
//    }




    @Autowired
    private ShopService shopService;
    @Autowired
    private UserService userService;

    @Autowired
    private OrderDao orderDao;

    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    public ResponseModel addOrder(@RequestBody Map<String,Object> pizzaOrderModel, HttpSession session) {
        ResponseModel responseModel = new ResponseModel();
        User u = (User) session.getAttribute("userInfo");
        if (u == null) {
            responseModel.setStatus("500");
            responseModel.setMessage("用户未登录!");

        } else {
            int userId = u.getUserId();
            // System.err.println(userId);
            Map<String,Object> shop = (Map<String,Object>)pizzaOrderModel.get("shop");
            int shopId = (int)shop.get("shopId");
            //int shopId = pizzaOrderModel.getShop().getShopId();
            String fromPosX = (String)shop.get("fromPosX");
            String fromPosY = (String)shop.get("fromPosY");
//            List<ItemWrapModel> itemsList = pizzaOrderModel.getItems();
            String items = pizzaOrderModel.get("items").toString();
            System.out.println(items);
//            JSONArray array = JSONArray.fromObject(itemsList);
//            String items = array.toString();
            String state = "1";//   订单未支付，状态为1
            //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            //df.format(new Date());

            Date startTime = new Date();

            String toPosX = (String)pizzaOrderModel.get("toPosX");
            String toPosY = (String)pizzaOrderModel.get("toPosY");
            double price = Double.parseDouble(pizzaOrderModel.get("price").toString());

            //获取用户账户余额
            double balance = userService.findBalance(userId);
            if(balance<price){
                responseModel.setStatus("500");
                responseModel.setMessage("余额不足！下单失败");

            }else {
                int orderId = orderDao.getMaxOrderNum();
                int result = deliverService.allocateOrderToDeliver(shopId,orderId+1);
                if (result != -1) {
                    orderService.insertToPizzaOrder(userId, shopId, items, startTime, state, fromPosX, fromPosY, toPosX, toPosY, price);

                    responseModel.setStatus("200");
                    responseModel.setMessage("下单成功！");
                    responseModel.setModel(pizzaOrderModel);
                    balance = balance - price;
                    userService.modifyBalance(balance, userId);
                }
                else {
                    responseModel.setStatus("500");
                    responseModel.setMessage("无空闲配送员可接单！");
                }
            }
        }
        return responseModel;
    }

    @RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
    public ResponseModel cancelOrder(@RequestBody PizzaOrderModel pizzaOrderModel, HttpSession session) {
        ResponseModel responseModel = new ResponseModel();
        User u = (User) session.getAttribute("userInfo");
        if (u == null) {
            responseModel.setStatus("500");
            responseModel.setMessage("用户未登录!");
        } else {
            int orderId = pizzaOrderModel.getPizzaOrderId();
            Date time = new Date();
            System.out.println(time);
            Date startTime = orderService.findStartTime(orderId);
            long start = time.getTime();
            long end = startTime.getTime();
            int msec = (int) (start - end);
            System.out.println("两个时间之间的毫秒差为：" + msec);
            if (msec > 600000) {
                responseModel.setStatus("500");
                responseModel.setMessage("超时，取消失败！");
            } else {
                orderService.modifyStatus(orderId);
                responseModel.setStatus("200");
                responseModel.setMessage("取消成功！");
            }
        }
        return responseModel;
    }

    @RequestMapping(value = "/getOrdersById", method = RequestMethod.GET)
    public ResponseModel getOrdersByUserId(HttpSession session) {
        ResponseModel responseModel = new ResponseModel();
        User u = (User) session.getAttribute("userInfo");
        List<PizzaOrderModel> modelList = new ArrayList<>();
        if (u == null) {
            responseModel.setStatus("500");
            responseModel.setMessage("用户未登录!");
        } else {
            int userId = u.getUserId();
            List<PizzaOrder> list = orderService.queryOrderByUserId(userId);
            for (PizzaOrder pizzaOrder : list) {
                int shopId = pizzaOrder.getShopId();
                Shop shop = shopService.findByShopId(shopId);
                ShopModel shopModel = new ShopModel();
                shopModel.setShopName(shop.getShopName());
                shopModel.setShopId(shopId);
                String items = pizzaOrder.getItems();
                JSONArray array = JSONArray.fromObject(items);
                List<ItemWrapModel> itemWrapModels = new ArrayList<>();
                ItemWrapModel itemWrapModel = new ItemWrapModel();
                for(Object o : array){
                    JSONObject object = JSONObject.fromObject(o);
                    JSONObject a = JSONObject.fromObject(object.getString("item"));
                    int b = Integer.parseInt(object.getString("count"));
                    Item item = (Item) JSONObject.toBean(a, Item.class);
                    int itemId = item.getItemId();
                    Item i = itemService.findByItemId(itemId);
                    itemWrapModel.setItem(i);
                    itemWrapModel.setCount(b);
                    itemWrapModels.add(itemWrapModel);
                }
                PizzaOrderModel pizzaOrderModel = new PizzaOrderModel();
                pizzaOrderModel.setUserId(pizzaOrder.getUserId());
                pizzaOrderModel.setPizzaOrderId(pizzaOrder.getOrderId());
                pizzaOrderModel.setStartTime(pizzaOrder.getStartTime());
                pizzaOrderModel.setState(pizzaOrder.getState());
                pizzaOrderModel.setPrice(pizzaOrder.getPrice());
                pizzaOrderModel.setToPosX(pizzaOrder.getToPosX());
                pizzaOrderModel.setToPosY(pizzaOrder.getToPosY());
                pizzaOrderModel.setShop(shopModel);
                pizzaOrderModel.setItems(itemWrapModels);

                // pizzaOrderModel.setItems(pizzaOrder.);
                modelList.add(pizzaOrderModel);
            }

            responseModel.setStatus("200");
            responseModel.setMessage("查询成功！");
            responseModel.setModel(modelList);
        }
        return responseModel;

    }

}




