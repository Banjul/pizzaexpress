package com.springboot.pizzaexpress.service;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.bean.PizzaOrder;
import java.util.List;

public interface OrderService {
    public List<PizzaOrder> getLastTwentyOrders(int shop_id);

    public List<PizzaOrder> queryOrderByTimeAndShop(String start_time, String end_time, int shop_id);

//    public List<PizzaOrder> getOrderByDeliver(int shop_id, int deliver_id);
}
