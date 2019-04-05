package com.springboot.pizzaexpress.dao;

/**
 * Created by sts on 2019/3/1.
 */

import com.springboot.pizzaexpress.bean.PizzaOrder;
import com.springboot.pizzaexpress.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Repository
public interface UserDao extends JpaRepository<User,String> {

    /**
     * 查找用户
     * @param userId
     * @return
     */
    @Query(value = "select * from user where user_id = ?1",nativeQuery = true)
    User queryUserByUserId(int userId);


    /**
     * 更新用户钱包
     * @param userId
     * @param userMoney
     */
    @Transactional
    @Modifying
    @Query(value = "update user set money = ?2 where order_id = ?1 ", nativeQuery = true)
    int updateUserMoney(int userId,double userMoney);



    /**
     * 查询系统管理员账户
     * @param account
     * @param password
     * @return
     */
    @Query(value = "select * from user where user_id =?1 and password =?2",nativeQuery = true)
    User getAdminByAccountAndPassword(int account,String password);

    @Query(value = "select * from user",nativeQuery = true)
    List<User> queryUserInfo();
}
