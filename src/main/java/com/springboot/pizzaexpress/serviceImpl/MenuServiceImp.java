package com.springboot.pizzaexpress.serviceImpl;

/**
 * Created by sts on 2019/3/2.
 */

import com.springboot.pizzaexpress.bean.Item;
import com.springboot.pizzaexpress.bean.Menu;
import com.springboot.pizzaexpress.bean.Formula;
import com.springboot.pizzaexpress.bean.Shop;
import com.springboot.pizzaexpress.dao.FormulaDao;
import com.springboot.pizzaexpress.dao.MenuDao;
import com.springboot.pizzaexpress.dao.ItemDao;
import com.springboot.pizzaexpress.dao.ShopDao;
import com.springboot.pizzaexpress.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;


@Service
public class MenuServiceImp implements MenuService {
    @Autowired
    private MenuDao menuDao;

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private FormulaDao formulaDao;

    @Override
    public String getMenuByShopId(int shopId) {
        JSONArray menuArray = new JSONArray();
        JSONObject menuData = new JSONObject();
        JSONObject dataJson = new JSONObject();

        Menu menu = menuDao.queryMenuByShopId(shopId);
        if (menu != null) {
            String menuItems = menu.getItems();
            JSONArray menuOldArray = JSONArray.fromObject(menuItems);
            for (int i = 0; i < menuOldArray.size(); i++) {
                JSONObject itemJson = menuOldArray.getJSONObject(i);
                String itemIdString = itemJson.get("itemId").toString();
                String itemCountString = itemJson.get("count").toString();
                int itemCount = Integer.parseInt(itemCountString);
                int itemId = Integer.parseInt(itemIdString);

                Item oneItem = itemDao.queryItemByItemId(itemId);
                JSONObject menuItemJson = new JSONObject();
                menuItemJson.put("itemId",oneItem.getItemId());
                menuItemJson.put("itemName", oneItem.getItemName());
                menuItemJson.put("price", oneItem.getPrice());
                menuItemJson.put("picUrl", oneItem.getPicUrl());
                menuItemJson.put("state", oneItem.getState());
                menuItemJson.put("size", oneItem.getPizzaSize());
                menuItemJson.put("description", oneItem.getDescription());
                menuItemJson.put("count", itemCount);

                menuArray.add(menuItemJson);
            }
        }
        menuData.put("data", menuArray);
        dataJson.put("itemData", menuData);
        return dataJson.toString();
    }

    @Override
    public String updateMenuByShopId(int shopId, int itemId, int itemCount) {
        JSONObject dataJSON = new JSONObject();
        Menu menu = menuDao.queryMenuByShopId(shopId);
        String menuItems = menu.getItems();
        JSONArray menuOldArray = JSONArray.fromObject(menuItems);  // 这个商店所有的成品库存

        //更改原料库存
        Item item = itemDao.queryItemByItemId(itemId);
        int formulaId = item.getFormulaId();
        Formula formula = formulaDao.queryFormulaById(formulaId);
        //所有原料消耗
        int flourCost = formula.getFlourQuantity() * itemCount;
        int eggCost = formula.getEggQuantity() * itemCount;
        int cheeseCost = formula.getCheeseQuantity() * itemCount;
        int vegetableCost = formula.getVegetableQuantity() * itemCount;
        int meatCost = formula.getMeatQuantity() * itemCount;

        //判断原料库存是否充足
        Shop shop = shopDao.queryShop(shopId);
        int restFlourCount = shop.getFlourQuantity() - flourCost;
        int restEggCount = shop.getEggQuantity() - eggCost;
        int restCheeseCount = shop.getCheeseQuantity() - cheeseCost;
        int restVegetableCount = shop.getCheeseQuantity() - vegetableCost;
        int restMeatCount = shop.getMeatQuantity() - meatCost;
        if (restFlourCount < 0 || restEggCount < 0 || restCheeseCount < 0 || restVegetableCount < 0 ||restMeatCount < 0 ) {
            dataJSON.put("status","原料不足");
            return dataJSON.toString();
        }

        //更改shop数据库各原料库存
        int changeFormulaResult = shopDao.updateAllFormulaCount(shopId,restFlourCount,restEggCount,restCheeseCount,restVegetableCount,restMeatCount);

        //更改menu的item字段
        for (int i = 0; i < menuOldArray.size(); i++) {
            JSONObject itemJson = menuOldArray.getJSONObject(i); // 每个成品pizza
            String itemidstring = itemJson.get("itemId").toString();
            int eachItemId = Integer.parseInt(itemidstring);  // 其对应的id
            int eachItemCount = Integer.parseInt(itemJson.get("count").toString());
            //找到要更改的itemId
            if (eachItemId == itemId) {
                //更改count
//                String itemCountString = itemJson.get("count").toString();
//                int oldCount = Integer.parseInt(itemCountString);
//                int newCount = oldCount + itemCount;
//                    String newcount = newCount +"";
//                itemJson.put("count", itemCount); //更改count
                menuOldArray.getJSONObject(i).put("count",eachItemCount + itemCount); // 新增
//                itemJson.put("count", eachItemCount + itemCount);  // 新增
//                JSONObject newItemJson = itemJson;
//                menuOldArray.remove(itemJson);
//                menuOldArray.add(newItemJson);
                break;
            }
        }
        //更改数据库menu的item字段
        String newItems = menuOldArray.toString();
        int changeMenuResult = menuDao.updateMenuByShopId(shopId, newItems);

        if (changeFormulaResult == 1 && changeMenuResult == 1) dataJSON.put("status", 200);
        else dataJSON.put("status", 500);
        return dataJSON.toString();
    }

    @Override
    public String getMenuByItemName(int shopId, String itemName) {
        JSONArray menuArray = new JSONArray();
        JSONObject menuData = new JSONObject();
        JSONObject dataJson = new JSONObject();
        Menu menu = menuDao.queryMenuByShopId(shopId);
        List<Item> item = itemDao.queryItemByName(itemName);
        if ((menu != null) && (item.size() > 0)) {
            Item item9 = item.get(0);
            Item item12 = item.get(1);
            String menuItems = menu.getItems();
            JSONArray menuOldArray = JSONArray.fromObject(menuItems);
            for (int i = 0; i < menuOldArray.size(); i++) {
                JSONObject itemJson = menuOldArray.getJSONObject(i);
                String itemIdString = itemJson.get("itemId").toString();
                String itemCountString = itemJson.get("count").toString();
                int itemCount = Integer.parseInt(itemCountString);
                int itemId = Integer.parseInt(itemIdString);
                JSONObject menuItemJson = new JSONObject();
                Item oneItem = new Item();
                if(itemId == item9.getItemId()){
                    oneItem = item9;
                }
                else if(itemId == item12.getItemId()){
                    oneItem = item12;
                }
                else{
                    continue;
                }
                menuItemJson.put("itemId",oneItem.getItemId());
                menuItemJson.put("itemName", oneItem.getItemName());
                menuItemJson.put("price", oneItem.getPrice());
                menuItemJson.put("picUrl", oneItem.getPicUrl());
                menuItemJson.put("state", oneItem.getState());
                menuItemJson.put("size", oneItem.getPizzaSize());
                menuItemJson.put("description", oneItem.getDescription());
                menuItemJson.put("count", itemCount);

                menuArray.add(menuItemJson);
            }
        }
        menuData.put("data", menuArray);
        dataJson.put("itemData", menuData);
        return dataJson.toString();
    }

    @Override
    public Menu findByShopId(int shopId){
        return menuDao.findByShopId(shopId);
    }

}
