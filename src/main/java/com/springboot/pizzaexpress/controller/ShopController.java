package com.springboot.pizzaexpress.controller;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.bean.Shop;
import com.springboot.pizzaexpress.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value ="/shop")
@Api("商店api")
public class ShopController {

    @Autowired
    private ShopService shopService;


    @ApiOperation(value="登录",notes = "需要参数：用户id，用户密码,session")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account",value="管理员id（指定）",dataType = "String"),
            @ApiImplicitParam(name="password",value = "用户密码",dataType = "String"),
            @ApiImplicitParam(name="session",value = "session",dataType = "HttpSession")
    }
    )
    @RequestMapping(value="/login",method=RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> login(String account, String password, HttpSession session) {
        System.out.println(account);
        System.out.print(password);
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        Shop shopAdmin = shopService.adminLogin(account,password);
        if ( shopAdmin!=null ) {
                session.setAttribute("shopAdmin",shopAdmin);
                System.out.println(session);
                resultMap.put("status", 200);
                resultMap.put("message", "登录成功");
                resultMap.put("account", shopAdmin.getAccount());
                resultMap.put("shopID",shopAdmin.getShopId());
        }
        else {
            resultMap.put("status", 500);
            resultMap.put("message", "账号密码错误");
        }
        return resultMap;
    }

//    @ApiOperation(value = "根据时间查询订单", notes = "需要，开始时间，结束时间")
//    @ApiImplicitParam(name = "params", value = "包含shopId,formula的json", dataType = "JSON")
//    @RequestMapping(value = "/getformulacount", method = RequestMethod.POST)
//    public int getFormulaCount(@RequestBody Map<String, Object> params) {
//        String shopid = params.get("shopID").toString();
//        int shopId = Integer.parseInt(shopid);
//        String formula = params.get("Formula").toString();
//        return shopService.getFormulaCount(shopId,formula);
//    }

    @ApiOperation(value = "查询所有工厂", notes = "需要，开始时间，结束时间")
    @RequestMapping(value = "/getallshops", method = RequestMethod.POST)
    public String getAllShops() {
        return shopService.getAllShops();
    }

    @ApiOperation(value = "查询销量前五的工厂", notes = "")
    @RequestMapping(value = "/getTop5shops", method = RequestMethod.POST)
    public String getTop5Shops() {
        return shopService.getTop5Shops();
    }

    @ApiOperation(value = "添加商家", notes = "")
    @ApiImplicitParam(name = "params", value = "", dataType = "JSON")
    @RequestMapping(value = "/insertnewshop", method = RequestMethod.POST)
    public String insertNewShop(@RequestBody Map<String, Object> params) {
        String shopName = params.get("shopName").toString();
        String posString = params.get("posString").toString();
        String picUrl = params.get("picUrl").toString();
        String account = params.get("account").toString();
        String password = params.get("password").toString();
        String phone = params.get("shopPhone").toString();
        String startTime = params.get("shopStartTime").toString();
        String endTime = params.get("shopEndTime").toString();

        return shopService.insertShop(shopName, posString, picUrl,  account, password, phone, startTime,  endTime);
    }

    @ApiOperation(value = "删除商家", notes = "")
    @ApiImplicitParam(name = "params", value = "", dataType = "JSON")
    @RequestMapping(value = "/deleteshop", method = RequestMethod.POST)
    public String deleteShop(@RequestBody Map<String, Object> params) {
        String shopid = params.get("shopID").toString();
        int shopId = Integer.parseInt(shopid);

        return shopService.deleteShop(shopId);
    }

    @ApiOperation(value = "修改商家信息", notes = "")
    @ApiImplicitParam(name = "params", value = "", dataType = "JSON")
    @RequestMapping(value = "/updateShop", method = RequestMethod.POST)
    public String updateShop(@RequestBody Map<String, Object> params) {
        int shopID = Integer.parseInt(params.get("shopID").toString());
        String shopName = params.get("shopName").toString();
        String shopAddress = params.get("shopAddress").toString();
        String shopPhone = params.get("shopPhone").toString();
        String shopStartTime = params.get("shopStartTime").toString();
        String shopEndTime = params.get("shopEndTime").toString();
        return shopService.updateShop(shopID, shopName, shopAddress, shopPhone, shopStartTime, shopEndTime);
    }

    @ApiOperation(value = "查询原料", notes = "")
    @ApiImplicitParam(name = "params", value = "", dataType = "JSON")
    @RequestMapping(value = "/getformulbyname", method = RequestMethod.POST)
    public String getFormulaByname(@RequestBody Map<String, Object> params) {
        String shopid = params.get("shopID").toString();
        int shopId = Integer.parseInt(shopid);

        String formulaName = params.get("formulaName").toString();
        return shopService.getFormulaByname(shopId,formulaName);
    }

    @ApiOperation(value = "查看工厂信息", notes = "")
    @ApiImplicitParam(name = "params", value = "", dataType = "JSON")
    @RequestMapping(value = "/getshopbyid", method = RequestMethod.POST)
    public String getShopById (@RequestBody Map<String, Object> params) {
        String shopid = params.get("shopID").toString();
        int shopId = Integer.parseInt(shopid);

        return shopService.getShopById(shopId);
    }

    @ApiOperation(value = "根据id或名字查看工厂信息", notes = "")
    @ApiImplicitParam(name = "params", value = "", dataType = "JSON")
    @RequestMapping(value = "/getShopByIDorName", method = RequestMethod.POST)
    public String getShopByIDorName (@RequestBody Map<String, Object> params) {
        String shopid = params.get("shopID").toString();
        String shopName = params.get("shopName").toString();
        int shopId = Integer.parseInt(shopid);
        return shopService.getShopByIDorName(shopId, shopName);
    }

    @ApiOperation(value = "查看工厂信息", notes = "")
    @ApiImplicitParam(name = "params", value = "", dataType = "JSON")
    @RequestMapping(value = "/getshopbydistance", method = RequestMethod.POST)
    public String getShopByDistance (@RequestBody Map<String, Object> params) {
        String userid = params.get("userId").toString();
        int userId = Integer.parseInt(userid);
        return shopService.getAllShopsWithinDistance(userId);
    }

    @ApiOperation(value = "查看工厂所有原料", notes = "")
    @ApiImplicitParam(name = "params", value = "", dataType = "JSON")
    @RequestMapping(value = "/getallformulabyshop", method = RequestMethod.POST)
    public String getAllFormulaByShop (@RequestBody Map<String, Object> params) {
        String shopid = params.get("shopID").toString();
        int shopId = Integer.parseInt(shopid);

        return shopService.getAllFormulaByShop(shopId);
    }



}
