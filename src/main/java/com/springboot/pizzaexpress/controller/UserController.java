package com.springboot.pizzaexpress.controller;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.bean.User;
import com.springboot.pizzaexpress.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping(value ="/user")
@Api("用户api")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value="登录",notes = "需要参数：用户id，用户密码,session")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account",value="管理员id（指定）",dataType = "String"),
            @ApiImplicitParam(name="password",value = "用户密码",dataType = "String"),
            @ApiImplicitParam(name="session",value = "session",dataType = "HttpSession")
    }
    )
    @RequestMapping(value="/systemaccountlogin",method=RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> login(int account, String password, HttpSession session) {
        System.out.println(account);
        System.out.print(password);
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        User sysAdmin = userService.sysAdminLogin(account,password);
        if ( sysAdmin!=null ) {
            session.setAttribute("sysAdmin",sysAdmin);
            resultMap.put("status", 200);
            resultMap.put("message", "登录成功");
            resultMap.put("account", sysAdmin.getUserId());
        }
        else {
            resultMap.put("status", 500);
            resultMap.put("message", "账号密码错误");
        }
        return resultMap;
    }


    @ApiOperation(value="用户管理")
    @RequestMapping(value = "/getuserinfo",method = RequestMethod.POST)
    public String getUserInfo() {
        return userService.getUserInfo();
    }

    @ApiOperation(value="根据ID查找用户")
    @ApiImplicitParam(name = "params", value = " ", dataType = "JSON")
    @RequestMapping(value = "/getuserbyid",method = RequestMethod.POST)
    public String getUserById(@RequestBody Map<String, Object> params) {
        String userID = params.get("userID").toString();
        int userId = Integer.parseInt(userID);

        return userService.getUserById(userId);
    }
}
