package com.springboot.pizzaexpress.model;


import java.util.ArrayList;
import java.util.List;

public class MenuModel {
    private int menuId;

    private ShopModel shop;

    private List<ItemWrapModel> items = new ArrayList<>();
}
