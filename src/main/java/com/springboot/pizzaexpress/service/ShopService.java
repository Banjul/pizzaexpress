package com.springboot.pizzaexpress.service;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.bean.Shop;

import java.util.List;
public interface ShopService {
    Shop adminLogin(String adminAccount, String adminPassword);
}
