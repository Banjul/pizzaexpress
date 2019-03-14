//package com.springboot.pizzaexpress.controller;
//
///**
// * Created by sts on 2019/3/2.
// */
//import com.springboot.pizzaexpress.bean.Shop;
//import com.springboot.pizzaexpress.dao.ShopDao;
//import com.springboot.pizzaexpress.service.ShopService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//import javax.servlet.http.HttpSession;
//
//@RestController
//@RequestMapping(value ="/shop")
//@Api("商店api")
//public class ShopController {
//
//    @Autowired
//    private ShopService shopService;
//
//    @Autowired
//    private ShopDao shopDao;
//
//    @ApiOperation(value="登录",notes = "需要参数：用户id，用户密码,session")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "account",value="管理员id（指定）",dataType = "String"),
//            @ApiImplicitParam(name="password",value = "用户密码",dataType = "String"),
//            @ApiImplicitParam(name="session",value = "session",dataType = "HttpSession")
//    }
//    )
//    @RequestMapping(value="/login",method=RequestMethod.GET)
//    @ResponseBody
//    public Map<String,Object> login(String account, String password, HttpSession session) {
//        System.out.println(account);
//        System.out.print(password);
//        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
//        Shop shopAdmin = shopService.adminLogin(account,password);
//        if ( shopAdmin!=null ) {
//                session.setAttribute("shopAdmin",shopAdmin);
//                System.out.println(session);
//                resultMap.put("status", 200);
//                resultMap.put("message", "登录成功");
//                resultMap.put("account", shopAdmin.getAccount());
//                resultMap.put("shopID",shopAdmin.getShopId());
//        }
//        else {
//            resultMap.put("status", 500);
//            resultMap.put("message", "账号密码错误");
//        }
//        return resultMap;
//    }
//
//    @ApiOperation(value = "根据时间查询订单", notes = "需要，开始时间，结束时间")
//    @ApiImplicitParam(name = "params", value = "包含shopId,formula的json", dataType = "JSON")
//    @RequestMapping(value = "/getformulacount", method = RequestMethod.POST)
//    public int getFormulaCount(@RequestBody Map<String, Object> params) {
//        String shopid = params.get("shopID").toString();
//        int shopId = Integer.parseInt(shopid);
//        String formula = params.get("Formula").toString();
//        return shopService.getFormulaCount(shopId,formula);
//    }
//
//    @ApiOperation(value = "根据时间查询订单", notes = "需要，开始时间，结束时间")
//    @RequestMapping(value = "/getallshops", method = RequestMethod.POST)
//    public String getAllShops() {
//        return shopService.getAllShops();
//    }
//
//    @ApiOperation(value = "添加商家", notes = "")
//    @ApiImplicitParam(name = "params", value = "", dataType = "JSON")
//    @RequestMapping(value = "/insertnewshop", method = RequestMethod.POST)
//    public String insertNewShop(@RequestBody Map<String, Object> params) {
//        String shopName = params.get("shopName").toString();
//        String posX = params.get("posX").toString();
//        String posY = params.get("posY").toString();
//        String posString = params.get("posString").toString();
//        String picUrl = params.get("picUrl").toString();
//        String account = params.get("account").toString();
//        String password = params.get("password").toString();
//        String phone = params.get("phone").toString();
//        String startTime = params.get("startTime").toString();
//        String endTime = params.get("endTime").toString();
//
//        int result = shopDao.insertShop(shopName, posX, posY, posString, picUrl,  account, password, phone, startTime,  endTime);
//        if (result == 1)
//            return "添加成功";
//        else
//            return "添加失败";
//    }
//
//    @ApiOperation(value = "删除商家", notes = "")
//    @ApiImplicitParam(name = "params", value = "", dataType = "JSON")
//    @RequestMapping(value = "/deleteshop", method = RequestMethod.POST)
//    public String deleteShop(@RequestBody Map<String, Object> params) {
//        String shopid = params.get("shopID").toString();
//        int shopId = Integer.parseInt(shopid);
//
//        int result = shopDao.deleteShop(shopId);
//        if (result == 1)
//            return "删除成功";
//        else
//            return "删除失败";
//    }
//
//}
