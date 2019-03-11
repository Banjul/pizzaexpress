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
public class ItemServiceImp implements ItemService{
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
        if (items.size() > 0)
        {
            for (Item item: items) {
                if (index%2 ==0) {
                    JSONObject itemJSON = new JSONObject();
                    itemJSON.put("pizzaID",item.getItem_id());
                    itemJSON.put("pizzaName",item.getItem_name());
                    itemJSON.put("description",item.getDescription());
                    itemJSON.put("picURL",item.getPic_url());
                    int formulaId = item.getFormula_id();
                    Formula formula = formulaDao.queryFormulaById(formulaId);
                    String formulaString = "";
                    if (formula.getFlour_quantity()>0)
                        formulaString += "flour; ";
                    if (formula.getEgg_quantity()>0)
                        formulaString +="egg; ";
                    if (formula.getCheese_quantity()>0)
                        formulaString +="Cheese; ";
                    if (formula.getVegetable_quantity()>0)
                        formulaString +="Vegetable; ";
                    if (formula.getMeat_quantity()>0)
                        formulaString +="Meat; ";
                    itemJSON.put("formula",formulaString);
                    itemJSON.put("price9",item.getPrice());

                    itemArray.add(itemJSON);
                }
                else {
                    int tmp = index -1;
                    JSONObject fallItemJSON = itemArray.getJSONObject(tmp);
                    fallItemJSON.put("price12",item.getPrice());
                    itemArray.remove(tmp);
                    itemArray.add(fallItemJSON);
                }
                index ++;
            }
        }
        itemData.put("count",items.size());
        itemData.put("data",itemArray);
        dataJson.put("itemData",itemData);
        return dataJson.toJSONString();
    }
}
