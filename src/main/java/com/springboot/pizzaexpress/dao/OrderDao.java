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

import java.util.List;

@Component
@Repository
public interface OrderDao extends JpaRepository<PizzaOrder,String> {

    /**
     * 查询实时数据基表
     * @param shopId
     * @return
     */
    @Query(value = "select * from pizza_order where shop_id = ?1 order by order_id desc LIMIT 20",nativeQuery = true)
    List<PizzaOrder> queryLastTwentyOrders(int shopId);

    /**
     * 查询实时数据基表
     * @param shop_id
     * @param start_time
     * @param end_time
     * @return
     */
    @Query(value = "select * from pizza_order where shop_id = ?3 and start_time  between ?1 and ?2",nativeQuery = true)
    List<PizzaOrder> queryOrderByTimeAndShop(String start_time,String end_time,int shop_id);

    /**
     * 查询实时数据基表
     * @param orderID
     * @return
     */
    @Query(value = "select * from pizza_order where  order_id = ?1",nativeQuery = true)
    PizzaOrder queryOrderByOrderId(int orderID);


    /**
     * 查询实时数据基表
     * @param orderID
     * @param shopId
     * @param startTime
     * @param endTime
     * @return
     */
    @Query(value = "select * from pizza_order where shop_id = ?2 and start_time  between ?3 and ?4 and order_id = ?1",nativeQuery = true)
    List<PizzaOrder> queryOrderByOrderIdAndTime(int orderID, int shopId, String startTime, String endTime);


    /**
     * 查询实时数据基表
     * @param deliverId
     * @param shopId
     * @param startTime
     * @param endTime
     * @return
     */
    @Query(value = "select * from pizza_order where shop_id = ?2 and start_time  between ?3 and ?4 and deliver_id = ?1",nativeQuery = true)
    List<PizzaOrder> queryOrderByDeliverAndTime(int deliverId, int shopId, String startTime, String endTime);



    /**
     * 查询实时数据基表
     * @param shop_id
     * @param deliver_id
     * @return
     */
    @Query(value = "select * from pizza_order where shop_id = ?1 and deliver_id = ?2",nativeQuery = true)
    List<PizzaOrder> queryOrderByDeliver(int shop_id, int deliver_id);


    /**
     * 根据站点对内信息删除站点
     * @param orderId
     */
    @Transactional
    @Modifying
    @Query(value = "delete from pizza_order where order_id = ?1 ", nativeQuery = true)
    void deleteOrderByOrderId(int orderId);

    @Transactional
    @Modifying
    @Query(value = "update pizza_order set end_time = ?3, state = ?2 where order_id= ?1",nativeQuery = true)
    void updateOrderStatus(int orderId,String newStatus, String finishTime);

}