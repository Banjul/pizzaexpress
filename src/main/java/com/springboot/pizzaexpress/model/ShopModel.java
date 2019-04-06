package com.springboot.pizzaexpress.model;

public class ShopModel {
    private int shopId;

    private String shopName;

    private String posX;

    private String posY;

    private String posString;

    private String picUrl;

    public int getShopId() {
        return shopId;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public String getPosString() {
        return posString;
    }

    public String getPosX() {
        return posX;
    }

    public String getPosY() {
        return posY;
    }

    public String getShopName() {
        return shopName;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public void setPosString(String posString) {
        this.posString = posString;
    }

    public void setPosX(String posX) {
        this.posX = posX;
    }

    public void setPosY(String posY) {
        this.posY = posY;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
