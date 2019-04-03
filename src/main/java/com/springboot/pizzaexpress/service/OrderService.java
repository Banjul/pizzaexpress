package com.springboot.pizzaexpress.service;

import com.springboot.pizzaexpress.bean.Order;

import java.util.Date;
import java.util.List;

/**
 * Created by sts on 2019/3/2.
 */

public interface OrderService {

    public int insertToPizzaOrder(int userId, int shopId, String items, Date startTime,String state,
                                  String fromPosX,String fromPosY,String toPosX,String toPosY,double price);

    public Date findStartTime(int orderId);

    public void modifyStatus(int orderId);

    public List<Order> queryOrderByUserId(int userId);
}
