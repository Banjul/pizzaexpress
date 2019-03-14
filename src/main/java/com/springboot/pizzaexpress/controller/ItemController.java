package com.springboot.pizzaexpress.controller;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.bean.Item;
import com.springboot.pizzaexpress.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping(value ="/item")
@Api("披萨种类api")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @ApiOperation(value="查询所有pizza种类")
    @RequestMapping(value = "/getallitems",method = RequestMethod.POST)
    public String getLastTwentyOrders(){
        return itemService.getAllItems();
    }

    @ApiOperation(value="更新pizza种类")
    @ApiImplicitParam(name = "params", value = "包含开始时间，结束时间,shopId,deliverID,order_id的json", dataType = "JSON")
    @RequestMapping(value = "/updateitems", method = RequestMethod.POST)
    public String updateItems (@RequestBody Map<String, Object> params) {
        String pizzaid = params.get("pizzaID").toString();
        int pizzaId = Integer.parseInt(pizzaid);
        String pizzaName = params.get("pizzaName").toString();
        String description = params.get("description").toString();
        String pizzaStatus = params.get("pizzaStatus").toString();
        String price9s = params.get("price9").toString();

        double price9 = Double.parseDouble(price9s);
        String price12s = params.get("price12").toString();
        double price12 = Double.parseDouble(price12s);
        String picURL = "http://modernmixvancouver.com/wp-content/uploads/2016/11/Famoso-Korean-BBQ-PIzza--360x180.jpg";
        return itemService.updateItems(pizzaId,pizzaName,description,pizzaStatus,price9,price12,picURL);
    }

    @ApiOperation(value="添加pizza种类")
    @ApiImplicitParam(name = "params", value = "包含开始时间，结束时间,shopId,deliverID,order_id的json", dataType = "JSON")
    @RequestMapping(value = "/insertitems", method = RequestMethod.POST)
    public String insertItems (@RequestBody Map<String, Object> params) {
        String pizzaName = params.get("pizzaName").toString();
        String description = params.get("description").toString();
        String flourS = params.get("flour").toString();
        double flour = Double.parseDouble(flourS);
        String eggS = params.get("egg").toString();
        double egg = Double.parseDouble(eggS);
        String cheeseS = params.get("cheese").toString();
        double cheese = Double.parseDouble(cheeseS);
        String vegetableS = params.get("vegetable").toString();
        double vegetable = Double.parseDouble(vegetableS);
        String meatS = params.get("meat").toString();
        double meat = Double.parseDouble(meatS);
        String pizzaStatus = "上架";
        String picURL = "https://tse4.mm.bing.net/th?id=OIP.gdbTlUtZuBW0tunKDj4BXgAAAA&pid=Api";
        String price9S = params.get("price9").toString();
        double price9 = Double.parseDouble(price9S);
        String price12S = params.get("price12").toString();
        double price12 = Double.parseDouble(price12S);

        return itemService.insertItemAndFormula(pizzaName,description,flour,egg,cheese,vegetable,meat,pizzaStatus,picURL,price9,price12);

    }

    @ApiOperation(value="根据id和name选择pizza种类")
    @ApiImplicitParam(name = "params", value = "json", dataType = "JSON")
    @RequestMapping(value = "/getitembyidorname", method = RequestMethod.POST)
    public String getItemByIdOrName (@RequestBody Map<String, Object> params) {
        String itemID = params.get("pizzaID").toString();
        String itemName = params.get("pizzaName").toString();
        System.err.println(itemID);
        System.err.println(itemName);

        if (itemID.equals("-1"))
            return itemService.getItemByName(itemName);
        else {
            int itemId = Integer.parseInt(itemID);
            return itemService.getItemById(itemId);
        }
    }

}
