package com.springboot.pizzaexpress.service;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.bean.Item;

import java.util.List;
public interface ItemService {
    public String getAllItems();

    public String updateItems(int pizzaId,String pizzaName,String description,String pizzaStatus, double price9, double price12, String picURL);

    public String insertItemAndFormula(String pizzaName,String description, double flour, double egg, double cheese,double vegetable, double meat, String pizzaStatus, String picURL, double price9,double price12);

    public String getItemByName(String itemName);

    public String getItemById(int itemId);
}
