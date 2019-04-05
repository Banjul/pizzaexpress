package com.springboot.pizzaexpress.service;

/**
 * Created by sts on 2019/3/10.
 */

import com.springboot.pizzaexpress.bean.Purchase;

public interface PurchaseService {
    public String getPurchaseByFormula(int shopId,String formula);
}
