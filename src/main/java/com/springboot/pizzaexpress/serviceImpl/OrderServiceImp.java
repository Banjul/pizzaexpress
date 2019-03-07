package com.springboot.pizzaexpress.serviceImpl;

/**
 * Created by sts on 2019/3/2.
 */
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.springboot.pizzaexpress.bean.PizzaOrder;
import com.springboot.pizzaexpress.dao.OrderDao;
import com.springboot.pizzaexpress.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImp implements OrderService{
    @Autowired
    private OrderDao orderDao;

    @Override
    public String getLastTwentyOrders(int shop_id) {
        JSONArray orderArray = new JSONArray();
        JSONObject orderData = new JSONObject();
        JSONObject dataJson = new JSONObject();

        List<PizzaOrder> pizzaOrders = orderDao.queryLastTwentyOrders(shop_id);
        if (pizzaOrders.size() >0) {
            for (PizzaOrder pizzaOrder : pizzaOrders) {
                JSONObject pizzaOrderJSON = new JSONObject();
                pizzaOrderJSON.put("orderid",pizzaOrder.getOrder_id());
                pizzaOrderJSON.put("date",pizzaOrder.getStart_time());
                pizzaOrderJSON.put("user",pizzaOrder.getUser_id());
                pizzaOrderJSON.put("deliver",pizzaOrder.getDeliver_id());
                pizzaOrderJSON.put("orderInfo",pizzaOrder.getItems());
                pizzaOrderJSON.put("orderStatus",pizzaOrder.getState());
                pizzaOrderJSON.put("orderAmount",pizzaOrder.getPrice());

                orderArray.add(pizzaOrderJSON);
            }
            orderData.put("count",pizzaOrders.size());
            orderData.put("data",orderArray);
            dataJson.put("orderData",orderData);
            return dataJson.toJSONString();
        }
        else {
            orderData.put("count",pizzaOrders.size());
            orderData.put("data",orderArray);
            dataJson.put("orderData",orderData);
            return dataJson.toJSONString();
        }

    }

    @Override
    public String queryOrderByTimeAndShop(String start_time, String end_time, int shop_id) {
        JSONArray orderArray = new JSONArray();
        JSONObject orderData = new JSONObject();
        JSONObject dataJson = new JSONObject();

        List<PizzaOrder> pizzaOrders = orderDao.queryOrderByTimeAndShop(start_time,end_time,shop_id);
        if (pizzaOrders.size() >0) {
            for (PizzaOrder pizzaOrder : pizzaOrders) {
                JSONObject pizzaOrderJSON = new JSONObject();
                pizzaOrderJSON.put("orderid",pizzaOrder.getOrder_id());
                pizzaOrderJSON.put("date",pizzaOrder.getStart_time());
                pizzaOrderJSON.put("user",pizzaOrder.getUser_id());
                pizzaOrderJSON.put("deliver",pizzaOrder.getDeliver_id());
                pizzaOrderJSON.put("orderInfo",pizzaOrder.getItems());
                pizzaOrderJSON.put("orderStatus",pizzaOrder.getState());
                pizzaOrderJSON.put("orderAmount",pizzaOrder.getPrice());

                orderArray.add(pizzaOrderJSON);
            }
            orderData.put("count",pizzaOrders.size());
            orderData.put("data",orderArray);
            dataJson.put("orderData",orderData);
            return dataJson.toJSONString();
        }
        else {
            orderData.put("count",pizzaOrders.size());
            orderData.put("data",orderArray);
            dataJson.put("orderData",orderData);
            return dataJson.toJSONString();
        }

    }

    @Override
    public String queryOrderByOrderId(int orderID, int shopId) {
        JSONArray orderArray = new JSONArray();
        JSONObject orderData = new JSONObject();
        JSONObject dataJson = new JSONObject();

        List<PizzaOrder> pizzaOrders = orderDao.queryOrderByOrderId(orderID,shopId);
        if (pizzaOrders.size() >0) {
            for (PizzaOrder pizzaOrder : pizzaOrders) {
                JSONObject pizzaOrderJSON = new JSONObject();
                pizzaOrderJSON.put("orderid",pizzaOrder.getOrder_id());
                pizzaOrderJSON.put("date",pizzaOrder.getStart_time());
                pizzaOrderJSON.put("user",pizzaOrder.getUser_id());
                pizzaOrderJSON.put("orderInfo",pizzaOrder.getItems());
                pizzaOrderJSON.put("orderStatus",pizzaOrder.getState());
                pizzaOrderJSON.put("orderAmount",pizzaOrder.getPrice());

                orderArray.add(pizzaOrderJSON);
            }
            orderData.put("count",pizzaOrders.size());
            orderData.put("data",orderArray);
            dataJson.put("orderData",orderData);
            return dataJson.toJSONString();
        }
        else {
            orderData.put("count",pizzaOrders.size());
            orderData.put("data",orderArray);
            dataJson.put("orderData",orderData);
            return dataJson.toJSONString();
        }
    }

    @Override
    public String queryOrderByOrderIdAndTime(int orderID, int shopId, String startTime, String endTime) {
        JSONArray orderArray = new JSONArray();
        JSONObject orderData = new JSONObject();
        JSONObject dataJson = new JSONObject();

        List<PizzaOrder> pizzaOrders = orderDao.queryOrderByOrderIdAndTime(orderID,shopId,startTime,endTime);
        if (pizzaOrders.size() >0) {
            for (PizzaOrder pizzaOrder : pizzaOrders) {
                JSONObject pizzaOrderJSON = new JSONObject();
                pizzaOrderJSON.put("orderid",pizzaOrder.getOrder_id());
                pizzaOrderJSON.put("date",pizzaOrder.getStart_time());
                pizzaOrderJSON.put("user",pizzaOrder.getUser_id());
                pizzaOrderJSON.put("deliver",pizzaOrder.getDeliver_id());
                pizzaOrderJSON.put("orderInfo",pizzaOrder.getItems());
                pizzaOrderJSON.put("orderStatus",pizzaOrder.getState());
                pizzaOrderJSON.put("orderAmount",pizzaOrder.getPrice());

                orderArray.add(pizzaOrderJSON);
            }
            orderData.put("count",pizzaOrders.size());
            orderData.put("data",orderArray);
            dataJson.put("orderData",orderData);
            return dataJson.toJSONString();
        }
        else {
            orderData.put("count",pizzaOrders.size());
            orderData.put("data",orderArray);
            dataJson.put("orderData",orderData);
            return dataJson.toJSONString();
        }
    }

    @Override
    public String queryOrderByDeliverAndTime(int deliverId, int shopId, String startTime, String endTime) {
        JSONArray orderArray = new JSONArray();
        JSONObject orderData = new JSONObject();
        JSONObject dataJson = new JSONObject();

        List<PizzaOrder> pizzaOrders = orderDao.queryOrderByDeliverAndTime(deliverId,shopId,startTime,endTime);
        if (pizzaOrders.size() >0) {
            for (PizzaOrder pizzaOrder : pizzaOrders) {
                JSONObject pizzaOrderJSON = new JSONObject();
                pizzaOrderJSON.put("orderid",pizzaOrder.getOrder_id());
                pizzaOrderJSON.put("date",pizzaOrder.getStart_time());
                pizzaOrderJSON.put("user",pizzaOrder.getUser_id());
                pizzaOrderJSON.put("deliver",pizzaOrder.getDeliver_id());
                pizzaOrderJSON.put("orderInfo",pizzaOrder.getItems());
                pizzaOrderJSON.put("orderStatus",pizzaOrder.getState());
                pizzaOrderJSON.put("orderAmount",pizzaOrder.getPrice());

                orderArray.add(pizzaOrderJSON);
            }
            orderData.put("count",pizzaOrders.size());
            orderData.put("data",orderArray);
            dataJson.put("orderData",orderData);
            return dataJson.toJSONString();
        }
        else {
            orderData.put("count",pizzaOrders.size());
            orderData.put("data",orderArray);
            dataJson.put("orderData",orderData);
            return dataJson.toJSONString();
        }
    }

    @Override
    public String getOrderByDeliver(int shop_id, int deliver_id) {
        JSONArray orderArray = new JSONArray();
        JSONObject orderData = new JSONObject();
        JSONObject dataJson = new JSONObject();

        List<PizzaOrder> pizzaOrders = orderDao.queryOrderByDeliver(shop_id,deliver_id);
        if (pizzaOrders.size() >0) {
            for (PizzaOrder pizzaOrder : pizzaOrders) {
                JSONObject pizzaOrderJSON = new JSONObject();
                pizzaOrderJSON.put("orderid",pizzaOrder.getOrder_id());
                pizzaOrderJSON.put("date",pizzaOrder.getStart_time());
                pizzaOrderJSON.put("user",pizzaOrder.getUser_id());
                pizzaOrderJSON.put("orderInfo",pizzaOrder.getItems());
                pizzaOrderJSON.put("orderStatus",pizzaOrder.getState());
                pizzaOrderJSON.put("orderAmount",pizzaOrder.getPrice());

                orderArray.add(pizzaOrderJSON);
            }
            orderData.put("count",pizzaOrders.size());
            orderData.put("data",orderArray);
            dataJson.put("orderData",orderData);
            return dataJson.toJSONString();
        }
        else {
            orderData.put("count",pizzaOrders.size());
            orderData.put("data",orderArray);
            dataJson.put("orderData",orderData);
            return dataJson.toJSONString();
        }
    }

}
