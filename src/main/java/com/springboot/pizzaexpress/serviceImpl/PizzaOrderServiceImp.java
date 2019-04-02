package com.springboot.pizzaexpress.serviceImpl;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.bean.PizzaOrder;
import com.springboot.pizzaexpress.dao.PizzaOrderDao;
import com.springboot.pizzaexpress.service.PizzaOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PizzaOrderServiceImp implements PizzaOrderService {
    @Autowired
    private PizzaOrderDao pizzaOrderDao;


    @Override
    public int insertToPizzaOrder(int userId, int shopId, String items, Date startTime, String state,
                                  String fromPosX, String fromPosY, String toPosX, String toPosY,double price){
    return pizzaOrderDao.insertToPizzaOrder(userId, shopId, items,startTime,state,fromPosX,fromPosY,toPosX,toPosY,price);
    }

    @Override
    public Date findStartTime(int orderId) {
        return pizzaOrderDao.findStartTime(orderId);
    }

    @Override
    public void modifyStatus(int orderId) {
        pizzaOrderDao.modifyStatus(orderId);
    }

    @Override
    public List<PizzaOrder> queryOrderByUserId(int userId) {
        return pizzaOrderDao.queryOrderByUserId(userId);

    }
}
