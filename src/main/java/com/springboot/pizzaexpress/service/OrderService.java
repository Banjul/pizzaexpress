package com.springboot.pizzaexpress.service;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.bean.PizzaOrder;

import javax.persistence.criteria.Order;
import java.util.List;

public interface OrderService {
    public String getLastTwentyOrders(int shop_id);

    public String queryOrderByTimeAndShop(String start_time, String end_time, int shop_id);

    public PizzaOrder queryOrderByOrderId(int orderID);

    public String queryOrderByOrderIdAndTime(int orderID,int shopId,String startTime,String endTime);

    public String queryOrderByDeliverAndTime(int deliverId,int shopId,String startTime, String entTime);

    public String getOrderByDeliver(int shop_id, int deliver_id);

    public String deleteOrderByOrderId(int orderId);
}
