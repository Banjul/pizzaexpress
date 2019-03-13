package com.springboot.pizzaexpress.serviceImpl;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.dao.PizzaOrderDao;
import com.springboot.pizzaexpress.service.PizzaOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaOrderServiceImp implements PizzaOrderService {
    @Autowired
    private PizzaOrderDao pizzaOrderDao;

}
