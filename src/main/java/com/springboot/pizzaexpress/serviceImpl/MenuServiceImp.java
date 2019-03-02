package com.springboot.pizzaexpress.serviceImpl;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.bean.Menu;
import com.springboot.pizzaexpress.dao.MenuDao;
import com.springboot.pizzaexpress.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImp implements MenuService{
    @Autowired
    private MenuDao menuDao;

}
