package com.springboot.pizzaexpress.model;


public class ItemBean {
    int itemId;
    int count;

    public int getCount() {
        return count;
    }

    public int getItemId() {
        return itemId;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}