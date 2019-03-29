package com.springboot.pizzaexpress.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.springboot.pizzaexpress.bean.Shop;


import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;

/**
 * Created by sts on 2019/3/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ShopControllerTest {

    @Autowired
    ShopController shopController;
    HttpSession session =null;

    @Before
    public void initialize(){
        session=new MockHttpSession();
    }

    @Test
    public void shop_admin_login_function_return_noexist_when_the_user_does_not_exist() throws Exception {
        assertEquals("账号密码错误",shopController.login("qwe","123",session).get("message"));

    }

    @Test
    public void getAllShops() throws Exception {

    }

    @Test
    public void insertNewShop() throws Exception {

    }

    @Test
    public void deleteShop() throws Exception {

    }

    @Test
    public void getFormulaByname() throws Exception {

    }

    @Test
    public void getShopById() throws Exception {

    }

    @Test
    public void getShopByDistance() throws Exception {

    }

    @Test
    public void getAllFormulaByShop() throws Exception {

    }

}