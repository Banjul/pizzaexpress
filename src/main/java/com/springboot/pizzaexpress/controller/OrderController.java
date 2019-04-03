package com.springboot.pizzaexpress.controller;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.bean.Item;
import com.springboot.pizzaexpress.bean.Shop;
import com.springboot.pizzaexpress.bean.User;
import com.springboot.pizzaexpress.bean.Order;
import com.springboot.pizzaexpress.model.ItemWrapModel;
import com.springboot.pizzaexpress.model.PizzaOrderModel;
import com.springboot.pizzaexpress.model.ResponseModel;
import com.springboot.pizzaexpress.model.ShopModel;
import com.springboot.pizzaexpress.service.OrderService;
import com.springboot.pizzaexpress.service.ShopService;
import com.springboot.pizzaexpress.service.UserService;
import io.swagger.annotations.Api;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("/order")
@Api("订单api")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    public ResponseModel addOrder(@RequestBody PizzaOrderModel pizzaOrderModel, HttpSession session) {
        ResponseModel responseModel = new ResponseModel();
        User u = (User) session.getAttribute("userInfo");
        if (u == null) {
            responseModel.setStatus("500");
            responseModel.setMessage("用户未登录!");

        } else {
            int userId = u.getUserId();
            // System.err.println(userId);
            int shopId = pizzaOrderModel.getShop().getShopId();
            String fromPosX = pizzaOrderModel.getShop().getPosX();
            String fromPosY = pizzaOrderModel.getShop().getPosY();
            List<ItemWrapModel> itemsList = pizzaOrderModel.getItems();
            JSONArray array = JSONArray.fromObject(itemsList);
            String items = array.toString();
            String state = "1";//   订单未支付，状态为1
            //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            //df.format(new Date());

            Date startTime = new Date();

            String toPosX = pizzaOrderModel.getToPosX();
            String toPosY = pizzaOrderModel.getToPosY();
            double price = pizzaOrderModel.getPrice();

            //获取用户账户余额
            double balance = userService.findBalance(userId);
            if(balance<price){
                responseModel.setStatus("500");
                responseModel.setMessage("余额不足！下单失败");

            }else {

                orderService.insertToPizzaOrder(userId, shopId, items, startTime, state, fromPosX, fromPosY, toPosX, toPosY, price);

                responseModel.setStatus("200");
                responseModel.setMessage("下单成功！");
                responseModel.setModel(pizzaOrderModel);
                balance = balance -price;
                userService.modifyBalance(balance,userId);
            }
        }
        return responseModel;
    }

    @RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
    public ResponseModel cancelOrder(@RequestBody PizzaOrderModel pizzaOrderModel, HttpSession session) {
        ResponseModel responseModel = new ResponseModel();
        User u = (User) session.getAttribute("userInfo");
        if (u == null) {
            responseModel.setStatus("500");
            responseModel.setMessage("用户未登录!");
        } else {
            int orderId = pizzaOrderModel.getPizzaOrderId();
            Date time = new Date();
            System.out.println(time);
            Date startTime = orderService.findStartTime(orderId);
            long start = time.getTime();
            long end = startTime.getTime();
            int msec = (int) (start - end);
            System.out.println("两个时间之间的毫秒差为：" + msec);
            if (msec > 600000) {
                responseModel.setStatus("500");
                responseModel.setMessage("超时，取消失败！");
            } else {
                orderService.modifyStatus(orderId);
                responseModel.setStatus("200");
                responseModel.setMessage("取消成功！");
            }
        }
        return responseModel;
    }

    @RequestMapping(value = "/getOrdersById", method = RequestMethod.GET)
    public ResponseModel getOrdersByUserId(HttpSession session) {
        ResponseModel responseModel = new ResponseModel();
        User u = (User) session.getAttribute("userInfo");
        List<PizzaOrderModel> modelList = new ArrayList<>();
        if (u == null) {
            responseModel.setStatus("500");
            responseModel.setMessage("用户未登录!");
        } else {
            int userId = u.getUserId();
            List<Order> list = orderService.queryOrderByUserId(userId);
            for (Order pizzaOrder : list) {
                int shopId = pizzaOrder.getShopId();
                Shop shop = shopService.findByShopId(shopId);
                ShopModel shopModel = new ShopModel();
                shopModel.setShopName(shop.getShopName());
                shopModel.setShopId(shopId);
                String items = pizzaOrder.getItems();
                JSONArray array = JSONArray.fromObject(items);
                List<ItemWrapModel> itemWrapModels = new ArrayList<>();
                ItemWrapModel itemWrapModel = new ItemWrapModel();
                for(Object o : array){
                    JSONObject object = JSONObject.fromObject(o);
                    JSONObject a = JSONObject.fromObject(object.getString("item"));
                    int b = Integer.parseInt(object.getString("count"));
                    Item item = (Item) JSONObject.toBean(a, Item.class);
                    itemWrapModel.setItem(item);
                    itemWrapModel.setCount(b);
                    itemWrapModels.add(itemWrapModel);
                }
                PizzaOrderModel pizzaOrderModel = new PizzaOrderModel();
                pizzaOrderModel.setUserId(pizzaOrder.getUserId());
                pizzaOrderModel.setPizzaOrderId(pizzaOrder.getOrderId());
                pizzaOrderModel.setStartTimeStr(pizzaOrder.getStartTime());
                pizzaOrderModel.setState(pizzaOrder.getState());
                pizzaOrderModel.setPrice(pizzaOrder.getPrice());
                pizzaOrderModel.setToPosX(pizzaOrder.getToPosX());
                pizzaOrderModel.setToPosY(pizzaOrder.getToPosY());
                pizzaOrderModel.setShop(shopModel);
                pizzaOrderModel.setItems(itemWrapModels);

                // pizzaOrderModel.setItems(pizzaOrder.);
                modelList.add(pizzaOrderModel);
            }

            responseModel.setStatus("200");
            responseModel.setMessage("查询成功！");
            responseModel.setModel(modelList);
        }
        return responseModel;

    }
}




