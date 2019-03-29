package com.springboot.pizzaexpress.controller;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.bean.User;
import com.springboot.pizzaexpress.bean.PizzaOrder;
import com.springboot.pizzaexpress.model.ItemWrapModel;
import com.springboot.pizzaexpress.model.PizzaOrderModel;
import com.springboot.pizzaexpress.model.ResponseModel;
import com.springboot.pizzaexpress.service.PizzaOrderService;
import io.swagger.annotations.Api;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/order")
@Api("订单api")
public class PizzaOrderController {

    @Autowired
    private PizzaOrderService pizzaOrderService;

    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    public ResponseModel addOrder(@RequestBody PizzaOrderModel pizzaOrderModel, HttpSession session) {
        ResponseModel responseModel = new ResponseModel();
        User u = (User) session.getAttribute("userInfo");
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

        pizzaOrderService.insertToPizzaOrder(userId, shopId, items, startTime, state, fromPosX, fromPosY, toPosX, toPosY, price);

        responseModel.setStatus("200");
        responseModel.setMessage("下单成功！");
        responseModel.setModel(pizzaOrderModel);
        return responseModel;
    }

}
//    @RequestMapping(value = "/getOrdersByUser",method = RequestMethod.POST)
//    public ResponseModel getOrdersByUser (HttpSession session) {
//
//        ResponseModel responseModel = new ResponseModel();
//        User u = (User) session.getAttribute("userInfo");
//        int userId = u.getUserId();
//        List<PizzaOrder> pizzaOrders=pizzaOrderService.queryOrderByUser(userId);

//        responseModel.setStatus("200");
//        responseModel.setMessage("查询成功！");
//
//        PizzaOrderModel pizzaOrderModel=new PizzaOrderModel();
//        pizzaOrderModel.setUserId();
//
//
//
//        cartModel.setCartId(cart.getCartId());
//        cartModel.setItems(list);
//        responseModel.setModel(cartModel);
//        return pizzaOrderService.queryOrderByUser(userId);
//
//    }
//
//}

