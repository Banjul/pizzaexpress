package com.springboot.pizzaexpress.service;

/**
 * Created by sts on 2019/3/2.
 */

import com.springboot.pizzaexpress.bean.Shop;
import com.springboot.pizzaexpress.bean.User;

import java.util.Date;
import java.util.List;

public interface UserService {
    public User findByUserName(String name);
    public int setUser(String name,String password,Date time);
    public int modifyName(String name,int userId);
    public int modifyAddress(String address,int userId);
    public User findById(int userId);
    public int modifyState(Date time,int userId);
    public double findBalance(int userId);
    public int modifyBalance(double balance,int userId);
    //public int setCode(String code,Date time);
    public User findByTelephone(String telephone);
    public int setUser2(String nickName,String telephone,Date time);
}
