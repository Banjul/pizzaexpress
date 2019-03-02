package com.springboot.pizzaexpress.serviceImpl;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.bean.Deliver;
import com.springboot.pizzaexpress.dao.DeliverDao;
import com.springboot.pizzaexpress.service.DeliverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliverServiceImp implements DeliverService{
    @Autowired
    private DeliverDao deliverDao;

}
