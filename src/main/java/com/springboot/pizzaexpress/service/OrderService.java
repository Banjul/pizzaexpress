package com.springboot.pizzaexpress.service;

import java.util.Date;
import java.util.List;

/**
 * Created by sts on 2019/3/2.
 */

import com.springboot.pizzaexpress.bean.PizzaOrder;

import java.util.List;


public interface OrderService {
    public String getLastTwentyOrders(int shop_id);

    public String queryOrderByTimeAndShop(String start_time, String end_time, int shop_id);
//
    public String queryOrderByOrderId(int orderID);
//
    public String queryOrderByOrderIdAndTime(int orderID,int shopId,String startTime,String endTime);
//
    public String queryOrderByDeliverAndTime(int deliverId,int shopId,String startTime, String entTime);
//
    public String getOrderByDeliver(int shop_id, int deliver_id);


//    public String deleteOrderByOrderId(int orderId);

    public int insertToPizzaOrder(int userId, int shopId, String items, Date startTime,String state,
                                  String fromPosX,String fromPosY,String toPosX,String toPosY,double price, int deliverId,int expressId);

    public Date findStartTime(int orderId);

    public void modifyStatus(int orderId);

    public List<PizzaOrder> queryOrderByUserId(int userId);
}
