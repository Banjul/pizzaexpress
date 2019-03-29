package com.springboot.pizzaexpress.controller;

import com.alibaba.fastjson.JSON;
import com.springboot.pizzaexpress.model.ResponseModel;
import com.springboot.pizzaexpress.model.UserModel;
import com.springboot.pizzaexpress.service.UserService;
import com.springboot.pizzaexpress.serviceImpl.UserServiceImp;
import net.sf.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
@Transactional
public class UserControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext userController;

    @Test
    public void registerByName() throws Exception {
        UserModel user = new UserModel();
        //测试用户名已存在的情况
        user.setNickName("mmy");
        user.setPassword("1234");
        String requestJson1 = JSON.toJSONString(user);
        System.out.println("--------请求参数="+requestJson1);
        String responseString1 = mockMvc.perform(MockMvcRequestBuilders.post("/user/registerByName")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson1))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println("-------返回的json = " + responseString1);
        JSONObject jsonObject1 = JSONObject.fromObject(responseString1);
        String message1 = jsonObject1.getString("message");
        assertEquals(message1,"该用户名已存在");


        //注册成功的情况
        user.setNickName("shijiayi");
        user.setPassword("12345");
        String requestJson2 = JSON.toJSONString(user);
        System.out.println("--------请求参数="+requestJson2);
        String responseString2 = mockMvc.perform(MockMvcRequestBuilders.post("/user/registerByName")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson2))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println("-------返回的json = " + responseString2);
        JSONObject jsonObject2 = JSONObject.fromObject(responseString2);
        String message2 = jsonObject2.getString("message");
        assertEquals(message2,"注册成功");

    }

    @Test
    public void login() throws Exception {
    }



}