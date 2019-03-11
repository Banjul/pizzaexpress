package com.springboot.pizzaexpress.service;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.bean.Item;

import java.util.List;
public interface ItemService {
    public Item findByItemId(int itemId);
}
