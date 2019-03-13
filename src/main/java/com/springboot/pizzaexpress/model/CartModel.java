package com.springboot.pizzaexpress.model;


import java.util.ArrayList;
import java.util.List;

public class CartModel {
    private int cartId;

    private int userId;

    private List<ItemWrapModel>items = new ArrayList<>();

    private ShopModel shop;

    public int getCartId() {
        return cartId;
    }

    public void setShop(ShopModel shop) {
        this.shop = shop;
    }

    public void setItems(List<ItemWrapModel> items) {
        this.items = items;
    }

    public ShopModel getShop() {
        return shop;
    }

    public List<ItemWrapModel> getItems() {
        return items;
    }

    public int getUserId() {
        return userId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
