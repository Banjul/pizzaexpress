package com.springboot.pizzaexpress.dao;

/**
 * Created by sts on 2019/3/1.
 */


import com.springboot.pizzaexpress.bean.User;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Date;


@Component
@Repository
public interface UserDao extends JpaRepository<User, Integer> {


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

    @Query(value = "select * from user where nick_name = ?1",nativeQuery = true)
    User findByName(String name);

    @Modifying
    @Transactional
    @Query(value = "insert into user(nick_name,password,money,status,last_login) values(?1,?2,'0.00','online',?3)",nativeQuery = true)
    int addUser(String name,String password,Date time);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update user set nick_name = ?1 where user_id = ?2",nativeQuery = true)
    int modifyName(String name,int userId);

    @Query(value = "select * from user where user_id = ?1",nativeQuery = true)
    User findByUserId(int userId);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update user set address = ?1 where user_id =?2",nativeQuery = true)
    int modifyAddress(String address,int userId);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value ="update user set status = 'online' , last_login=?1 where user_id=?2",nativeQuery = true)
    int modifyState(Date time,int userId);

    @Query(value = "select money from user where user_id=?1",nativeQuery = true)
    double findBalance(int userId);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value ="update user set money=?1 where user_id =?2",nativeQuery = true)
    int modifyBalance(double balance,int userId);

    @Query(value = "select * from user where phone_number=?1",nativeQuery = true)
    User findByTelephone(String telephone);

    @Modifying
    @Transactional
    @Query(value = "insert into user(nick_name,phone_number,money,status,last_login) VALUES (?1,?2,'99.00','online',?3)",nativeQuery = true)
    int addUser2(String nickName, String telephone, Date time);


    @Query(value = "select * from user",nativeQuery = true)
    List<User> queryUserInfo();
}
