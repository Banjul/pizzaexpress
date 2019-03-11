package com.springboot.pizzaexpress.dao;

/**
 * Created by sts on 2019/3/1.
 */

import com.springboot.pizzaexpress.bean.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Repository
public interface ShopDao extends JpaRepository<Shop,String> {

    /**
     * 查询实时数据基表
     * @param adminAccount
     * @param adminPassword
     * @return
     */
    @Query(value = "select * from shop where account =?1 and password =?2",nativeQuery = true)
    Shop getAdminByAccountAndPassword(String adminAccount,String adminPassword);


    /**
     * 查询商家
     * @param shopId
     * @return
     */
    @Query(value = "select * from shop where shop_id = ?1",nativeQuery = true)
    Shop queryShop(int shopId);

    /**
     * 更新库存
     * @param shopId
     * @param newCount
     */
    @Transactional
    @Modifying
    @Query(value = "update shop set flour_quantity = ?2 where shop_id = ?1 ", nativeQuery = true)
    void updateFlourFormulaCount (int shopId,int newCount);


    /**
     * 更新库存
     * @param shopId
     * @param newCount
     */
    @Transactional
    @Modifying
    @Query(value = "update shop set cheese_quantity = ?2 where shop_id = ?1 ", nativeQuery = true)
    void updateCheeseFormulaCount (int shopId,int newCount);


    /**
     * 更新库存
     * @param shopId
     * @param newCount
     */
    @Transactional
    @Modifying
    @Query(value = "update shop set egg_quantity = ?2 where shop_id = ?1 ", nativeQuery = true)
    void updateEggFormulaCount (int shopId,int newCount);


    /**
     * 更新库存
     * @param shopId
     * @param newCount
     */
    @Transactional
    @Modifying
    @Query(value = "update shop set vegetable_quantity = ?2 where shop_id = ?1 ", nativeQuery = true)
    void updateVegetableFormulaCount (int shopId,int newCount);


    /**
     * 更新库存
     * @param shopId
     * @param newCount
     */
    @Transactional
    @Modifying
    @Query(value = "update shop set meat_quantity = ?2 where shop_id = ?1 ", nativeQuery = true)
    void updateMeatFormulaCount (int shopId,int newCount);

    @Query(value = "select * from shop",nativeQuery = true)
    List<Shop> queryAllShop();
}
