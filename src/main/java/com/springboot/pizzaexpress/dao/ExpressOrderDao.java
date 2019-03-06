package com.springboot.pizzaexpress.dao;

/**
 * Created by sts on 2019/3/1.
 */

import com.springboot.pizzaexpress.bean.ExpressOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

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

}
