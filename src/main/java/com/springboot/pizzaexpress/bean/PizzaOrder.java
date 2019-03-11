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
import java.util.Date;

@Data
@Entity
@Table(name="pizza_order")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class PizzaOrder {

    @Id
    @Column(name = "order_id")
    private int order_id;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "shop_id")
    private int shop_id;

    @Column(name = "express_id")
    private int express_id;

    @Column(name = "items")
    private String items;

    @Column(name = "price")
    private double price;

    @Column(name = "start_time")
    private Date start_time;

    @Column(name = "end_time")
    private Date end_time;

    @Column(name = "state")
    private String state;

    @Column(name = "from_pos_x")
    private String from_pos_x;

    @Column(name = "from_pos_y")
    private String from_pos_y;

    @Column(name = "to_pos_y")
    private String to_pos_y;

    @Column(name = "to_pos_x")
    private String to_pos_x;

    @Column(name = "from_pos_string")
    private String from_pos_string;

    @Column(name = "to_pos_string")
    private String to_pos_string;

    @Column(name = "deliver_id")
    private int deliver_id;

    public int getDeliver_id() {
        return deliver_id;
    }

    public void setDeliver_id(int deliver_id) {
        this.deliver_id = deliver_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public int getExpress_id() {
        return express_id;
    }

    public void setExpress_id(int express_id) {
        this.express_id = express_id;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFrom_pos_x() {
        return from_pos_x;
    }

    public void setFrom_pos_x(String from_pos_x) {
        this.from_pos_x = from_pos_x;
    }

    public String getFrom_pos_y() {
        return from_pos_y;
    }

    public void setFrom_pos_y(String from_pos_y) {
        this.from_pos_y = from_pos_y;
    }

    public String getTo_pos_y() {
        return to_pos_y;
    }

    public void setTo_pos_y(String to_pos_y) {
        this.to_pos_y = to_pos_y;
    }

    public String getTo_pos_x() {
        return to_pos_x;
    }

    public void setTo_pos_x(String to_pos_x) {
        this.to_pos_x = to_pos_x;
    }

    public String getFrom_pos_string() {
        return from_pos_string;
    }

    public void setFrom_pos_string(String from_pos_string) {
        this.from_pos_string = from_pos_string;
    }

    public String getTo_pos_string() {
        return to_pos_string;
    }

    public void setTo_pos_string(String to_pos_string) {
        this.to_pos_string = to_pos_string;
    }
}
