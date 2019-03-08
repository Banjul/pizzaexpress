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

    public int getShop_id() {
        return shopId;
    }

    public void setShop_id(int shop_id) {
        this.shopId = shop_id;
    }

    public String getShop_name() {
        return shopName;
    }

    public void setShop_name(String shop_name) {
        this.shopName = shop_name;
    }

    public String getPos_x() {
        return posX;
    }

    public void setPos_x(String pos_x) {
        this.posX = pos_x;
    }

    public String getPos_y() {
        return posY;
    }

    public void setPos_y(String pos_y) {
        this.posY = pos_y;
    }

    public String getPos_string() {
        return posString;
    }

    public void setPos_string(String pos_string) {
        this.posString = pos_string;
    }

    public String getPic_url() {
        return picUrl;
    }

    public void setPic_url(String pic_url) {
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
