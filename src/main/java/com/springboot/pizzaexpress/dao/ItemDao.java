package com.springboot.pizzaexpress.dao;

/**
 * Created by sts on 2019/3/1.
 */

import com.springboot.pizzaexpress.bean.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Repository
public interface ItemDao extends JpaRepository<Item,String> {

    /**
     * 查询所有pizza种类
     * @return
     */
    @Query(value = "select * from item",nativeQuery = true)
    List<Item> getAllItems();

    @Query(value = "select * from item where item_id = ?1",nativeQuery = true)
    Item queryItemByItemId(int itemId);

    @Transactional
    @Modifying
    @Query(value = "update item set item_name = ?2, price = ?5,pic_url = ?6,description =?3,state = ?4 where item_id =?1",nativeQuery = true)
    void updateItems(int pizzaId, String pizzaName,String description,String pizzaStatus,double price, String picURL);

    @Transactional
    @Modifying
    @Query(value = "insert into item(item_name,formula_id,price,pic_url,pizza_size,description,state) values (?1,?2,?3,?4,?5,?6,?7)",nativeQuery = true)
    int insertItems(String pizzaName,int itemFormula, double price, String picURL, int pizzaSize,String description,String pizzaStatus);

    @Query(value = "select * from item where item_name = ?1",nativeQuery = true)
    List<Item> queryItemByName(String itemName);
}
