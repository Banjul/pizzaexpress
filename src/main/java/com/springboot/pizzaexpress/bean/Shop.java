package com.springboot.pizzaexpress.bean;

/**
 * Created by sts on 2019/3/1.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="shop")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Shop {

    @Id
    @Column(name = "shop_id")
    @GeneratedValue
    private int shopId;

    @Column(name = "shop_name")
    private String shopName;

    @Column(name = "pos_x")
    private String posX;

    @Column(name = "pos_y")
    private String posY;

    @Column(name = "pos_string")
    private String posString;

    @Column(name = "pic_url")
    private String picUrl;

    @Column(name = "account")
    private String account;

    @Column(name = "password")
    private String password;

    @Column(name = "flour_quantity")
    private int flourQuantity;

    @Column(name = "egg_quantity")
    private int eggQuantity;

    @Column(name = "cheese_quantity")
    private int cheeseQuantity;

    @Column(name = "vegetable_quantity")
    private int vegetableQuantity;

    @Column(name = "meat_quantity")
    private int meatQuantity;

    @Column(name = "sales_volume")
    private int salesVolume;

    @Column(name = "phone")
    private String phone;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getPosX() {
        return posX;
    }

    public void setPosX(String posX) {
        this.posX = posX;
    }

    public String getPosY() {
        return posY;
    }

    public void setPosY(String posY) {
        this.posY = posY;
    }

    public String getPosString() {
        return posString;
    }

    public void setPosString(String posString) {
        this.posString = posString;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getFlourQuantity() {
        return flourQuantity;
    }

    public void setFlourQuantity(int flourQuantity) {
        this.flourQuantity = flourQuantity;
    }

    public int getEggQuantity() {
        return eggQuantity;
    }

    public void setEggQuantity(int eggQuantity) {
        this.eggQuantity = eggQuantity;
    }

    public int getCheeseQuantity() {
        return cheeseQuantity;
    }

    public void setCheeseQuantity(int cheeseQuantity) {
        this.cheeseQuantity = cheeseQuantity;
    }

    public int getVegetableQuantity() {
        return vegetableQuantity;
    }

    public void setVegetableQuantity(int vegetableQuantity) {
        this.vegetableQuantity = vegetableQuantity;
    }

    public int getMeatQuantity() {
        return meatQuantity;
    }

    public void setMeatQuantity(int meatQuantity) {
        this.meatQuantity = meatQuantity;
    }

    public int getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(int salesVolume) {
        this.salesVolume = salesVolume;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
}
