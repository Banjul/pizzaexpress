package com.springboot.pizzaexpress.serviceImpl;

/**
 * Created by sts on 2019/3/2.
 */
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
import com.springboot.pizzaexpress.bean.Item;
import com.springboot.pizzaexpress.bean.PizzaOrder;
import com.springboot.pizzaexpress.dao.ItemDao;
import com.springboot.pizzaexpress.dao.OrderDao;
import com.springboot.pizzaexpress.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import javax.persistence.criteria.Order;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ItemDao itemDao;

    @Override
    public String getLastTwentyOrders(int shop_id) {
        JSONArray orderArray = new JSONArray();
        JSONObject orderData = new JSONObject();
        JSONObject dataJson = new JSONObject();

        List<PizzaOrder> pizzaOrders = orderDao.queryLastTwentyOrders(shop_id);
        if (pizzaOrders.size() > 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            for (PizzaOrder pizzaOrder : pizzaOrders) {
                JSONObject pizzaOrderJSON = new JSONObject();
                pizzaOrderJSON.put("orderID", pizzaOrder.getOrderId());
                Date dateTime = pizzaOrder.getStartTime();
                String startDateSdf = sdf.format(dateTime);
                pizzaOrderJSON.put("startDate", startDateSdf);

                Date enddateTime = pizzaOrder.getEndTime();
                if (enddateTime == null) {
                    pizzaOrderJSON.put("finishDate", "-");
                } else {
                    String endDateSdf = sdf.format(enddateTime);
                    pizzaOrderJSON.put("finishDate", endDateSdf);
                }
                pizzaOrderJSON.put("user", pizzaOrder.getUserId());
                pizzaOrderJSON.put("deliver", pizzaOrder.getDeliverId());

                String pizzaOrderItems = "";
                String orderItems = pizzaOrder.getItems();
                JSONArray orderOldArray = JSONArray.fromObject(orderItems);
                for (int i = 0; i < orderOldArray.size(); i++) {
                    JSONObject itemJson = orderOldArray.getJSONObject(i);
                    String itemIdString = itemJson.get("itemId").toString();
                    String itemCountString = itemJson.get("count").toString();
                    int itemId = Integer.parseInt(itemIdString);

                    Item oneItem = itemDao.queryItemByItemId(itemId);
                    pizzaOrderItems = pizzaOrderItems + oneItem.getItemName() + ": " + itemCountString + ";" + "\n";
                }
                pizzaOrderJSON.put("orderInfo", pizzaOrderItems);

                pizzaOrderJSON.put("orderStatus", pizzaOrder.getState());
                pizzaOrderJSON.put("orderAmount", pizzaOrder.getPrice());
                pizzaOrderJSON.put("orderAddress", pizzaOrder.getToPosString());

                orderArray.add(pizzaOrderJSON);
            }

        }
        orderData.put("count", pizzaOrders.size());
        orderData.put("data", orderArray);
        dataJson.put("orderData", orderData);
        return dataJson.toString();

    }

    @Override
    public String queryOrderByTimeAndShop(String start_time, String end_time, int shop_id) {
        JSONArray orderArray = new JSONArray();
        JSONObject orderData = new JSONObject();
        JSONObject dataJson = new JSONObject();

        List<PizzaOrder> pizzaOrders = orderDao.queryOrderByTimeAndShop(start_time, end_time, shop_id);
        if (pizzaOrders.size() > 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            for (PizzaOrder pizzaOrder : pizzaOrders) {
                JSONObject pizzaOrderJSON = new JSONObject();
                pizzaOrderJSON.put("orderID", pizzaOrder.getOrderId());
                Date dateTime = pizzaOrder.getStartTime();
                String startDateSdf = sdf.format(dateTime);
                pizzaOrderJSON.put("startDate", startDateSdf);

                Date enddateTime = pizzaOrder.getEndTime();
                if (enddateTime == null) {
                    pizzaOrderJSON.put("finishDate", "-");
                } else {
                    String endDateSdf = sdf.format(enddateTime);
                    pizzaOrderJSON.put("finishDate", endDateSdf);
                }
                pizzaOrderJSON.put("user", pizzaOrder.getUserId());
                pizzaOrderJSON.put("deliver", pizzaOrder.getDeliverId());

                String pizzaOrderItems = "";
                String orderItems = pizzaOrder.getItems();
                JSONArray orderOldArray = JSONArray.fromObject(orderItems);
                for (int i = 0; i < orderOldArray.size(); i++) {
                    JSONObject itemJson = orderOldArray.getJSONObject(i);
                    String itemIdString = itemJson.get("itemId").toString();
                    String itemCountString = itemJson.get("count").toString();
                    int itemId = Integer.parseInt(itemIdString);

                    Item oneItem = itemDao.queryItemByItemId(itemId);
                    pizzaOrderItems = pizzaOrderItems + oneItem.getItemName() + ": " + itemCountString + ";" + "\n";
                }
                pizzaOrderJSON.put("orderInfo", pizzaOrderItems);

                pizzaOrderJSON.put("orderStatus", pizzaOrder.getState());
                pizzaOrderJSON.put("orderAmount", pizzaOrder.getPrice());
                pizzaOrderJSON.put("orderAddress", pizzaOrder.getToPosString());

                orderArray.add(pizzaOrderJSON);
            }
        }
        orderData.put("count", pizzaOrders.size());
        orderData.put("data", orderArray);
        dataJson.put("orderData", orderData);
        return dataJson.toString();
    }


    @Override
    public String queryOrderByOrderId(int orderID) {
        JSONArray orderArray = new JSONArray();
        JSONObject orderData = new JSONObject();
        JSONObject dataJson = new JSONObject();

        PizzaOrder pizzaOrder = orderDao.queryOrderByOrderId(orderID);

        if (pizzaOrder != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            JSONObject pizzaOrderJSON = new JSONObject();
            pizzaOrderJSON.put("orderID", pizzaOrder.getOrderId());
            Date dateTime = pizzaOrder.getStartTime();
            String startDateSdf = sdf.format(dateTime);
            pizzaOrderJSON.put("startDate", startDateSdf);

            Date enddateTime = pizzaOrder.getEndTime();
            if (enddateTime == null) {
                pizzaOrderJSON.put("finishDate", "-");
            } else {
                String endDateSdf = sdf.format(enddateTime);
                pizzaOrderJSON.put("finishDate", endDateSdf);
            }
            pizzaOrderJSON.put("user", pizzaOrder.getUserId());
            pizzaOrderJSON.put("deliver", pizzaOrder.getDeliverId());

            String pizzaOrderItems = "";
            String orderItems = pizzaOrder.getItems();
            JSONArray orderOldArray = JSONArray.fromObject(orderItems);
            for (int i = 0; i < orderOldArray.size(); i++) {
                JSONObject itemJson = orderOldArray.getJSONObject(i);
                String itemIdString = itemJson.get("itemId").toString();
                String itemCountString = itemJson.get("count").toString();
                int itemId = Integer.parseInt(itemIdString);

                Item oneItem = itemDao.queryItemByItemId(itemId);
                pizzaOrderItems = pizzaOrderItems + oneItem.getItemName() + ": " + itemCountString + ";" + "\n";
            }
            pizzaOrderJSON.put("orderInfo", pizzaOrderItems);

            pizzaOrderJSON.put("orderStatus", pizzaOrder.getState());
            pizzaOrderJSON.put("orderAmount", pizzaOrder.getPrice());
            pizzaOrderJSON.put("orderAddress", pizzaOrder.getToPosString());

            orderArray.add(pizzaOrderJSON);
        }
        orderData.put("data",orderArray);
        dataJson.put("orderData",orderData);
        return dataJson.toString();

    }

    @Override
    public String queryOrderByOrderIdAndTime(int orderID, int shopId, String startTime, String endTime) {
        JSONArray orderArray = new JSONArray();
        JSONObject orderData = new JSONObject();
        JSONObject dataJson = new JSONObject();

        List<PizzaOrder> pizzaOrders = orderDao.queryOrderByOrderIdAndTime(orderID,shopId,startTime,endTime);
        if (pizzaOrders.size() >0) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            for (PizzaOrder pizzaOrder : pizzaOrders) {
                JSONObject pizzaOrderJSON = new JSONObject();
                pizzaOrderJSON.put("orderID", pizzaOrder.getOrderId());
                Date dateTime = pizzaOrder.getStartTime();
                String startDateSdf = sdf.format(dateTime);
                pizzaOrderJSON.put("startDate", startDateSdf);

                Date enddateTime = pizzaOrder.getEndTime();
                if (enddateTime == null) {
                    pizzaOrderJSON.put("finishDate", "-");
                } else {
                    String endDateSdf = sdf.format(enddateTime);
                    pizzaOrderJSON.put("finishDate", endDateSdf);
                }
                pizzaOrderJSON.put("user", pizzaOrder.getUserId());
                pizzaOrderJSON.put("deliver", pizzaOrder.getDeliverId());

                String pizzaOrderItems = "";
                String orderItems = pizzaOrder.getItems();
                JSONArray orderOldArray = JSONArray.fromObject(orderItems);
                for (int i = 0; i < orderOldArray.size(); i++) {
                    JSONObject itemJson = orderOldArray.getJSONObject(i);
                    String itemIdString = itemJson.get("itemId").toString();
                    String itemCountString = itemJson.get("count").toString();
                    int itemId = Integer.parseInt(itemIdString);

                    Item oneItem = itemDao.queryItemByItemId(itemId);
                    pizzaOrderItems = pizzaOrderItems + oneItem.getItemName() + ": " + itemCountString + ";" + "\n";
                }
                pizzaOrderJSON.put("orderInfo", pizzaOrderItems);

                pizzaOrderJSON.put("orderStatus", pizzaOrder.getState());
                pizzaOrderJSON.put("orderAmount", pizzaOrder.getPrice());
                pizzaOrderJSON.put("orderAddress", pizzaOrder.getToPosString());

                orderArray.add(pizzaOrderJSON);
            }
        }
            orderData.put("count",pizzaOrders.size());
            orderData.put("data",orderArray);
            dataJson.put("orderData",orderData);
            return dataJson.toString();
    }


    @Override
    public String queryOrderByDeliverAndTime(int deliverId, int shopId, String startTime, String endTime) {
        JSONArray orderArray = new JSONArray();
        JSONObject orderData = new JSONObject();
        JSONObject dataJson = new JSONObject();

        List<PizzaOrder> pizzaOrders = orderDao.queryOrderByDeliverAndTime(deliverId,shopId,startTime,endTime);
        if (pizzaOrders.size() >0) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            for (PizzaOrder pizzaOrder : pizzaOrders) {
                JSONObject pizzaOrderJSON = new JSONObject();
                pizzaOrderJSON.put("orderID", pizzaOrder.getOrderId());
                Date dateTime = pizzaOrder.getStartTime();
                String startDateSdf = sdf.format(dateTime);
                pizzaOrderJSON.put("startDate", startDateSdf);

                Date enddateTime = pizzaOrder.getEndTime();
                if (enddateTime == null) {
                    pizzaOrderJSON.put("finishDate", "-");
                } else {
                    String endDateSdf = sdf.format(enddateTime);
                    pizzaOrderJSON.put("finishDate", endDateSdf);
                }
                pizzaOrderJSON.put("user", pizzaOrder.getUserId());
                pizzaOrderJSON.put("deliver", pizzaOrder.getDeliverId());

                String pizzaOrderItems = "";
                String orderItems = pizzaOrder.getItems();
                JSONArray orderOldArray = JSONArray.fromObject(orderItems);
                for (int i = 0; i < orderOldArray.size(); i++) {
                    JSONObject itemJson = orderOldArray.getJSONObject(i);
                    String itemIdString = itemJson.get("itemId").toString();
                    String itemCountString = itemJson.get("count").toString();
                    int itemId = Integer.parseInt(itemIdString);

                    Item oneItem = itemDao.queryItemByItemId(itemId);
                    pizzaOrderItems = pizzaOrderItems + oneItem.getItemName() + ": " + itemCountString + ";" + "\n";
                }
                pizzaOrderJSON.put("orderInfo", pizzaOrderItems);

                pizzaOrderJSON.put("orderStatus", pizzaOrder.getState());
                pizzaOrderJSON.put("orderAmount", pizzaOrder.getPrice());
                pizzaOrderJSON.put("orderAddress", pizzaOrder.getToPosString());

                orderArray.add(pizzaOrderJSON);
            }
        }
            orderData.put("count",pizzaOrders.size());
            orderData.put("data",orderArray);
            dataJson.put("orderData",orderData);
            return dataJson.toString();
    }


    @Override
    public String getOrderByDeliver(int shop_id, int deliver_id) {
        JSONArray orderArray = new JSONArray();
        JSONObject orderData = new JSONObject();
        JSONObject dataJson = new JSONObject();

        List<PizzaOrder> pizzaOrders = orderDao.queryOrderByDeliver(shop_id,deliver_id);
        if (pizzaOrders.size() >0) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            for (PizzaOrder pizzaOrder : pizzaOrders) {
                JSONObject pizzaOrderJSON = new JSONObject();
                pizzaOrderJSON.put("orderID", pizzaOrder.getOrderId());
                Date dateTime = pizzaOrder.getStartTime();
                String startDateSdf = sdf.format(dateTime);
                pizzaOrderJSON.put("startDate", startDateSdf);

                Date enddateTime = pizzaOrder.getEndTime();
                if (enddateTime == null) {
                    pizzaOrderJSON.put("finishDate", "-");
                } else {
                    String endDateSdf = sdf.format(enddateTime);
                    pizzaOrderJSON.put("finishDate", endDateSdf);
                }
                pizzaOrderJSON.put("user", pizzaOrder.getUserId());
                pizzaOrderJSON.put("deliver", pizzaOrder.getDeliverId());

                String pizzaOrderItems = "";
                String orderItems = pizzaOrder.getItems();
                JSONArray orderOldArray = JSONArray.fromObject(orderItems);
                for (int i = 0; i < orderOldArray.size(); i++) {
                    JSONObject itemJson = orderOldArray.getJSONObject(i);
                    String itemIdString = itemJson.get("itemId").toString();
                    String itemCountString = itemJson.get("count").toString();
                    int itemId = Integer.parseInt(itemIdString);

                    Item oneItem = itemDao.queryItemByItemId(itemId);
                    pizzaOrderItems = pizzaOrderItems + oneItem.getItemName() + ": " + itemCountString + ";" + "\n";
                }
                pizzaOrderJSON.put("orderInfo", pizzaOrderItems);

                pizzaOrderJSON.put("orderStatus", pizzaOrder.getState());
                pizzaOrderJSON.put("orderAmount", pizzaOrder.getPrice());
                pizzaOrderJSON.put("orderAddress", pizzaOrder.getToPosString());

                orderArray.add(pizzaOrderJSON);
        }
    }
        orderData.put("count",pizzaOrders.size());
        orderData.put("data",orderArray);
        dataJson.put("orderData",orderData);
        return dataJson.toString();

    }
//
//    @Override
//    public String deleteOrderByOrderId(int orderId) {
//        orderDao.deleteOrderByOrderId(orderId);
//        return "删除成功";
//    }
}

