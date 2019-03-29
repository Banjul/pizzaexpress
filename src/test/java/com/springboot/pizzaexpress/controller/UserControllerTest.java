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
@ContextConfiguration("/application*.yml")


@WebAppConfiguration()
public class UserControllerTest {
    protected MockMvc mockMvc;

//    @Autowired
//    private UserService userService;

    @Autowired
    protected WebApplicationContext userController;
    @Before
    public void setup() throws Exception{
        mockMvc = MockMvcBuilders.webAppContextSetup(userController).alwaysExpect(forwardedUrl(null)).build();
    }

    @Test
    public void registerByName() throws Exception {
        UserModel user = new UserModel();
        user.setNickName("mmy");
        user.setPassword("1234");
        String requestJson = JSON.toJSONString(user);
        System.out.println("--------请求参数="+requestJson);
        String responseString = mockMvc.perform(MockMvcRequestBuilders.post("/user/registerByName")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println("-------返回的json = " + responseString);
        JSONObject jsonObject = JSONObject.fromObject(responseString);
        String message = jsonObject.getString("message");
        assertEquals(message,"注册失败");


    }

//    @Test
//    public void login() throws Exception {
//    }



}