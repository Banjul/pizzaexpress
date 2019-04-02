package com.springboot.pizzaexpress.serviceImpl;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.bean.Shop;
import com.springboot.pizzaexpress.dao.ShopDao;
import com.springboot.pizzaexpress.model.ShopModel;
import com.springboot.pizzaexpress.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImp implements ShopService{
    @Autowired
    private ShopDao shopDao;

    @Override
    public Shop findByShopId(int shopId) {
        return shopDao.findByShopId(shopId);
    }

    @Override
    public ShopModel findByShopIdModel(int shopId) {
        return shopDao.findByShopIdModel(shopId);
    }

    @Override
    public List<Shop> getShop() {
        List<Shop> list = shopDao.findAll();
        return list;
    }
}
