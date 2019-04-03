package com.springboot.pizzaexpress.serviceImpl;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.bean.Order;
import com.springboot.pizzaexpress.dao.OrderDao;
import com.springboot.pizzaexpress.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderDao orderDao;


    @Override
    public int insertToPizzaOrder(int userId, int shopId, String items, Date startTime, String state,
                                  String fromPosX, String fromPosY, String toPosX, String toPosY,double price){
    return orderDao.insertToPizzaOrder(userId, shopId, items,startTime,state,fromPosX,fromPosY,toPosX,toPosY,price);
    }

    @Override
    public Date findStartTime(int orderId) {
        return orderDao.findStartTime(orderId);
    }

    @Override
    public void modifyStatus(int orderId) {
        orderDao.modifyStatus(orderId);
    }

    @Override
    public List<Order> queryOrderByUserId(int userId) {
        return orderDao.queryOrderByUserId(userId);

    }
}
