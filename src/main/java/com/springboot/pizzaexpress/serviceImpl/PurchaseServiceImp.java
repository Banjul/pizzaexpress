package com.springboot.pizzaexpress.serviceImpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.springboot.pizzaexpress.bean.Purchase;
import com.springboot.pizzaexpress.dao.PurchaseDao;
import com.springboot.pizzaexpress.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by sts on 2019/3/10.
 */

@Service
public class PurchaseServiceImp implements PurchaseService {

    @Autowired
    private PurchaseDao purchaseDao;

    @Override
    public String getPurchaseByFormula(int shopId, String formula) {

        JSONArray purchaseArray = new JSONArray();
        JSONObject purchaseData = new JSONObject();
        JSONObject dataJson = new JSONObject();

        List<Purchase> purchases = purchaseDao.queryPurchaseByFormula(shopId,formula);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (purchases.size() >0) {
            for (Purchase purchase : purchases) {
                JSONObject purchaseJSON = new JSONObject();
                purchaseJSON.put("purchaseFormula", purchase.getPurchaseFormula());
                String purchaseTime = sdf.format(purchase.getPurchaseTime());
                purchaseJSON.put("purchaseTime", purchaseTime);
                purchaseJSON.put("purchaseCount", purchase.getPurchaseCount());
                purchaseJSON.put("purchaseManufacture",purchase.getPurchaseManufacture());
                purchaseJSON.put("purchaseID",purchase.getId());

                purchaseArray.add(purchaseJSON);
            }
        }
        purchaseData.put("count",purchases.size());
        purchaseData.put("data",purchaseArray);
        dataJson.put("purchaseData",purchaseData);

        return dataJson.toJSONString();
    }
}
