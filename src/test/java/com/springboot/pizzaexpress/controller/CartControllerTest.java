package com.springboot.pizzaexpress.controller;

import com.springboot.pizzaexpress.bean.Shop;
import com.springboot.pizzaexpress.model.CartModel;
import com.springboot.pizzaexpress.model.ItemWrapModel;
import com.springboot.pizzaexpress.model.ShopModel;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
@Transactional
public class CartControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext cartController;
    @Autowired
    protected WebApplicationContext userController;
    @Autowired
    protected MockHttpSession session;


    protected void login() throws Exception{
        String requestJson2 = "{\"nickName\":\"s1\",\"password\":\"1111\"}";
        System.out.println("-------请求参数="+requestJson2);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson2))
                .andExpect(status().isOk())
                .andReturn();
        String responseString2 = mvcResult.getResponse().getContentAsString();

        System.out.println("-------返回的json = " + responseString2);
        JSONObject jsonObject2 = JSONObject.fromObject(responseString2);
        String message2 = jsonObject2.getString("message");
        assertEquals(message2,"登录成功");
        MockHttpSession mockHttpSession = (MockHttpSession) mvcResult.getRequest().getSession();
        this.session = mockHttpSession;

    }


    @Test
    public void addToCart() throws Exception {
        //测试未登录
        String requestJson1 = "{\"shop\":{\"shopId\":\"1\"},\"items\":[{\"item\":{\"itemId\":\"1\"},\"count\":5},{\"item\":{\"itemId\":\"1\"},\"count\":10}]}";
        System.out.println("-------请求参数="+requestJson1);
        String responseString1 = mockMvc.perform(MockMvcRequestBuilders.post("/Cart/addToCart")
                .session(session)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson1))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println("-------返回的json = " + responseString1);
        JSONObject jsonObject1 = JSONObject.fromObject(responseString1);
        String message1 = jsonObject1.getString("message");
        assertEquals(message1,"用户未登录!");

        //测试登录加入购物车
        //先做登录操作
        this.session = new MockHttpSession();
        login();
        //session.getAttribute("userInfo");
        System.out.println("session___________"+session.getAttribute("userInfo"));
        //再做加入购物车
        String requestJson3 = "{\"shop\":{\"shopId\":\"1\"},\"items\":[{\"item\":{\"itemId\":\"1\"},\"count\":5},{\"item\":{\"itemId\":\"1\"},\"count\":10}]}";
        System.out.println("-------请求参数="+requestJson3);
        String responseString3 = mockMvc.perform(MockMvcRequestBuilders.post("/Cart/addToCart")
                .session(session)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson3))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println("-------返回的json = " + responseString3);
        JSONObject jsonObject3 = JSONObject.fromObject(responseString3);
        String message3 = jsonObject3.getString("message");
        assertEquals(message3,"添加成功！");
    }

    @Test
    public void showCart() throws Exception {
        //测试未登录
        String responseString1 = mockMvc.perform(MockMvcRequestBuilders.get("/Cart/showCart/?shopId={shopId}",1).session(session))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println("-------返回的json = " + responseString1);
        JSONObject jsonObject1 = JSONObject.fromObject(responseString1);
        String message1 = jsonObject1.getString("message");
        assertEquals(message1,"用户未登录!");

        //测试登录情况
        //先登录
        this.session = new MockHttpSession();
        login();
        //再查询
        String responseString3 = mockMvc.perform(MockMvcRequestBuilders.get("/Cart/showCart/?shopId={shopId}",1).session(session))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println("-------返回的json = " + responseString3);
        JSONObject jsonObject3 = JSONObject.fromObject(responseString3);
        String message3 = jsonObject3.getString("message");
        assertEquals(message3,"查询成功！");
    }

}