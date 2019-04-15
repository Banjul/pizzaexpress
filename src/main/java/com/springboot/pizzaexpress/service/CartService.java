package com.springboot.pizzaexpress.service;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.bean.Cart;

import java.util.List;
public interface CartService {

    public int insertToCart(int userId,int shopId,String items);

    public Cart findCartItems(int shopId,int userId);

    public int modifyCart(int userId,int shopId,String items);

    public int clearCart(int userId,int shopId);

    public List<Cart> findAllCart(int userId);

}
