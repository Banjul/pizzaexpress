package com.springboot.pizzaexpress.dao;

/**
 * Created by sts on 2019/3/1.
 */

import com.springboot.pizzaexpress.bean.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface MenuDao extends JpaRepository<Menu,String> {

    @Query(value = "select * from menu where shop_id = ?1",nativeQuery = true)
    Menu queryMenuByShopId(int shopId);


}
