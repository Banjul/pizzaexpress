package com.springboot.pizzaexpress.service;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.bean.Deliver;

import java.util.List;
public interface DeliverService {

    public String getAllDeliversByShop(int shopId);

    public String queryDeliverByName(String deliverName,int shopId);

    public String queryDeliverById(int deliverID,int shopId);

    public Deliver deliverLogin(int account,String password);

    public void updateDeliverStatus(int deliverId,String newStatus);

    public void deliverFinishOneOrder(int deliverId,int expressId,int orderId,String newStatus,String finishTime);

    public String allocateOrderToDeliver(int shopId,int orderId);

}
