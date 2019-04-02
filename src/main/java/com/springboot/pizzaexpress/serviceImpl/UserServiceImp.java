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
    public int setUser(String name,String password){
        int userId = userDao.addUser(name,password);
        return userId;
    }

    @Override
    public int modifyName(String name,int userId) {
        return userDao.modifyName(name,userId);
    }
}
