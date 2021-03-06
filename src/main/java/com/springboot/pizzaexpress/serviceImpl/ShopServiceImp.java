package com.springboot.pizzaexpress.serviceImpl;

/**
 * Created by sts on 2019/3/2.
 */
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.springboot.pizzaexpress.bean.Notice;
import com.springboot.pizzaexpress.bean.Shop;
import com.springboot.pizzaexpress.bean.User;
import com.springboot.pizzaexpress.dao.NoticeDao;
import com.springboot.pizzaexpress.dao.ShopDao;
import com.springboot.pizzaexpress.dao.UserDao;
import com.springboot.pizzaexpress.model.ShopModel;
import com.springboot.pizzaexpress.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.pizzaexpress.util.LocationUtils;
import com.springboot.pizzaexpress.util.HttpClientUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.List;

@Service
public class ShopServiceImp implements ShopService{

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private NoticeDao noticeDao;

    @Autowired
    private UserDao userDao;

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
        switch (formulaName){
            case "面粉":
                formulaOldCount = shop.getFlourQuantity();
                formulaNewCount = formulaOldCount + purchaseCount;
                shopDao.updateFlourFormulaCount(shopId,formulaNewCount);
                break;
            case "芝士":
                formulaOldCount = shop.getCheeseQuantity();
                formulaNewCount = formulaOldCount + purchaseCount;
                shopDao.updateCheeseFormulaCount(shopId,formulaNewCount);
                break;
            case "鸡蛋":
                formulaOldCount = shop.getEggQuantity();
                formulaNewCount = formulaOldCount + purchaseCount;
                shopDao.updateEggFormulaCount(shopId,formulaNewCount);
                break;
            case "蔬菜":
                formulaOldCount = shop.getVegetableQuantity();
                formulaNewCount = formulaOldCount + purchaseCount;
                shopDao.updateVegetableFormulaCount(shopId,formulaNewCount);
                break;
            default:
                formulaOldCount = shop.getMeatQuantity();
                formulaNewCount = formulaOldCount + purchaseCount;
                shopDao.updateMeatFormulaCount(shopId,formulaNewCount);
        }
    }

    @Override
    public void queryFormulaCountTimely(int shopId) {
       Shop shop = shopDao.queryShop(shopId);
       int flourCount = shop.getFlourQuantity();
       int eggCount = shop.getEggQuantity();
       int cheeseCount = shop.getCheeseQuantity();
       int vegetableCount = shop.getVegetableQuantity();
       int meatCount = shop.getMeatQuantity();
       if (flourCount < 500) {
           List<Notice> notice= noticeDao.getNoticeByStatusContent(shopId,"未读", "面粉");
           if(notice.size() == 0){
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
               String noticeTime = sdf.format(new Date());
               noticeDao.insertNotice("面粉库存少于500g，请及时购进","Warning","面粉",shopId,"未读",noticeTime);
               System.err.println("新增通知:面粉不足");
           }
       }
       if (eggCount <  200) {
           List<Notice> notice= noticeDao.getNoticeByStatusContent(shopId,"未读", "鸡蛋");
           if(notice.size() == 0){
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
               String noticeTime = sdf.format(new Date());
               noticeDao.insertNotice("鸡蛋库存少于200g，请及时购进","Warning","鸡蛋",shopId,"未读",noticeTime);
               System.err.println("新增通知:鸡蛋不足");
           }
       }
        if (cheeseCount <  200) {
            List<Notice> notice= noticeDao.getNoticeByStatusContent(shopId,"未读", "芝士");
            if(notice.size() == 0){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String noticeTime = sdf.format(new Date());
                noticeDao.insertNotice("芝士库存少于200g，请及时购进","Warning","芝士",shopId,"未读",noticeTime);
                System.err.println("新增通知:芝士不足");
            }
        }
        if (vegetableCount <  200) {
            List<Notice> notice= noticeDao.getNoticeByStatusContent(shopId,"未读", "蔬菜");
            if(notice.size() == 0){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String noticeTime = sdf.format(new Date());
                noticeDao.insertNotice("蔬菜库存少于200g，请及时购进","Warning","蔬菜",shopId,"未读",noticeTime);
                System.err.println("新增通知:蔬菜不足");
            }
        }
        if (meatCount <  200) {
            List<Notice> notice= noticeDao.getNoticeByStatusContent(shopId,"未读", "肉类");
            if(notice.size() == 0){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String noticeTime = sdf.format(new Date());
                noticeDao.insertNotice("肉类库存少于200g，请及时购进","Warning","肉类",shopId,"未读",noticeTime);
                System.err.println("新增通知:肉类不足");
            }
        }
    }

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
                shopJSON.put("shopID",shop.getShopId());
                shopJSON.put("shopAddress",shop.getPosString());
                shopJSON.put("shopPhone",shop.getPhone());
                shopJSON.put("shopPicUrl",shop.getPicUrl());
                shopJSON.put("shopSalesVolume",shop.getSalesVolume());
                shopJSON.put("shopStartTime",shop.getStartTime());
                shopJSON.put("shopEndTime",shop.getEndTime());

                shopArray.add(shopJSON);
            }
        }
        shopData.put("count",shops.size());
        shopData.put("data",shopArray);
        dataJson.put("shopData",shopData);
        return dataJson.toJSONString();
    }

    @Override
    public String getTop5Shops() {
//        JSONArray shopArray = new JSONArray();
        JSONObject shopJSON = new JSONObject();
        JSONObject shopData = new JSONObject();
        JSONObject dataJson = new JSONObject();
        JSONArray nameArray = new JSONArray();
        JSONArray incomeArray = new JSONArray();

        List<Shop> shops = shopDao.getTop5shops();
        if (shops.size()>0) {
            for (int i=0; i<5; i++) {
                nameArray.add(i, shops.get(i).getShopName());
                incomeArray.add(i, shops.get(i).getSalesVolume());
            }
            shopJSON.put("shopName",nameArray);
            shopJSON.put("shopSalesVolume",incomeArray);
        }
        shopData.put("data",shopJSON);
        dataJson.put("shopData",shopData);
        return dataJson.toJSONString();
    }

    @Override
    public String deleteShop(int shopId) {
        JSONObject dataJSON = new JSONObject();
        int result = shopDao.deleteShop(shopId);
        if (result == 1) dataJSON.put("status",200);
        else dataJSON.put("status",500);
        return dataJSON.toJSONString();
    }

    @Override
    public String insertShop(String shopName,String posString, String picUrl, String account,
                             String password, String phone, String startTime, String endTime) {
        JSONObject dataJSON = new JSONObject();
        String getUrl = "https://restapi.amap.com/v3/geocode/geo?address=" + posString + "&output=JSON&key=ee4084f8d57d35442a70a1a2f77d8de8";

        net.sf.json.JSONObject addressResult = HttpClientUtil.doGetStr(getUrl);

        net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(addressResult.get("geocodes"));
        net.sf.json.JSONObject jsonObject = jsonArray.getJSONObject(0);
        String location = jsonObject.get("location").toString();

        String[] locationXY = location.split(",");
        double posX = Double.parseDouble(locationXY[0]);
        double posY = Double.parseDouble(locationXY[1]);

        int result = shopDao.insertShop(shopName, posX, posY, posString, picUrl,  account, password, phone, startTime,  endTime);
        if (result == 1) dataJSON.put("status",200);
        else dataJSON.put("status",500);

        return dataJSON.toJSONString();
    }

    @Override
    public String updateShop(int shopID, String shopName, String shopAddress, String shopPhone,
                              String shopStartTime, String shopEndTime){
        JSONObject dataJSON = new JSONObject();
        String getUrl = "https://restapi.amap.com/v3/geocode/geo?address=" + shopAddress + "&output=JSON&key=ee4084f8d57d35442a70a1a2f77d8de8";

        net.sf.json.JSONObject addressResult = HttpClientUtil.doGetStr(getUrl);

        net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(addressResult.get("geocodes"));
        net.sf.json.JSONObject jsonObject = jsonArray.getJSONObject(0);
        String location = jsonObject.get("location").toString();
        String[] locationXY = location.split(",");
        double posX = Double.parseDouble(locationXY[0]);
        double posY = Double.parseDouble(locationXY[1]);

        String shopPicUrl = "https://events.orlandojobs.com/wp-content/uploads/2018/09/h.jpg";
        int res = shopDao.updateShop(shopID, shopName, posX, posY, shopAddress, shopPicUrl, shopPhone, shopStartTime, shopEndTime);
        if (res == 1) dataJSON.put("status", 200);
        else dataJSON.put("status", 500);
        return dataJSON.toJSONString();
    }

    @Override
    public String getFormulaByname(int shopId, String formulaName) {
        JSONArray formulaArray = new JSONArray();
        JSONObject formulaData = new JSONObject();
        JSONObject dataJson = new JSONObject();

        Shop shop = shopDao.queryShop(shopId);
        int formulaCount = 0;
        int formulaId = 0;
        switch (formulaName) {
            case "面粉":
                formulaId = 1;
                formulaCount = shop.getFlourQuantity();
                break;
            case "鸡蛋":
                formulaId = 2;
                formulaCount = shop.getEggQuantity();
                break;
            case "芝士":
                formulaId = 3;
                formulaCount = shop.getCheeseQuantity();
                break;
            case "蔬菜":
                formulaId = 4;
                formulaCount = shop.getVegetableQuantity();
                break;
            case "肉":
                formulaId = 5;
                formulaCount = shop.getMeatQuantity();
                break;
            default:
                formulaData.put("data",formulaArray);
                dataJson.put("purchaseData",formulaData);
                return dataJson.toJSONString();
        }
        JSONObject shopJSON = new JSONObject();
        shopJSON.put("formulaId",formulaId);
        shopJSON.put("formulaName",formulaName);
        shopJSON.put("formulaCount",formulaCount);

        formulaArray.add(shopJSON);
        formulaData.put("data",formulaArray);
        dataJson.put("purchaseData",formulaData);
        return dataJson.toJSONString();
    }

    @Override
    public String getShopById(int shopId) {
        JSONArray shopArray = new JSONArray();
        JSONObject shopData = new JSONObject();
        JSONObject dataJson = new JSONObject();

        Shop shop = shopDao.queryShop(shopId);
        JSONObject shopJSON = new JSONObject();

        shopJSON.put("shopName",shop.getShopName());
        shopJSON.put("shopID",shop.getShopId());
        shopJSON.put("shopPhone",shop.getPhone());
        shopJSON.put("shopAddress",shop.getPosString());
        shopJSON.put("shopSalesVolume",shop.getSalesVolume());

        shopJSON.put("shopStartTime",shop.getStartTime());
        shopJSON.put("shopEndTime",shop.getEndTime());
        shopArray.add(shopJSON);
        shopData.put("data",shopArray);
        dataJson.put("shopData",shopData);
        return dataJson.toJSONString();

    }

    @Override
    public String getShopByIDorName(int shopId, String shopName) {
        JSONArray shopArray = new JSONArray();
        JSONObject shopData = new JSONObject();
        JSONObject dataJson = new JSONObject();
        List<Shop> shops = new ArrayList<Shop>();
        if(shopId != -1 && shopName.equals("-1")){
            // 根据id查
            return getShopById(shopId);
        }
        else{
            // 根据名字查
            shops = shopDao.queryShopByName(shopName);
            if(shops.size() > 0){
                for(Shop shop : shops){
                    JSONObject shopJSON = new JSONObject();
                    shopJSON.put("shopName",shop.getShopName());
                    shopJSON.put("shopID",shop.getShopId());
                    shopJSON.put("shopPhone",shop.getPhone());
                    shopJSON.put("shopAddress",shop.getPosString());
                    shopJSON.put("shopSalesVolume",shop.getSalesVolume());

                    shopJSON.put("shopStartTime",shop.getStartTime());
                    shopJSON.put("shopEndTime",shop.getEndTime());
                    shopArray.add(shopJSON);
                }
            }
            shopData.put("data",shopArray);
            dataJson.put("shopData",shopData);
            return dataJson.toJSONString();
        }
    }

    @Override
    public String getAllFormulaByShop(int shopId) {
        JSONArray shopArray = new JSONArray();
        JSONObject shopData = new JSONObject();
        JSONObject dataJson = new JSONObject();
        queryFormulaCountTimely(shopId);
        Shop shop = shopDao.queryShop(shopId);
        String[] formula = {"面粉","鸡蛋","芝士","蔬菜","肉类"};
        int[] count = {shop.getFlourQuantity(),shop.getEggQuantity(),shop.getCheeseQuantity(),shop.getVegetableQuantity(),shop.getMeatQuantity()};
        for (int i = 0; i< 5;i++) {
            JSONObject shopJSON = new JSONObject();
            int index = i+1;
            shopJSON.put("formulaId",index);
            shopJSON.put("formulaName",formula[i]);
            shopJSON.put("formulaCount",count[i]);

            shopArray.add(shopJSON);
        }
        shopData.put("data",shopArray);
        dataJson.put("shopData",shopData);
        return dataJson.toJSONString();
    }

    @Override
    public String getAllShopsWithinDistance(int userId) {
        JSONArray shopArray = new JSONArray();
        JSONObject shopData = new JSONObject();
        JSONObject dataJSON = new JSONObject();

        //获取用户坐标
        User user = userDao.queryUserByUserId(userId);
        String userpos = user.getUserLocation();
        String[] userPos = userpos.split(",");
        double userX = Double.parseDouble(userPos[0]);
        double userY = Double.parseDouble(userPos[1]);

        List<Shop> shops = shopDao.queryAllShop();
        if (shops.size()>0) {
            double minDistance = 0;
            for (Shop shop : shops) {
                double shopX = shop.getPosX();
                double shopY = shop.getPosY();
                double distance = LocationUtils.getDistance(userX,userY,shopX,shopY);
                if (distance <= 20000) {
                    if (minDistance == 0) minDistance = distance;
                    JSONObject shopJSON = new JSONObject();
                    shopJSON.put("shopId",shop.getShopId());
                    shopJSON.put("shopName",shop.getShopName());
                    shopJSON.put("shopAddress",shop.getPosString());
                    shopJSON.put("shopPhone",shop.getPhone());
                    shopJSON.put("shopStartTime",shop.getStartTime());
                    shopJSON.put("shopEndTime",shop.getEndTime());
                    shopJSON.put("shopPicUrl",shop.getPicUrl());
//                    shopJSON.put("shopMenu")
                    if (distance <= minDistance) {
                        JSONObject tempJSON = shopArray.getJSONObject(0);
                        shopArray.remove(0);
                        shopArray.add(0,shopJSON);
                        shopArray.add(tempJSON);
                    }
                    else shopArray.add(shopJSON);
                }
            }
        }
        shopData.put("data",shopArray);
        dataJSON.put("shopsWithinDistance",shopData);
        return dataJSON.toJSONString();
    }

    @Override
    public Shop findByShopId(int shopId) {
        return shopDao.findByShopId(shopId);
    }

    @Override
    public ShopModel findByShopIdModel(int shopId) {
        return shopDao.findByShopIdModel(shopId);
    }

    @Override
    public String getAllSalesVolume() {
        int salesVolume = shopDao.getAllSalesVolume();
        int userCount = userDao.getUserCount();
        int shopCount = shopDao.getShopCount();

        JSONObject dataJSON = new JSONObject();
        dataJSON.put("salesVolume",salesVolume);
        dataJSON.put("userCount",userCount);
        dataJSON.put("shopCount",shopCount);
        return dataJSON.toJSONString();
    }

    @Override
    public List<Shop> getShop() {
        List<Shop> list = shopDao.findAll();
        return list;
    }

}
