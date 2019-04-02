package com.springboot.pizzaexpress.dao;

/**
 * Created by sts on 2019/3/1.
 */

import com.springboot.pizzaexpress.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Component
@Repository
public interface UserDao extends JpaRepository<User,String> {

    @Query(value = "select * from user where nick_name = ?1",nativeQuery = true)
    User findByName(String name);

    @Modifying
    @Transactional
    @Query(value = "insert into user(nick_name,password,money) values(?1,?2,'0.00')",nativeQuery = true)
    int addUser(String name,String password);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update user set nick_name = ?1 where user_id = ?2",nativeQuery = true)
    int modifyName(String name,int userId);


}
