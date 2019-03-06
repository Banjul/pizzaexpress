package com.springboot.pizzaexpress.serviceImpl;

/**
 * Created by sts on 2019/3/2.
 */
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
    public List<PizzaOrder> getLastTwentyOrders(int shop_id) {
        return orderDao.queryLastTwentyOrders(shop_id);
    }

    @Override
    public List<PizzaOrder> queryOrderByTimeAndShop(String start_time, String end_time, int shop_id) {
        return orderDao.queryOrderByTimeAndShop(start_time,end_time,shop_id);
    }

//    @Override
//    public List<PizzaOrder> getOrderByDeliver(int shop_id, int deliver_id) {
//        int express_id =
//        return orderDao.queryOrderByDeliver(shop_id,express_id);
//    }

}
