package com.springboot.pizzaexpress.dao;

/**
 * Created by sts on 2019/3/1.
 */

import com.springboot.pizzaexpress.bean.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface ShopDao extends JpaRepository<Shop,String> {

    /**
     * 查询实时数据基表
     * @param adminAccount
     * @param admingPassword
     * @return
     */
    @Query(value = "select * from shop where account =?1 and password =?2")
    Shop getAdminByAccountAndPassword(String adminAccount,String admingPassword);
}
