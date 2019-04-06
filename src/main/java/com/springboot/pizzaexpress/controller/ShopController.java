package com.springboot.pizzaexpress.controller;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.bean.Shop;
import com.springboot.pizzaexpress.model.CartModel;
import com.springboot.pizzaexpress.model.ResponseModel;
import com.springboot.pizzaexpress.model.ShopModel;
import com.springboot.pizzaexpress.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/shop")
@Api("商店api")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @RequestMapping(value = "/findShop",method = RequestMethod.POST)
    public ResponseModel findShop (@RequestBody Map<String,String> position){
        //用户经纬度
        String posX = position.get("posX");
        String posY = position.get("posY");
        //获取所有店面经纬度
        List<Shop> list = shopService.getShop();
        StringBuffer str = new StringBuffer();
        for(Shop s : list){
            Double shopPosX = s.getPosX();
            Double shopPosY = s.getPosY();
            str = str.append(shopPosX).append(",").append(shopPosY).append("|");
        }
        int length = str.length();
        str.deleteCharAt(length-1);
        String s = "https://restapi.amap.com/v3/distance?origins="+str+"&destination="+posX+","+posY
                +"&output=json&key=07546a6b17273fcf9b80c2431cec5359";
        StringBuffer result = new StringBuffer();
        try {
            URL url = new URL(s);
            URLConnection conn = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            String line;
            while((line = in.readLine()) != null){
                result.append(line);
            }
            in.close();

        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

        String jsonString = result.toString();
        JSONObject object = JSONObject.fromObject(jsonString);
        String r= object.getString("results");
        JSONArray array = JSONArray.fromObject(r);
        for(Object o:array){
            JSONObject ob = JSONObject.fromObject(o);
            int distance = Integer.parseInt(ob.getString("distance"));
            int id = Integer.parseInt(ob.getString("origin_id"));
            if(distance>5000) {
                list.remove(id - 1);
            }
        }

        ResponseModel responseModel = new ResponseModel();
        responseModel.setStatus("200");
        responseModel.setMessage("拉取商店列表成功！");
        responseModel.setModel(list);
        return responseModel;

    }


}
