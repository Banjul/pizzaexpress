package com.springboot.pizzaexpress.dao;

/**
 * Created by sts on 2019/3/1.
 */

import com.springboot.pizzaexpress.bean.Shop;
import com.springboot.pizzaexpress.model.ShopModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface ShopDao extends JpaRepository<Shop,String> {

    @Query(value = "select * from shop where shop_id=?1",nativeQuery = true)
    Shop findByShopId(int shopId);

    @Query(value = "select * from shop where shop_id=?1",nativeQuery = true)
    ShopModel findByShopIdModel(int shopId);
}
