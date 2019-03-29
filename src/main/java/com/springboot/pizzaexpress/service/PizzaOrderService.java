package com.springboot.pizzaexpress.service;

import com.springboot.pizzaexpress.bean.Cart;
import com.springboot.pizzaexpress.bean.PizzaOrder;

import java.util.Date;

/**
 * Created by sts on 2019/3/2.
 */

public interface PizzaOrderService {

    public int insertToPizzaOrder(int userId, int shopId, String items, Date startTime,String state,
                                  String fromPosX,String fromPosY,String toPosX,String toPosY,double price);

}
