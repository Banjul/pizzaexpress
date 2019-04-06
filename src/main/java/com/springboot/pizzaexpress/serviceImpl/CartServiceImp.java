package com.springboot.pizzaexpress.serviceImpl;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.bean.Cart;
import com.springboot.pizzaexpress.dao.CartDao;
import com.springboot.pizzaexpress.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImp implements CartService{
    @Autowired
    private CartDao cartDao;

    @Override
    public int insertToCart(int userId, int shopId, String items) {
        return cartDao.insertToCart(userId,shopId,items);
    }

    @Override
    public Cart findCartItems(int shopId, int userId) {
        return cartDao.findCartItems(shopId,userId);
    }

    @Override
    public int modifyCart(int userId, int shopId, String items) {
        return cartDao.modifyCart(userId,shopId,items);
    }
}
