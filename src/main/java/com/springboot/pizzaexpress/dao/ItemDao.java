package com.springboot.pizzaexpress.dao;

/**
 * Created by sts on 2019/3/1.
 */

import com.springboot.pizzaexpress.bean.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

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

}
