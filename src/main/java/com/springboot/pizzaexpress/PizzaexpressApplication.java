package com.springboot.pizzaexpress;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.springboot.pizzaexpress.util.HttpClientUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.spring.web.json.Json;

import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.springboot.pizzaexpress")
@EnableJpaRepositories("com.springboot.pizzaexpress.dao")
@EntityScan("com.springboot.pizzaexpress.bean")
@EnableTransactionManagement
@EnableScheduling
public class PizzaexpressApplication {

	public static void main(String[] args) {
		SpringApplication.run(PizzaexpressApplication.class, args);

//		String getUrl = "https://restapi.amap.com/v3/geocode/geo?address=北京市朝阳区阜通东大街6号&output=JSON&key=ee4084f8d57d35442a70a1a2f77d8de8";
//
//		net.sf.json.JSONObject addressResult = HttpClientUtil.doGetStr(getUrl);
//		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(addressResult.get("geocodes"));
//		net.sf.json.JSONObject jsonObject = jsonArray.getJSONObject(0);
//		net.sf.json.JSONObject data1 = net.sf.json.JSONObject.fromObject(data);
//		net.sf.json.JSONObject data2 = net.sf.json.JSONObject.fromObject(data1.get("0"));
//		net.sf.json.JSONObject data3 = net.sf.json.JSONObject.fromObject(data2.get("location"));
//		String data3 = jsonObject.get("location").toString();
//
//		System.err.println(data3);
//		System.err.println(HttpClientUtil.doGetStr(getUrl));
//		String location = addressResult.get("localtion").toString();
	}

}
