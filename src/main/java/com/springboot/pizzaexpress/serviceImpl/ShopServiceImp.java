package com.springboot.pizzaexpress.serviceImpl;

/**
 * Created by sts on 2019/3/2.
 */
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.springboot.pizzaexpress.bean.Shop;
//import com.springboot.pizzaexpress.dao.NoticeDao;
import com.springboot.pizzaexpress.dao.ShopDao;
import com.springboot.pizzaexpress.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ShopServiceImp implements ShopService{

    @Autowired
    private ShopDao shopDao;

//    @Autowired
//    private NoticeDao noticeDao;

    @Override
    public Shop adminLogin(String adminAccount, String adminPassword) {
        return shopDao.getAdminByAccountAndPassword(adminAccount,adminPassword);
    }

    @Override
    public int getFormulaCount(int shopId, String formula) {

        Shop shop = shopDao.queryShop(shopId);
        if (formula.equals("flour"))
            return shop.getFlourQuantity();
        else if (formula.equals("egg"))
            return shop.getEggQuantity();
        else if (formula.equals("cheese"))
            return shop.getCheeseQuantity();
        else if (formula.equals("vegetable"))
            return shop.getVegetableQuantity();
        else
            return shop.getMeatQuantity();
    }

    @Override
    public void updateFormulaCount(int shopId, String formulaName, int purchaseCount) {
        Shop shop = shopDao.queryShop(shopId);
        int formulaOldCount;
        int formulaNewCount;
        if (formulaName.equals("flour")){
            formulaOldCount = shop.getFlourQuantity();
            formulaNewCount = formulaOldCount + purchaseCount;
            shopDao.updateFlourFormulaCount(shopId,formulaNewCount);
        }
        else if (formulaName.equals("cheese")) {
            formulaOldCount = shop.getCheeseQuantity();
            formulaNewCount = formulaOldCount + purchaseCount;
            shopDao.updateCheeseFormulaCount(shopId,formulaNewCount);
        }
        else if (formulaName.equals("egg")) {
            formulaOldCount = shop.getEggQuantity();
            formulaNewCount = formulaOldCount + purchaseCount;
            shopDao.updateEggFormulaCount(shopId,formulaNewCount);
        }
        else if (formulaName.equals("vegetable")) {
            formulaOldCount = shop.getVegetableQuantity();
            formulaNewCount = formulaOldCount + purchaseCount;
            shopDao.updateVegetableFormulaCount(shopId,formulaNewCount);
        }
        else {
            formulaOldCount = shop.getMeatQuantity();
            formulaNewCount = formulaOldCount + purchaseCount;
            shopDao.updateMeatFormulaCount(shopId,formulaNewCount);
        }
    }

//    @Override
//    public void queryFormulaCountTimely(int shopId) {
//       Shop shop = shopDao.queryShop(shopId);
//       int flourCount = shop.getFlour_quantity();
//       int eggCount = shop.getEgg_quantity();
//       int cheeseCount = shop.getCheese_quantity();
//       int vegetableCount = shop.getVegetable_quantity();
//       int meatCount = shop.getMeat_quantity();
//       if (flourCount <= 100)
//       {
//           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//           String noticeTime = sdf.format(new Date());
//           noticeDao.insertNotice("面粉补货通知","面粉库存少于100g请及时购进","未读",noticeTime,"warning",shopId);
//       }
//       if (eggCount <=  100)
//       {
//           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//           String noticeTime = sdf.format(new Date());
//           noticeDao.insertNotice("鸡蛋补货通知","鸡蛋库存少于100g请及时购进","未读",noticeTime,"warning",shopId);
//       }
//        if (cheeseCount <=  100)
//        {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String noticeTime = sdf.format(new Date());
//            noticeDao.insertNotice("芝士补货通知","芝士库存少于100g请及时购进","未读",noticeTime,"warning",shopId);
//        }
//        if (vegetableCount <=  100)
//        {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String noticeTime = sdf.format(new Date());
//            noticeDao.insertNotice("蔬菜补货通知","蔬菜库存少于100g请及时购进","未读",noticeTime,"warning",shopId);
//        }
//        if (meatCount <=  100)
//        {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String noticeTime = sdf.format(new Date());
//            noticeDao.insertNotice("肉补货通知","肉库存少于100g请及时购进","未读",noticeTime,"warning",shopId);
//        }
//    }

    @Override
    public String getAllShops() {
        JSONArray shopArray = new JSONArray();
        JSONObject shopData = new JSONObject();
        JSONObject dataJson = new JSONObject();

        List<Shop> shops = shopDao.queryAllShop();
        if (shops.size()>0) {
            for (Shop shop : shops) {
                JSONObject shopJSON = new JSONObject();
                shopJSON.put("shopName",shop.getShopName());
                shopJSON.put("shopId",shop.getShopId());
                shopJSON.put("address",shop.getPosString());
                shopJSON.put("phone",shop.getPhone());
                shopJSON.put("picUrl",shop.getPicUrl());
                shopJSON.put("salesVolume",shop.getSalesVolume());
                shopJSON.put("startTime",shop.getStartTime().toString());
                shopJSON.put("endTime",shop.getEndTime().toString());
            }
        }
        shopData.put("count",shops.size());
        shopData.put("data",shopArray);
        dataJson.put("shopData",shopData);
        return dataJson.toJSONString();
    }


}
