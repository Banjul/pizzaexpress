package com.springboot.pizzaexpress.service;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.bean.Shop;

import java.util.List;
public interface ShopService {

    public Shop findByShopId(int shopId);
}
