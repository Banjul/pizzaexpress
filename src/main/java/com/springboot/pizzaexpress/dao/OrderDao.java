package com.springboot.pizzaexpress.dao;

/**
 * Created by sts on 2019/3/1.
 */

import com.springboot.pizzaexpress.bean.PizzaOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
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
     * @return deliverId
     */
    @Query(value = "select * from pizza_order where shop_id = ?3 and start_time = between ?1 and ?2",nativeQuery = true)
    List<PizzaOrder> queryOrderByTimeAndShop(String start_time,String end_time,int shop_id);


}
