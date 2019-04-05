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

import java.text.SimpleDateFormat;
import java.util.Date;
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
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (User user : users) {
                JSONObject userJSON = new JSONObject();
                userJSON.put("userID",user.getUserId());
                userJSON.put("userName",user.getNickName());
                userJSON.put("userPhone",user.getPhoneNumber());
                userJSON.put("userStatus",user.getStatus());
                userJSON.put("userAddress",user.getAddress());
                Date lastLoginS = user.getLastLoginTime();
                System.err.println(lastLoginS);
                String lastLogin = sdf.format(lastLoginS);
                userJSON.put("lastLogin",lastLogin);
//                userJSON.put("email",user.getEmail());
//                userJSON.put("money",user.getMoney());

                userArray.add(userJSON);
            }
        }
        userData.put("count",users.size());
        userData.put("data",userArray);
        dataJson.put("userData",userData);
        return dataJson.toJSONString();
    }

    @Override
    public String getUserById(int userId) {

        JSONArray userArray = new JSONArray();
        JSONObject userData = new JSONObject();
        JSONObject dataJson = new JSONObject();

        User user = userDao.queryUserByUserId(userId);
        if (user != null) {
            JSONObject userJSON = new JSONObject();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            userJSON.put("userID",user.getUserId());
            userJSON.put("userName",user.getNickName());
            userJSON.put("userPhone",user.getPhoneNumber());

            userJSON.put("userStatus",user.getStatus());
            userJSON.put("userAddress",user.getAddress());
            Date lastLoginS = user.getLastLoginTime();
            String lastLogin = sdf.format(lastLoginS);
            userJSON.put("lastLogin",lastLogin);
            userArray.add(userJSON);
        }


        userData.put("data",userArray);
        dataJson.put("userData",userData);
        return dataJson.toJSONString();
    }
}
