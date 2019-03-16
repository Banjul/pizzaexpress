package com.springboot.pizzaexpress.dao;

/**
 * Created by sts on 2019/3/10.
 */


import com.springboot.pizzaexpress.bean.PizzaOrder;
import com.springboot.pizzaexpress.bean.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Repository
public interface PurchaseDao extends JpaRepository<Purchase,String> {

    /**
     * 查询实时数据基表
     * @param shop_id
     * @param formula
     * @return
     */
    @Query(value = "select * from purchase where shop_id = ?1 and purchase_formula =?2",nativeQuery = true)
    List<Purchase> queryPurchaseByFormula(int shop_id,String formula);

    @Transactional
    @Modifying
    @Query(value = "insert into purchase(shop_id,purchase_formula,purchase_time,purchase_count,purchase_manufacture) values(?1,?2,?3,?4,?5)",nativeQuery = true)
    int insertPurchase(int shopId,String formulaName,String purchaseTime,int purchaseCount,String purchaseManufacture);


}
