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
    private int shop_id;

    @Column(name = "shop_name")
    private String shop_name;

    @Column(name = "pos_x")
    private String pos_x;

    @Column(name = "pos_y")
    private String pos_y;

    @Column(name = "pos_string")
    private String pos_string;

    @Column(name = "pic_url")
    private String pic_url;

    @Column(name = "account")
    private String account;

    @Column(name = "password")
    private String password;

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getPos_x() {
        return pos_x;
    }

    public void setPos_x(String pos_x) {
        this.pos_x = pos_x;
    }

    public String getPos_y() {
        return pos_y;
    }

    public void setPos_y(String pos_y) {
        this.pos_y = pos_y;
    }

    public String getPos_string() {
        return pos_string;
    }

    public void setPos_string(String pos_string) {
        this.pos_string = pos_string;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
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
