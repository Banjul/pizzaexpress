package com.springboot.pizzaexpress.model;


import java.util.ArrayList;
import java.util.List;

public class MenuModel {
    private int menuId;

    private ShopModel shop;

    private List<ItemWrapModel> items = new ArrayList<>();

    public int getMenuId() {
        return menuId;
    }

    public List<ItemWrapModel> getItems() {
        return items;
    }

    public ShopModel getShop() {
        return shop;
    }

    public void setItems(List<ItemWrapModel> items) {
        this.items = items;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public void setShop(ShopModel shop) {
        this.shop = shop;
    }
}
