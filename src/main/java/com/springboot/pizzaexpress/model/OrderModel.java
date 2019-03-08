package com.springboot.pizzaexpress.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

public class OrderModel {

    private int orderId;

    private UserModel user;

    private ShopModel shop;

    private ExpressModel express;

    private List<ItemWrapModel> items = new ArrayList<>();

    private double price;

    private String startTime;

    private String endTime;

    private String state;

    private String fromPosX;

    private String fromPosY;

    private String toPosY;

    private String toPosX;

    private String fromPosString;

    private String toPosString;

}
