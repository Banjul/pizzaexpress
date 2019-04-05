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

    @Column(name = "shopId")
    private int shopId;

    @Column(name = "purchase_formula")
    private String purchaseFormula;

    @Column(name = "purchase_time")
    private Date purchaseTime;

    @Column(name = "purchase_count")
    private int purchaseCount;

    @Column(name = "purchase_manufacture")
    private String purchaseManufacture;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getPurchaseFormula() {
        return purchaseFormula;
    }

    public void setPurchaseFormula(String purchaseFormula) {
        this.purchaseFormula = purchaseFormula;
    }

    public Date getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public int getPurchaseCount() {
        return purchaseCount;
    }

    public void setPurchaseCount(int purchaseCount) {
        this.purchaseCount = purchaseCount;
    }

    public String getPurchaseManufacture() {
        return purchaseManufacture;
    }

    public void setPurchaseManufacture(String purchaseManufacture) {
        this.purchaseManufacture = purchaseManufacture;
    }
}
