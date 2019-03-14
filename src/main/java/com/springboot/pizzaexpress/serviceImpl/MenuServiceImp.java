package com.springboot.pizzaexpress.serviceImpl;

/**
 * Created by sts on 2019/3/2.
 */
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
import com.springboot.pizzaexpress.bean.Item;
import com.springboot.pizzaexpress.bean.Menu;
import com.springboot.pizzaexpress.dao.MenuDao;
import com.springboot.pizzaexpress.dao.ItemDao;
import com.springboot.pizzaexpress.service.MenuService;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Service
public class MenuServiceImp implements MenuService{
    @Autowired
    private MenuDao menuDao;

    @Autowired
    private ItemDao itemDao;
    @Override
    public String getMenuByShopId(int shopId) {
        JSONArray menuArray = new JSONArray();
        JSONObject menuData = new JSONObject();
        JSONObject dataJson = new JSONObject();

        Menu menu = menuDao.queryMenuByShopId(shopId);
        if (menu != null) {
            String menuItems = menu.getItems();
            JSONArray menuOldArray = JSONArray.fromObject(menuItems);
            for(int i =0; i < menuOldArray.size();i++) {
                JSONObject itemJson = menuOldArray.getJSONObject(i);
                String itemIdString = itemJson.get("itemId").toString();
                String itemCountString = itemJson.get("count").toString();
                int itemCount = Integer.parseInt(itemCountString);
                int itemId = Integer.parseInt(itemIdString);

                Item oneItem = itemDao.queryItemByItemId(itemId);
                JSONObject menuItemJson = new JSONObject();
                menuItemJson.put("itemName",oneItem.getItemName());
                menuItemJson.put("price",oneItem.getPrice());
                menuItemJson.put("picUrl",oneItem.getPicUrl());
                menuItemJson.put("state",oneItem.getState());
                menuItemJson.put("size",oneItem.getPizzaSize());
                menuItemJson.put("description",oneItem.getDescription());
                menuItemJson.put("count",itemCount);

                menuArray.add(menuItemJson);
            }
        }
        menuData.put("data",menuArray);
        dataJson.put("itemData",menuData);
        return dataJson.toString();
    }
}
