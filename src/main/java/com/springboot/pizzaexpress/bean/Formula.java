package com.springboot.pizzaexpress.bean;

/**
 * Created by sts on 2019/3/1.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="formula")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Formula {

    @Id
    @Column(name = "formula_id")
    @GeneratedValue
    private int formulaId;

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


    public int getFormulaId() {
        return formulaId;
    }

    public void setFormulaId(int formulaId) {
        this.formulaId = formulaId;
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
}
