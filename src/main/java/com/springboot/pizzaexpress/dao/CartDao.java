package com.springboot.pizzaexpress.dao;

/**
 * Created by sts on 2019/3/1.
 */

import com.springboot.pizzaexpress.bean.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Repository
public interface CartDao extends JpaRepository<Cart,String> {

    @Modifying
    @Transactional
    @Query(value = "insert into cart(user_id,shop_id,items) values(?1,?2,?3)",nativeQuery = true)
    int insertToCart(int userId,int shopId,String items);

    @Query(value = "select * from cart where shop_id=?1 and user_id=?2",nativeQuery = true)
    Cart findCartItems(int shopId,int userId);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update cart set items =?3 where user_id=?1 and shop_id =?2",nativeQuery = true)
    int modifyCart(int userId,int shopId,String items);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "delete from cart where user_id = ?1 and shop_id = ?2",nativeQuery = true)
    int clearCart(int userId,int shopId);

    @Modifying
    @Transactional
    @Query(value = "select * from cart where user_id=?1",nativeQuery = true)
    List<Cart> findAllCart(int userId);
}
