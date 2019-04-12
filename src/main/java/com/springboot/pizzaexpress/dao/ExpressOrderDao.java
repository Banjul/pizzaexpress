package com.springboot.pizzaexpress.dao;

/**
 * Created by sts on 2019/3/1.
 */

import com.springboot.pizzaexpress.bean.ExpressOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Repository
public interface ExpressOrderDao extends JpaRepository<ExpressOrder,String> {


    @Query(value = "select * from express_order where deliver_id = ?1 ",nativeQuery = true)
    List<ExpressOrder> queryExpressOrderByDeliver(int deliverId);

    @Query(value = "select * from express_order where deliver_id = ?1 and express_status = ?2",nativeQuery = true)
    ExpressOrder queryExpressOrderByDeliverAndStatus(int deliverId, String expressStatus);


    @Transactional
    @Modifying
    @Query(value = "update express_order set order_list = ?2 where express_id= ?1",nativeQuery = true)
    void updateExpressOrderOrderList(int expressOrderId,String newOrderList);

    @Transactional
    @Modifying
    @Query(value = "insert into express_order(deliver_id,order_list) values(?1,?2)",nativeQuery = true)
    void insertExpressOrderOrderList(int deliverId,String OrderList);

    @Query(value = "select * from express_order where deliver_id =?1 and (express_status = ?2 or express_status = ?3)",nativeQuery = true)
    ExpressOrder getExpressContentByDeliverId(int deliverId, String status1, String status2);

    @Transactional
    @Modifying
    @Query(value = "update express_order set express_status = ?2 where deliver_id = ?1 and express_status = ?3",nativeQuery = true)
    void updateExpressStatus(int deliverId,String newExpressStatus,String oldExpressStatus);

    @Transactional
    @Modifying
    @Query(value = "update express_order set express_status = ?2 where express_id= ?1",nativeQuery = true)
    void updateExpressStatusById(int expressOrderId,String newStatus);

    @Query(value = "select * from express_order where express_id = ?1",nativeQuery = true)
    ExpressOrder queryExpressOrderById(int expressId);


}
