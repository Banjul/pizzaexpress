package com.springboot.pizzaexpress.service;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.bean.ExpressOrder;

import java.util.List;
public interface ExpressOrderService {
    public String getExpressContent(int deliverId);

    public void updateExpressStatus(int deliverId, String newExpressStatus,String oldExpressStatus);

}
