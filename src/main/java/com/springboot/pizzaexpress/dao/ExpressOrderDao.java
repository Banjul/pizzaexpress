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

    /**
     * 查询实时数据基表
     * @param deliverId
     * @return
     */
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
}
