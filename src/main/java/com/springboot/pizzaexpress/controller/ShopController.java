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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
                resultMap.put("shopID",shopAdmin.getShop_id());
        }
        else {
            resultMap.put("status", 500);
            resultMap.put("message", "账号密码错误");
        }
        return resultMap;
    }


}
