package com.springboot.pizzaexpress.serviceImpl;

/**
 * Created by sts on 2019/3/2.
 */
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.springboot.pizzaexpress.bean.Deliver;
import com.springboot.pizzaexpress.bean.ExpressOrder;
import com.springboot.pizzaexpress.bean.PizzaOrder;
import com.springboot.pizzaexpress.dao.DeliverDao;
import com.springboot.pizzaexpress.dao.OrderDao;
import com.springboot.pizzaexpress.dao.ExpressOrderDao;
import com.springboot.pizzaexpress.service.DeliverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliverServiceImp implements DeliverService{

    @Autowired
    private DeliverDao deliverDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ExpressOrderDao expressOrderDao;

    @Override
    public String getAllDeliversByShop(int shopId) {
        JSONArray deliverArray = new JSONArray();
        JSONObject deliverData = new JSONObject();
        JSONObject dataJson = new JSONObject();

        List<Deliver> delivers = deliverDao.queryAllDeliversByShop(shopId);
        if (delivers.size() >0) {
            for (Deliver deliver : delivers) {
                JSONObject deliverJSON = new JSONObject();
                deliverJSON.put("deliverID",deliver.getDeliverId());
                deliverJSON.put("deliverName",deliver.getName());
                deliverJSON.put("phone",deliver.getPhone());
                deliverJSON.put("deliverStatus",deliver.getStatus());
                deliverJSON.put("deliverNum",deliver.getDeliverNum());

                deliverArray.add(deliverJSON);
            }
            deliverData.put("count",delivers.size());
            deliverData.put("data",deliverArray);
            dataJson.put("deliverData",deliverData);
            return dataJson.toJSONString();
        }
        else {
            deliverData.put("count",delivers.size());
            deliverData.put("data",deliverArray);
            dataJson.put("deliverData",deliverData);
            return dataJson.toJSONString();
        }

    }

    @Override
    public String queryDeliverByName(String deliverName, int shopId) {
        JSONArray deliverArray = new JSONArray();
        JSONObject deliverData = new JSONObject();
        JSONObject dataJson = new JSONObject();

        List<Deliver> delivers = deliverDao.queryDeliverByName(deliverName,shopId);
        if (delivers.size() >0) {
            for (Deliver deliver : delivers) {
                JSONObject deliverJSON = new JSONObject();
                deliverJSON.put("deliverID", deliver.getDeliverId());
                deliverJSON.put("deliverName", deliver.getName());
                deliverJSON.put("phone", deliver.getPhone());
                deliverJSON.put("deliverStatus", deliver.getStatus());
                deliverJSON.put("deliverNum",deliver.getDeliverNum());

                deliverArray.add(deliverJSON);
            }
        }
        deliverData.put("count",delivers.size());
        deliverData.put("data",deliverArray);
        dataJson.put("deliverData",deliverData);
        return dataJson.toJSONString();

    }

    @Override
    public String queryDeliverById(int deliverID, int shopId) {
        JSONArray deliverArray = new JSONArray();
        JSONObject deliverData = new JSONObject();
        JSONObject dataJson = new JSONObject();

        List<Deliver> delivers = deliverDao.queryDeliverById(deliverID,shopId);
        if (delivers.size() >0) {
            for (Deliver deliver : delivers) {
                JSONObject deliverJSON = new JSONObject();
                deliverJSON.put("deliverID", deliver.getDeliverId());
                deliverJSON.put("deliverName", deliver.getName());
                deliverJSON.put("phone", deliver.getPhone());
                deliverJSON.put("deliverStatus", deliver.getStatus());
                deliverJSON.put("deliverNum",deliver.getDeliverNum());

                deliverArray.add(deliverJSON);
            }
        }
        deliverData.put("count",delivers.size());
        deliverData.put("data",deliverArray);
        dataJson.put("deliverData",deliverData);
        return dataJson.toJSONString();
    }

    @Override
    public Deliver deliverLogin(int account, String password) {
        return deliverDao.getDeliverByAccountAndPassword(account,password);

    }

    @Override
    public void updateDeliverStatus(int deliverId, String newStatus) {
        deliverDao.updateDeliverStatus(deliverId,newStatus);
    }

    @Override
    public void deliverFinishOneOrder( int orderId, String newStatus, String finishTime) {
        orderDao.updateOrderStatus(orderId, newStatus, finishTime);
    }

    @Override
    public int allocateOrderToDeliver(int shopId, int orderId) {
        String status = "空闲";
        Deliver deliver = deliverDao.getFreeDeliver(shopId,status);
        if (deliver != null) {
            int deliverId = deliver.getDeliverId();
            ExpressOrder expressOrder = expressOrderDao.queryExpressOrderByDeliverAndStatus(deliverId,"未满");
            if (expressOrder !=null) {
                int expressOrderId = expressOrder.getExpressId();
                String orderList = expressOrder.getOrderList();
                String newOrderList = orderList + ","+orderId;
                expressOrderDao.updateExpressOrderOrderList(expressOrderId,newOrderList);
                String[] orderListArray = newOrderList.split(",");
                if (orderListArray.length == 3) {
                    String newStatus = "正在配送";
                    expressOrderDao.updateExpressStatusById(expressOrderId,newStatus);
                    deliverDao.updateDeliverStatus(deliverId,newStatus);

                }
            }
            else {
                String orderList = orderId +"";
                expressOrderDao.insertExpressOrderOrderList(deliverId,orderList);
            }
            return deliverId;
        }
        else return -1;
    }

}
