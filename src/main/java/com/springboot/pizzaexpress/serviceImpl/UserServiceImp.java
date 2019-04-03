package com.springboot.pizzaexpress.serviceImpl;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.bean.Shop;
import com.springboot.pizzaexpress.bean.User;
import com.springboot.pizzaexpress.dao.UserDao;
import com.springboot.pizzaexpress.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
public class UserServiceImp implements UserService{
    @Autowired
    private UserDao userDao;

    @Override
    public User findByUserName(String name){
        User user = userDao.findByName(name);
        return user;
    }


    @Override
    public int setUser(String name,String password,Date time){
        int userId = userDao.addUser(name,password,time);
        return userId;
    }

    @Override
    public int modifyName(String name,int userId) {
        return userDao.modifyName(name,userId);
    }

    @Override
    public int modifyAddress(String address, int userId) {
        return userDao.modifyAddress(address,userId);
    }

    @Override
    public User findById(int userId) {
        return userDao.findByUserId(userId);
    }

    @Override
    public int modifyState(Date time,int userId) {
        return userDao.modifyState(time,userId);
    }

    @Override
    public double findBalance(int userId) {
        return userDao.findBalance(userId);
    }

    @Override
    public int modifyBalance(double balance,int userId) {
        return userDao.modifyBalance(balance,userId);
    }

//    @Override
//    public int setCode(String code, Date time) {
//        return userDao.setCode(code,time);
//    }


    @Override
    public User findByTelephone(String telephone) {
        return userDao.findByTelephone(telephone);
    }

    @Override
    public int setUser2(String nickName, String telephone, Date time) {
        return userDao.addUser2(nickName,telephone,time);
    }
}
