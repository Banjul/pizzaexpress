package com.springboot.pizzaexpress.serviceImpl;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.bean.Item;
import com.springboot.pizzaexpress.dao.ItemDao;
import com.springboot.pizzaexpress.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImp implements ItemService{
    @Autowired
    private ItemDao itemDao;

}
