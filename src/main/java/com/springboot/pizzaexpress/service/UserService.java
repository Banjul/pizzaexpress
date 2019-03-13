package com.springboot.pizzaexpress.service;

/**
 * Created by sts on 2019/3/2.
 */

import com.springboot.pizzaexpress.bean.Shop;
import com.springboot.pizzaexpress.bean.User;
import java.util.List;

public interface UserService {
    public User findByUserName(String name);
    public int setUser(String name,String password);


}
