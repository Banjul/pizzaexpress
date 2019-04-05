package com.springboot.pizzaexpress.service;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.bean.Menu;
import java.util.List;

public interface MenuService {

    public String getMenuByShopId(int shopId);

    public String updateMenuByShopId(int shopId, int itemId,int itemCount);

    public String getMenuByItemName(int shopId, String itemName);
}
