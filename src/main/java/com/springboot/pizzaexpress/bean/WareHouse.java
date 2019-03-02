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
@Table(name="cart")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class WareHouse {

    @Id
    @Column(name = "warehouse_id")
    private int warehouse_id;

    @Column(name = "address")
    private String  address;

    @Column(name = "flour_quantity")
    private int flour_quantity;

    @Column(name = "egg_quantity")
    private int egg_quantity;

    @Column(name = "cheese_quantity")
    private int cheese_quantity;

    @Column(name = "vegetable_quantity")
    private int vegetable_quantity;

    @Column(name = "meat_quantity")
    private int meat_quantity;

    @Column(name = "account")
    private String account;

    @Column(name = "password")
    private String password;

    public int getWarehouse_id() {
        return warehouse_id;
    }

    public void setWarehouse_id(int warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getFlour_quantity() {
        return flour_quantity;
    }

    public void setFlour_quantity(int flour_quantity) {
        this.flour_quantity = flour_quantity;
    }

    public int getEgg_quantity() {
        return egg_quantity;
    }

    public void setEgg_quantity(int egg_quantity) {
        this.egg_quantity = egg_quantity;
    }

    public int getCheese_quantity() {
        return cheese_quantity;
    }

    public void setCheese_quantity(int cheese_quantity) {
        this.cheese_quantity = cheese_quantity;
    }

    public int getVegetable_quantity() {
        return vegetable_quantity;
    }

    public void setVegetable_quantity(int vegetable_quantity) {
        this.vegetable_quantity = vegetable_quantity;
    }

    public int getMeat_quantity() {
        return meat_quantity;
    }

    public void setMeat_quantity(int meat_quantity) {
        this.meat_quantity = meat_quantity;
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
