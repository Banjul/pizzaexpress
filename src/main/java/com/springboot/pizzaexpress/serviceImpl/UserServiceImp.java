package com.springboot.pizzaexpress.serviceImpl;

/**
 * Created by sts on 2019/3/2.
 */
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.springboot.pizzaexpress.bean.User;
import com.springboot.pizzaexpress.dao.UserDao;
import com.springboot.pizzaexpress.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    private UserDao userDao;

    @Override
    public User queryUserByUserId(int userId) {
        return userDao.queryUserByUserId(userId);
    }

    @Override
    public void updateUserMoney(int userId, double userMoney) {
        userDao.updateUserMoney(userId,userMoney);
    }

    @Override
    public User sysAdminLogin(int account, String password) {
        return userDao.getAdminByAccountAndPassword(account,password);
    }

    @Override
    public String getUserInfo() {
        JSONArray userArray = new JSONArray();
        JSONObject userData = new JSONObject();
        JSONObject dataJson = new JSONObject();

        List<User> users = userDao.queryUserInfo();
        if (users.size() >0) {
            for (User user : users) {
                JSONObject userJSON = new JSONObject();
                userJSON.put("userId",user.getUser_id());
                userJSON.put("userNickName",user.getNick_name());
                userJSON.put("userPhone",user.getPhone_number());
                userJSON.put("loginTime",user.getLastLoginTime());
                userJSON.put("userStatus",user.getStatus());

                userArray.add(userJSON);
            }
        }
        userData.put("count",users.size());
        userData.put("data",userArray);
        dataJson.put("userData",userData);
        return dataJson.toJSONString();
    }
}
