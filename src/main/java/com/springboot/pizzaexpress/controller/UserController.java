package com.springboot.pizzaexpress.controller;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.bean.User;
import com.springboot.pizzaexpress.dto.LoginResponse;
import com.springboot.pizzaexpress.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/user")
@Api("用户api")
public class UserController {

    @Autowired
    private UserService userService;

//    @ApiOperation(value = "用户名注册",httpMethod = "POST",notes="用户名唯一")
//    public User registerByName (@RequestBody RegisterRequset loginRequset){
//
//    }

    /*@ApiOperation(value = "用户名登录",httpMethod = "POST",notes = "")

    public LoginResponse login(@RequestBody LoginRequest request){

    }*/
}
