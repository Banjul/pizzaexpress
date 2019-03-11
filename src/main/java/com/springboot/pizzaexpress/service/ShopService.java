package com.springboot.pizzaexpress.service;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.bean.Shop;

import java.util.List;
public interface ShopService {
    public Shop adminLogin(String adminAccount, String adminPassword);

    public int getFormulaCount(int shopId,String formula);

    public void updateFormulaCount(int shopId,String formulaName, int purchaseCount);

    public void queryFormulaCountTimely(int shopId);
}
