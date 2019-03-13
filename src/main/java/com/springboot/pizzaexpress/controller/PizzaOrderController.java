package com.springboot.pizzaexpress.controller;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.bean.User;
import com.springboot.pizzaexpress.service.PizzaOrderService;
import io.swagger.annotations.Api;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.pizzaexpress.model.PizzaOrderModel;



import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/order")
@Api("订单api")
public class PizzaOrderController {

//    @Autowired
//    private PizzaOrderService pizzaOrderService;
//    @RequestMapping(value = "/addToOrder",method = RequestMethod.POST)
//       //public ResponseModel addToOrder (@RequestBody PizzaOrderModel cartModel, HttpSession session){
//       //ResponseModel responseModel = new ResponseModel();
//       session.getAttribute("userInfo");

}
