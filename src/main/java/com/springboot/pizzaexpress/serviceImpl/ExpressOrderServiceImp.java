package com.springboot.pizzaexpress.serviceImpl;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.bean.ExpressOrder;
import com.springboot.pizzaexpress.bean.PizzaOrder;
import com.springboot.pizzaexpress.bean.User;
import com.springboot.pizzaexpress.dao.ExpressOrderDao;
import com.springboot.pizzaexpress.dao.OrderDao;
import com.springboot.pizzaexpress.dao.UserDao;
import com.springboot.pizzaexpress.service.ExpressOrderService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class ExpressOrderServiceImp implements ExpressOrderService{
    @Autowired
    private ExpressOrderDao expressOrderDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserDao userDao;

    @Override
    public String getExpressContent(int deliverId) {
        JSONArray expressOrderArray = new JSONArray();
        JSONObject expressData = new JSONObject();
        JSONObject dataJson = new JSONObject();

        ExpressOrder expressOrder = expressOrderDao.getExpressContentByDeliverId(deliverId,"未满","正在配送");

        if (expressOrder != null) {
            String orderList = expressOrder.getOrderList();
            String[] order = orderList.split(",");
            for (int i = 0; i < order.length;i++)
            {
                int orderId = Integer.parseInt(order[i]);
                PizzaOrder pizzaOrder = orderDao.queryOrderByOrderId(orderId);
                int orderUser = pizzaOrder.getUserId();
                User user = userDao.queryUserByUserId(orderUser);
                JSONObject orderContent = new JSONObject();
                orderContent.put("expressId",expressOrder.getExpressId());
                orderContent.put("orderId",orderId);
                orderContent.put("orderAddress",pizzaOrder.getToPosString());
                orderContent.put("orderStartTime",pizzaOrder.getStartTime());
                orderContent.put("orderUserName",user.getNickName());
                orderContent.put("orderUserPhone",user.getPhoneNumber());

                expressOrderArray.add(orderContent);
            }
            expressData.put("count",order.length);

        }
        else expressData.put("count",0);
        expressData.put("data",expressOrderArray);
        dataJson.put("expressOrderData", expressData);

        return dataJson.toString();
    }

    @Override
    public void updateExpressStatus(int deliverId, String newExpressStatus,String oldExpressStatus) {
        expressOrderDao.updateExpressStatus(deliverId,newExpressStatus,oldExpressStatus);
    }
}
