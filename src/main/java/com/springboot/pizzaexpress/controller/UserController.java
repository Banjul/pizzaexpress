package com.springboot.pizzaexpress.controller;

/**
 * Created by sts on 2019/3/2.
 */
import com.springboot.pizzaexpress.bean.User;
import com.springboot.pizzaexpress.dto.LoginResponse;
import com.springboot.pizzaexpress.model.ResponseModel;
import com.springboot.pizzaexpress.model.UserModel;
import com.springboot.pizzaexpress.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/user")
@Api("用户api")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户名注册",httpMethod = "POST",notes="用户名唯一")
    @RequestMapping(value = "/registerByName",method = RequestMethod.POST)
    public ResponseModel registerByName (HttpSession session,@RequestBody UserModel userModel){

        /*@RequestParam("name") String name, @RequestParam("password") String password,
        @RequestParam("session") HttpSession httpSession*/

        String name = userModel.getNickName();
        String password = userModel.getPassword();

        ResponseModel response = new ResponseModel();
        User user = userService.findByUserName(name);
        System.err.print(user);

        if(user!=null){
            response.setStatus("500");
            response.setMessage("该用户名已存在");
        }
        else{
            int u = userService.setUser(name,password);
            System.err.println(u);
            if(u!=0){
                response.setStatus("200");
                response.setMessage("注册成功");
                User userInfo = userService.findByUserName(name);
                System.err.print(userInfo);
                session.setAttribute("userInfo",userInfo);
                response.setModel(userInfo);
            }
        }
        return response;
    }

    @ApiOperation(value = "用户名登录",httpMethod = "POST",notes = "")
    @RequestMapping(value = "/login")
    public ResponseModel login(HttpSession session,@RequestBody UserModel userModel){
        ResponseModel response = new ResponseModel();
        String name = userModel.getNickName();
        String password = userModel.getPassword();
        User user = userService.findByUserName(name);
        if(user != null){
            String pwd = user.getPassword();
            if(pwd.equals(password)){
                response.setStatus("200");
                response.setMessage("登录成功");
                session.setAttribute("userInfo",user);
            }
            else {
                response.setStatus("500");
                response.setMessage("密码错误！");
            }
        }
        else{
            response.setStatus("500");
            response.setMessage("该用户名不存在！");
        }

        return response;
    }
}
