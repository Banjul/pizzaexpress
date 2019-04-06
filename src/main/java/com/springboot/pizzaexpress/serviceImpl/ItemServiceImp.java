package com.springboot.pizzaexpress.serviceImpl;

/**
 * Created by sts on 2019/3/2.
 */
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.springboot.pizzaexpress.bean.Formula;
import com.springboot.pizzaexpress.bean.Item;
import com.springboot.pizzaexpress.dao.FormulaDao;
import com.springboot.pizzaexpress.dao.ItemDao;
import com.springboot.pizzaexpress.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImp implements ItemService {
    @Autowired
    private ItemDao itemDao;

    @Autowired
    private FormulaDao formulaDao;

    @Override
    public String getAllItems() {
        JSONArray itemArray = new JSONArray();
        JSONObject itemData = new JSONObject();
        JSONObject dataJson = new JSONObject();

        int index = 0;
        List<Item> items = itemDao.getAllItems();
        if (items.size() > 0) {
            for (Item item : items) {
                if (index % 2 == 0) {
                    JSONObject itemJSON = new JSONObject();
//                    itemJSON.put("pizzaID",item.getItem_id());
                    itemJSON.put("pizzaName", item.getItemName());
                    itemJSON.put("description", item.getDescription());
                    itemJSON.put("picURL", item.getPicUrl());
                    itemJSON.put("pizzaStatus", item.getState());
                    int formulaId = item.getFormulaId();
                    Formula formula = formulaDao.queryFormulaById(formulaId);
                    String formulaString = "";
                    if (formula.getFlourQuantity() > 0)
                        formulaString += "面粉; ";
                    if (formula.getEggQuantity() > 0)
                        formulaString += "鸡蛋; ";
                    if (formula.getCheeseQuantity() > 0)
                        formulaString += "芝士; ";
                    if (formula.getVegetableQuantity() > 0)
                        formulaString += "蔬菜; ";
                    if (formula.getMeatQuantity() > 0)
                        formulaString += "肉; ";
                    itemJSON.put("formula", formulaString);
                    itemJSON.put("flour", formula.getFlourQuantity());
                    itemJSON.put("egg", formula.getEggQuantity());
                    itemJSON.put("cheese", formula.getCheeseQuantity());
                    itemJSON.put("vegetable", formula.getVegetableQuantity());
                    itemJSON.put("meat", formula.getMeatQuantity());
                    itemJSON.put("price9", item.getPrice());

                    itemArray.add(itemJSON);
                } else {
                    int tmp = (index - 1) / 2;
                    int newItemId = item.getItemId() / 2;
                    JSONObject fallItemJSON = itemArray.getJSONObject(tmp);
                    fallItemJSON.put("price12", item.getPrice());
                    fallItemJSON.put("pizzaID", newItemId);
                    itemArray.remove(tmp);
                    itemArray.add(fallItemJSON);
                }
                index++;
            }
        }
        itemData.put("count", items.size());
        itemData.put("data", itemArray);
        dataJson.put("itemData", itemData);
        return dataJson.toJSONString();
    }

    @Override
    public String updateItems(int pizzaId, String pizzaName, String description, String pizzaStatus, double price9, double price12, String picURL) {
        JSONArray itemArray = new JSONArray();
        JSONObject itemData = new JSONObject();
        JSONObject dataJson = new JSONObject();

        int pizza9Id = pizzaId * 2 - 1;
        int pizza12Id = pizzaId * 2;
        itemDao.updateItems(pizza9Id, pizzaName, description, pizzaStatus, price9, picURL);
        itemDao.updateItems(pizza12Id, pizzaName, description, pizzaStatus, price12, picURL);
        Item item9 = itemDao.queryItemByItemId(pizza9Id);
        Item item12 = itemDao.queryItemByItemId(pizza12Id);
        JSONObject itemJSON = new JSONObject();
        itemJSON.put("pizzaID", pizzaId);
        itemJSON.put("pizzaName", item9.getItemName());
        itemJSON.put("description", item9.getDescription());
        itemJSON.put("pizzaStatus", item9.getState());
        itemJSON.put("price9", item9.getPrice());
        itemJSON.put("price12", item12.getPrice());
        itemJSON.put("picURL", item9.getPicUrl());

        itemArray.add(itemJSON);

        itemData.put("data", itemArray);
        dataJson.put("itemData", itemData);
        return dataJson.toJSONString();
    }

    @Override
    public String insertItemAndFormula(String pizzaName, String description, double flour, double egg, double cheese, double vegetable, double meat, String pizzaStatus, String picURL, double price9, double price12) {
        JSONObject dataJSON = new JSONObject();

        formulaDao.insertFormula(flour, egg, cheese, vegetable, meat);
        int itemFormula9 = formulaDao.queryLastId();
        formulaDao.insertFormula(flour * 1.5, egg * 1.5, cheese * 1.5, vegetable * 1.5, meat * 1.5);
        int itemFormula12 = formulaDao.queryLastId();
        int pizzaSize9 = 9;
        int pizzaSize12 = 12;
        int result1 = itemDao.insertItems(pizzaName, itemFormula9, price9, picURL, pizzaSize9, description, pizzaStatus);
        int result2 = itemDao.insertItems(pizzaName, itemFormula12, price12, picURL, pizzaSize12, description, pizzaStatus);

        if (result1 == 1 && result2 == 1) {
            dataJSON.put("status", 200);
        } else dataJSON.put("status", 500);
        return dataJSON.toJSONString();

    }

    @Override
    public String getItemByName(String itemName) {
        JSONArray itemArray = new JSONArray();
        JSONObject itemData = new JSONObject();
        JSONObject dataJson = new JSONObject();
        List<Item> items = itemDao.queryItemByName(itemName);
        if (items.size() > 0) {
            Item item = items.get(0);
            Item item1 = items.get(1);
            JSONObject itemJSON = new JSONObject();
            itemJSON.put("pizzaName", item.getItemName());
            itemJSON.put("description", item.getDescription());
            itemJSON.put("picURL", item.getPicUrl());
            itemJSON.put("pizzaStatus", item.getState());
            int formulaId = item.getFormulaId();
            Formula formula = formulaDao.queryFormulaById(formulaId);
            String formulaString = "";
            if (formula.getFlourQuantity() > 0)
                formulaString += "面粉; ";
            if (formula.getEggQuantity() > 0)
                formulaString += "鸡蛋; ";
            if (formula.getCheeseQuantity() > 0)
                formulaString += "芝士; ";
            if (formula.getVegetableQuantity() > 0)
                formulaString += "蔬菜; ";
            if (formula.getMeatQuantity() > 0)
                formulaString += "肉; ";
            itemJSON.put("formula", formulaString);
            itemJSON.put("price9", item.getPrice());
            itemJSON.put("price12", item1.getPrice());
            itemJSON.put("pizzaID", item1.getItemId() / 2);


            itemArray.add(itemJSON);

        }
        itemData.put("data", itemArray);
        dataJson.put("itemData", itemData);
        return dataJson.toJSONString();
    }

    @Override
    public String getItemById(int itemId) {
        JSONArray itemArray = new JSONArray();
        JSONObject itemData = new JSONObject();
        JSONObject dataJson = new JSONObject();

        int itemId9 = itemId * 2 - 1;
        int itemId12 = itemId * 2;
        Item item = itemDao.queryItemByItemId(itemId9);
        Item item1 = itemDao.queryItemByItemId(itemId12);

        JSONObject itemJSON = new JSONObject();
        itemJSON.put("pizzaName", item.getItemName());
        itemJSON.put("description", item.getDescription());
        itemJSON.put("picURL", item.getPicUrl());
        itemJSON.put("pizzaStatus", item.getState());
        int formulaId = item.getFormulaId();
        Formula formula = formulaDao.queryFormulaById(formulaId);
        String formulaString = "";
        if (formula.getFlourQuantity() > 0)
            formulaString += "面粉; ";
        if (formula.getEggQuantity() > 0)
            formulaString += "鸡蛋; ";
        if (formula.getCheeseQuantity() > 0)
            formulaString += "芝士; ";
        if (formula.getVegetableQuantity() > 0)
            formulaString += "蔬菜; ";
        if (formula.getMeatQuantity() > 0)
            formulaString += "肉; ";
        itemJSON.put("formula", formulaString);
        itemJSON.put("price9", item.getPrice());
        itemJSON.put("price12", item1.getPrice());
        itemJSON.put("pizzaID", itemId);

        itemArray.add(itemJSON);

        itemData.put("data", itemArray);
        dataJson.put("itemData", itemData);
        return dataJson.toJSONString();
    }

    @Override
    public Item findByItemId(int itemId) {
        return itemDao.findByItemId(itemId);

    }

}
