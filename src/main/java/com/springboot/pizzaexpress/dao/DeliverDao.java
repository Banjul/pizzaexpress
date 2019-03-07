package com.springboot.pizzaexpress.dao;

/**
 * Created by sts on 2019/3/2.
 */

import com.springboot.pizzaexpress.bean.Deliver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Repository
public interface DeliverDao extends JpaRepository<Deliver,String>{
    /**
     * 查询实时数据基表
     * @param shopId
     * @return
     */
    @Query(value = "select * from deliver where shop_id = ?1",nativeQuery = true)
    List<Deliver> queryAllDeliversByShop(int shopId);

    /**
     * 查询实时数据基表
     * @param shopId
     * @return
     */
    @Query(value = "select * from deliver where shop_id = ?2 and deliver_id = ?1",nativeQuery = true)
    List<Deliver> queryDeliverById(int deliverID,int shopId);

    /**
     * 查询实时数据基表
     * @param shopId
     * @return
     */
    @Query(value = "select * from deliver where shop_id = ?2 and name = ?1",nativeQuery = true)
    List<Deliver> queryDeliverByName(String deliverName,int shopId);
}
