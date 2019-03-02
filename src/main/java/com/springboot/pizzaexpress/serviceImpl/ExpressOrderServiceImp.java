package com.springboot.pizzaexpress.serviceImpl;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.bean.ExpressOrder;
import com.springboot.pizzaexpress.dao.ExpressOrderDao;
import com.springboot.pizzaexpress.service.ExpressOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpressOrderServiceImp implements ExpressOrderService{
    @Autowired
    private ExpressOrderDao expressOrderDao;

}
