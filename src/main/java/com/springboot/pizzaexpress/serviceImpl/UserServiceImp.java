package com.springboot.pizzaexpress.serviceImpl;

/**
 * Created by sts on 2019/3/2.
 */

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.springboot.pizzaexpress.bean.Shop;

import com.springboot.pizzaexpress.bean.User;
import com.springboot.pizzaexpress.dao.UserDao;
import com.springboot.pizzaexpress.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


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
                String jsonString = user.getAddress();
                net.sf.json.JSONObject object = net.sf.json.JSONObject.fromObject(jsonString);
                String userAddress1= object.getString("addressStr");
                String userAddress2= object.getString("addressStr2");
                String userAddress = userAddress1 +" "+userAddress2;
                userJSON.put("userAddress",userAddress);
                Date lastLoginS = user.getLastLoginTime();
                System.err.println(lastLoginS);
                String lastLogin = "2019-04-06 19:04:08";
                if(lastLoginS != null){
                    lastLogin = sdf.format(lastLoginS);
                }
                userJSON.put("lastLogin",lastLogin);
//                userJSON.put("email",user.getEmail());
//                userJSON.put("money",user.getMoney());

                userArray.add(userJSON);
            }
        }
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
            userJSON.put("userID", user.getUserId());
            userJSON.put("userName", user.getNickName());
            userJSON.put("userPhone", user.getPhoneNumber());
            userJSON.put("userStatus", user.getStatus());
            String jsonString = user.getAddress();
            net.sf.json.JSONObject object = net.sf.json.JSONObject.fromObject(jsonString);
            String userAddress1= object.getString("addressStr");
            String userAddress2= object.getString("addressStr2");
            String userAddress = userAddress1 +" "+userAddress2;
            userJSON.put("userAddress", userAddress);

            Date lastLoginS = user.getLastLoginTime();
            String lastLogin = "2019-04-06 19:04:08";
            if (lastLoginS != null) {
                lastLogin = sdf.format(lastLoginS);
            }
            userJSON.put("lastLogin", lastLogin);
            userArray.add(userJSON);
        }


        userData.put("data", userArray);
        dataJson.put("userData", userData);
        return dataJson.toJSONString();
    }

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
