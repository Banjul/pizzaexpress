package com.springboot.pizzaexpress.service;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.bean.Shop;
import com.springboot.pizzaexpress.model.ShopModel;

import java.util.List;
public interface ShopService {

    public Shop adminLogin(String adminAccount, String adminPassword);

    public int getFormulaCount(int shopId,String formula);

    public void updateFormulaCount(int shopId,String formulaName, int purchaseCount);

    public void queryFormulaCountTimely(int shopId);

    public String getAllShops();

    public String getTop5Shops();

    public String deleteShop(int shopId);

    public String insertShop(String shopName,String posString,String picUrl,String account,
                             String password,String phone,String startTime,String  endTime);

    public String updateShop(int shopID, String shopName, String shopAddress, String shopPhone,
                             String shopStartTime, String shopEndTime);

    public String getFormulaByname(int shopId,String formulaName);

    public String getShopById(int shopId);

    public String getShopByIDorName(int shopId, String shopName);

    public String getAllFormulaByShop(int shopId);

    public String getAllShopsWithinDistance(int userId);


    public Shop findByShopId(int shopId);
    public List<Shop> getShop();
    public ShopModel findByShopIdModel(int shopId);

}
