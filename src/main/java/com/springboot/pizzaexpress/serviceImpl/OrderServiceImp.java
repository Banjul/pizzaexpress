package com.springboot.pizzaexpress.serviceImpl;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.bean.Order;
import com.springboot.pizzaexpress.dao.OrderDao;
import com.springboot.pizzaexpress.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImp implements OrderService{
    @Autowired
    private OrderDao orderDao;

}
