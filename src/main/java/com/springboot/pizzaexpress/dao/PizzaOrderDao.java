package com.springboot.pizzaexpress.dao;

/**
 * Created by sts on 2019/3/1.
 */

import com.springboot.pizzaexpress.bean.PizzaOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component
@Repository
public interface PizzaOrderDao extends JpaRepository<PizzaOrder,String> {

    @Modifying
    @Transactional
    @Query(value = "insert into pizza_order(user_id,shop_id,items,start_time,state,from_pos_x,from_pos_y,to_pos_x,to_pos_y,price) values(?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10)",nativeQuery = true)
    int insertToPizzaOrder(int userId, int shopId, String items, Date startTime, String state, String fromPosX, String fromPosY, String toPosX, String toPosY,double price);


    @Query(value ="SELECT start_time from pizza_order where order_id = ?1",nativeQuery = true)
    Date findStartTime(int orderId);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value ="update pizza_order set state = 2 where order_id = ?1",nativeQuery = true)
    void modifyStatus(int orderId);


    @Query(value = "select * from pizza_order where user_id = ?1",nativeQuery = true)
    List<PizzaOrder> queryOrderByUserId(int userId);

}
