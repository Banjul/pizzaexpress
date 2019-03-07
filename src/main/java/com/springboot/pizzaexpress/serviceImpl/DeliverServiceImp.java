package com.springboot.pizzaexpress.serviceImpl;

/**
 * Created by sts on 2019/3/2.
 */
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.springboot.pizzaexpress.bean.Deliver;
import com.springboot.pizzaexpress.dao.DeliverDao;
import com.springboot.pizzaexpress.service.DeliverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliverServiceImp implements DeliverService{

    @Autowired
    private DeliverDao deliverDao;

    @Override
    public String getAllDeliversByShop(int shopId) {
        JSONArray deliverArray = new JSONArray();
        JSONObject deliverData = new JSONObject();
        JSONObject dataJson = new JSONObject();

        List<Deliver> delivers = deliverDao.queryAllDeliversByShop(shopId);
        if (delivers.size() >0) {
            for (Deliver deliver : delivers) {
                JSONObject deliverJSON = new JSONObject();
                deliverJSON.put("deliverID",deliver.getDeliver_id());
                deliverJSON.put("deliverName",deliver.getName());
                deliverJSON.put("phone",deliver.getPhone());
                deliverJSON.put("deliverStatus",deliver.getStatus());

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
                deliverJSON.put("deliverID", deliver.getDeliver_id());
                deliverJSON.put("deliverName", deliver.getName());
                deliverJSON.put("phone", deliver.getPhone());
                deliverJSON.put("deliverStatus", deliver.getStatus());

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
                deliverJSON.put("deliverID", deliver.getDeliver_id());
                deliverJSON.put("deliverName", deliver.getName());
                deliverJSON.put("phone", deliver.getPhone());
                deliverJSON.put("deliverStatus", deliver.getStatus());

                deliverArray.add(deliverJSON);
            }
        }
        deliverData.put("count",delivers.size());
        deliverData.put("data",deliverArray);
        dataJson.put("deliverData",deliverData);
        return dataJson.toJSONString();
    }
}
