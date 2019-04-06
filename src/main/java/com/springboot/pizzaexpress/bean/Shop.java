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
@Table(name="shop")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Shop {

    @Id
    @Column(name = "shop_id")
    private int shopId;

    @Column(name = "shop_name")
    private String shopName;

    @Column(name = "pos_x")
    private Double posX;

    @Column(name = "pos_y")
    private Double posY;

    @Column(name = "pos_string")
    private String posString;

    @Column(name = "pic_url")
    private String picUrl;

    @Column(name = "account")
    private String account;

    @Column(name = "password")
    private String password;

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shop_id) {
        this.shopId = shop_id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shop_name) {
        this.shopName = shop_name;
    }

    public Double getPosX() {
        return posX;
    }

    public void setPosX(Double pos_x) {
        this.posX = pos_x;
    }

    public Double getPosY() {
        return posY;
    }

    public void setPosY(Double pos_y) {
        this.posY = pos_y;
    }

    public String getPosString() {
        return posString;
    }

    public void setPosString(String pos_string) {
        this.posString = pos_string;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String pic_url) {
        this.picUrl = pic_url;
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
}
