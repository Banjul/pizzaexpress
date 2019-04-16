package com.springboot.pizzaexpress.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

public class PizzaOrderModel {

    private int pizzaOrderId;

    private int userId;

    private List<ItemWrapModel>items = new ArrayList<>();

    private ShopModel shop;

    private Date startTime;

    private String startTimeStr;

    private String state;

    private String toPosY;

    private String toPosX;

    private String toPosString;

    private double price;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Date getStartTime() {
        return startTime;
    }

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
        String time =sdf.format(startTime);
        setStartTimeStr(time);
    }

    public int getPizzaOrderId() {
        return pizzaOrderId;
    }

    public void setPizzaOrderId(int pizzaOrderId) {
        this.pizzaOrderId = pizzaOrderId;
    }

    public int getUserId() {return userId;}

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<ItemWrapModel> getItems() {
        return items;
    }

    public void setItems(List<ItemWrapModel> items) {
        this.items = items;
    }

    public ShopModel getShop(){return shop;}

    public void setShop(ShopModel shop) {
        this.shop = shop;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getToPosX() {
        return toPosX;
    }

    public String getToPosY() {
        return toPosY;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setToPosX(String toPosX) {
        this.toPosX = toPosX;
    }

    public void setToPosY(String toPosY) {
        this.toPosY = toPosY;
    }

    public String getToPosString() {
        return toPosString;
    }

    public void setToPosString(String toPosString) {
        this.toPosString = toPosString;
    }

    //    private ExpressModel express;
//
//    private double price;

//    private String startTime;
//
//    private String endTime;
//
//    private String state;
//
//    private String fromPosX;
//
//    private String fromPosY;
//
//    private String toPosY;
//
//    private String toPosX;
//
//    private String fromPosString;
//
//    private String toPosString;

}
