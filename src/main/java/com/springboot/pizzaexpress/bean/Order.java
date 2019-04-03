package com.springboot.pizzaexpress.bean;

/**
 * Created by sts on 2019/3/1.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="pizza_order")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Order {

    @Id
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "shop_id")
    private int shopId;

    @Column(name = "express_id")
    private int expressId;

    @Column(name = "items")
    private String items;

    @Column(name = "price")
    private double price;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @Column(name = "state")
    private String state;

    @Column(name = "from_pos_x")
    private String fromPosX;

    @Column(name = "from_pos_y")
    private String fromPosY;

    @Column(name = "to_pos_y")
    private String toPosY;

    @Column(name = "to_pos_x")
    private String toPosX;

    @Column(name = "from_pos_string")
    private String fromPosString;

    @Column(name = "to_pos_string")
    private String toPosString;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getExpressId() {
        return expressId;
    }

    public void setExpressId(int expressId) {
        this.expressId = expressId;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFromPosX() {
        return fromPosX;
    }

    public void setFromPosX(String fromPosX) {
        this.fromPosX = fromPosX;
    }

    public String getFromPosY() {
        return fromPosY;
    }

    public void setFromPosY(String fromPosY) {
        this.fromPosY = fromPosY;
    }

    public String getToPosY() {
        return toPosY;
    }

    public void setToPosY(String toPosY) {
        this.toPosY = toPosY;
    }

    public String getToPosX() {
        return toPosX;
    }

    public void setToPosX(String toPosX) {
        this.toPosX = toPosX;
    }

    public String getFromPosString() {
        return fromPosString;
    }

    public void setFromPosString(String fromPosString) {
        this.fromPosString = fromPosString;
    }

    public String getToPosString() {
        return toPosString;
    }

    public void setToPosString(String toPosString) {
        this.toPosString = toPosString;
    }
}
