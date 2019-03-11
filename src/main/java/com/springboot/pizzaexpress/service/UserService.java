package com.springboot.pizzaexpress.service;

/**
 * Created by sts on 2019/3/2.
 */

import com.springboot.pizzaexpress.bean.User;
import java.util.List;

public interface UserService {

    public User queryUserByUserId(int userId);

    public void updateUserMoney(int userId, double userMoney);

    public User sysAdminLogin(int account,String password);

    public String getUserInfo();

}
