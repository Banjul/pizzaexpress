package com.springboot.pizzaexpress.util;

import com.springboot.pizzaexpress.bean.Shop;
import com.springboot.pizzaexpress.dao.ShopDao;
import com.springboot.pizzaexpress.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by sts on 2019/3/11.
 */

@Component
public class PurchaseTimer implements CommandLineRunner {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopDao shopDao;

    @Override
    public void run(String... strings) throws Exception {
        Calendar date = Calendar.getInstance();
        date.set(date.get(Calendar.YEAR),date.get(Calendar.MONTH),date.get(Calendar.DATE),20,00,0);

        long daySpan = 24*60*60*1000;//一天的时间

        Timer t = new Timer();


        t.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("定时查询执行..");
                List<Shop> shops= shopDao.queryAllShop();
                if (shops.size()>0) {
                    for (Shop shop : shops) {
                        int shopId = shop.getShopId();
                        shopService.queryFormulaCountTimely(shopId);
                    }
                }

            }
        },date.getTime(),daySpan);
    }
}
