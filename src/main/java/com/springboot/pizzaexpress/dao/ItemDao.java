package com.springboot.pizzaexpress.dao;

/**
 * Created by sts on 2019/3/1.
 */

import com.springboot.pizzaexpress.bean.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface ItemDao extends JpaRepository<Item,String> {

    @Query(value = "select * from item where item_id = ?1",nativeQuery = true)
    Item findByItemId(int itemId);
}
