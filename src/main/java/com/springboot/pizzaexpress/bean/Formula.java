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
@Table(name="formula")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Formula {

    @Id
    @Column(name = "formula_id")
    private int formula_id;

    @Column(name = "flour_quantity")
    private int flour_quantity;

    @Column(name = "egg_quantity")
    private int egg_quantity;

    public int getFormula_id() {
        return formula_id;
    }

    public void setFormula_id(int formula_id) {
        this.formula_id = formula_id;
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

    @Column(name = "cheese_quantity")
    private int cheese_quantity;

    @Column(name = "vegetable_quantity")
    private int vegetable_quantity;

    @Column(name = "meat_quantity")
    private int meat_quantity;

}
