package com.springboot.pizzaexpress.model;


import java.util.ArrayList;
import java.util.List;

public class CartModel {
    private int cartId;

    private int userId;

    private List<ItemWrapModel>items = new ArrayList<>();

    private ShopModel shop;

}
