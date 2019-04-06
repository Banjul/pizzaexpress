package com.springboot.pizzaexpress.controller;

import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
@Transactional
public class MenuControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext cartController;

    @Test
    public void showMenu() throws Exception {
        String requestJson1 = "{\"menuId\":\"\",\"shop\":{\"shopId\":\"1\"}}";
        System.out.println("-------请求参数="+requestJson1);
        String responseString1 = mockMvc.perform(MockMvcRequestBuilders.post("/menu/showMenu")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson1))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println("-------返回的json = " + responseString1);
        JSONObject jsonObject1 = JSONObject.fromObject(responseString1);
        String message1 = jsonObject1.getString("message");
        assertEquals(message1,"success");

    }

}