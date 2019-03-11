package com.springboot.pizzaexpress.bean;

/**
 * Created by sts on 2019/3/10.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="purchase")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Purchase {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "shop_id")
    private int shop_id;

    @Column(name = "purchase_formula")
    private String purchase_formula;

    @Column(name = "purchase_time")
    private Date purchase_time;

    @Column(name = "purchase_count")
    private int purchase_count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public String getPurchase_formula() {
        return purchase_formula;
    }

    public void setPurchase_formula(String purchase_formula) {
        this.purchase_formula = purchase_formula;
    }

    public Date getPurchase_time() {
        return purchase_time;
    }

    public void setPurchase_time(Date purchase_time) {
        this.purchase_time = purchase_time;
    }

    public int getPurchase_count() {
        return purchase_count;
    }

    public void setPurchase_count(int purchase_count) {
        this.purchase_count = purchase_count;
    }
}
