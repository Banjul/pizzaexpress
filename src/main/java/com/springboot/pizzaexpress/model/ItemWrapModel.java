package com.springboot.pizzaexpress.model;

import com.springboot.pizzaexpress.bean.Item;

public class ItemWrapModel {
    private Item item;
    private int count;

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
