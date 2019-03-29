package com.springboot.pizzaexpress.controller;


import com.springboot.pizzaexpress.bean.Item;
import com.springboot.pizzaexpress.bean.Shop;
import com.springboot.pizzaexpress.bean.User;
import com.springboot.pizzaexpress.model.ItemWrapModel;
import com.springboot.pizzaexpress.model.PizzaOrderModel;
import com.springboot.pizzaexpress.model.ResponseModel;
import net.sf.json.JSONArray;
import net.sf.json.JSONArray;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.springboot.pizzaexpress.controller.PizzaOrderController;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static net.sf.json.JSONArray.fromObject;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
@Transactional
public class PizzaOrderControllerTest {
    @Autowired
    protected MockMvc mockMvc;

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
        net.sf.json.JSONObject jsonObject2 = net.sf.json.JSONObject.fromObject(responseString2);
        //JSONObject jsonObject2 = JSONObject.fromObject(responseString2);
        String message2 = jsonObject2.getString("message");
        assertEquals(message2,"登录成功");
        MockHttpSession mockHttpSession = (MockHttpSession) mvcResult.getRequest().getSession();
        this.session = mockHttpSession;
    }


    @Test
    public void addOrder() throws Exception {
        //测试订单
        this.session = new MockHttpSession();
        login();
        String requestJson1 = "{\"shop\":{\"shopId\":1,\"posX\":\"111.22\",\"posY\":\"22.33\"},\"items\":[{\"item\":{\"itemId\":1},\"count\":2}],\"toPosX\":\"50.10\",\"toPosY\":\"55.10\",\"price\":200.00}";
        System.out.println("-------请求参数=" + requestJson1);
        String responseString1 = mockMvc.perform(MockMvcRequestBuilders.post("/order/addOrder")
                .session(session)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson1))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println("-------返回的json = " + responseString1);
        net.sf.json.JSONObject jsonObject1 = net.sf.json.JSONObject.fromObject(responseString1);
       // JSONObject jsonObject1=JSONArray.fromObject(responseString1);
      // JSONObject jsonObject1 = JSONObject.fromObject(responseString1);
        String message1 = jsonObject1.getString("message");
        assertEquals(message1, "下单成功！");
    }


}