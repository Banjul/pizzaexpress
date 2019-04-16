package com.springboot.pizzaexpress.controller;

/**
 * Created by sts on 2019/3/2.
 */
import com.aliyuncs.utils.StringUtils;
import com.springboot.pizzaexpress.bean.User;
import com.springboot.pizzaexpress.dao.UserDao;
import com.springboot.pizzaexpress.model.ResponseModel;
import com.springboot.pizzaexpress.model.UserModel;
import com.springboot.pizzaexpress.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

@RestController
@RequestMapping(value ="/user")
@Api("用户api")
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private UserDao userDao;


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
            Date time = new Date();
            int u = userService.setUser(name,password,time);
            //System.err.println(u);
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
                Date time = new Date();
                int userId = user.getUserId();
                userService.modifyState(time,userId);
                response.setStatus("200");
                response.setMessage("登录成功");
                session.setAttribute("userInfo",user);
                response.setModel(user);
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

    @ApiOperation(value = "用户修改昵称",httpMethod = "POST",notes = "")
    @RequestMapping(value = "/modifyNickName")
    public ResponseModel modifyNickName(HttpSession session,@RequestBody UserModel userModel){
        ResponseModel responseModel = new ResponseModel();
        User u = (User) session.getAttribute("userInfo");
        if(u==null){
            responseModel.setStatus("500");
            responseModel.setMessage("用户未登录!");

        }
        else {
            int userId = u.getUserId();
            String nickName = userModel.getNickName();
            User rst = userService.findByUserName(nickName);
            if(rst ==null) {

                userService.modifyName(nickName, userId);
                responseModel.setStatus("200");
                responseModel.setMessage("修改信息成功！");
                responseModel.setModel(userModel);
            }else{
                responseModel.setStatus("500");
                responseModel.setMessage("该用户名已注册，请重新输入！");
            }
        }
        return responseModel;
    }

    @ApiOperation(value = "用户修改收货地址",httpMethod = "POST",notes = "")
    @RequestMapping(value = "/modifyAddress")
    public ResponseModel modifyAddress(HttpSession session,@RequestBody UserModel userModel){
        ResponseModel responseModel = new ResponseModel();
        User u = (User) session.getAttribute("userInfo");
        if(u==null){
            responseModel.setStatus("500");
            responseModel.setMessage("用户未登录!");
        }
        else {
            int userId = u.getUserId();
            Map addressMap = userModel.getAddress();
            String address = addressMap.toString();
            System.out.println(address);
            userService.modifyAddress(address,userId);
            responseModel.setStatus("200");
            responseModel.setMessage("修改成功！");
            User user = userService.findById(userId);
            responseModel.setModel(user);

        }
        return responseModel;
    }


    /**
     * 验证码接口设置
     */
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "LTAIiwh7NaezpHqn";  // TODO 修改成自己的
    static final String accessKeySecret = "xXaFMU2TUwi1bPD3zqfrlSVYEM0q0c";   // TODO 修改成自己的

    public static SendSmsResponse sendSms(String telephone, String code) throws ClientException {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(telephone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("pizzaExpress");    // TODO 修改成自己的
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_162630859");    // TODO 修改成自己的
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
//        request.setTemplateParam("{\"name\":\"Tom\", \"code\":\"123\"}");
        request.setTemplateParam("{\"code\":\"" + code + "\"}");
        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");
        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if(sendSmsResponse.getCode()!= null && sendSmsResponse.getCode().equals("OK")){
            System.out.println("短信发送成功！");
        }else {
            System.out.println("短信发送失败！");
        }
        return sendSmsResponse;
    }

    /**
     * 随机设置验证码
     */
    private static int newcode;
    public static int getNewcode() {
        return newcode;
    }
    public static void setNewcode(){
        newcode = (int)(Math.random()*9999)+100;  //每次调用生成一位四位数的随机数
    }

    @ApiOperation(value = "用户获取验证码",httpMethod = "POST",notes = "")
    @RequestMapping(value = "/getMessage")
    public ResponseModel getMessage(HttpSession session,@RequestBody Map<String,String> map) throws ClientException, InterruptedException{
        String telephone = map.get("telephone");
        ResponseModel responseModel = new ResponseModel();
        setNewcode();
        String code = Integer.toString(getNewcode());
        System.out.println("发送的验证码为："+code);
        //发短信
        SendSmsResponse response =sendSms(telephone,code); // TODO 填写你需要测试的手机号码
        session.setAttribute(telephone, code);
        System.out.println("短信接口返回的数据----------------");
        System.out.println("Code=" + response.getCode());
        System.out.println("Message=" + response.getMessage());
        System.out.println("RequestId=" + response.getRequestId());
        System.out.println("BizId=" + response.getBizId());
        Date date = new Date();
        //userService.setCode(code,date);
        responseModel.setStatus("200");
        responseModel.setMessage("获取验证码成功！");
        Map<String,Object> result = new HashMap<>();
        result.put("code",code);
        result.put("time",date);
        responseModel.setModel(result);
        return responseModel;
    }

    //随机生成用户名
    public  String setNickName(){
        String nickName = "user"+ (int)(Math.random()*99);  //每次调用生成一位四位数的随机数
        return nickName;
    }

    @ApiOperation(value = "用户获取验证码",httpMethod = "POST",notes = "")
    @RequestMapping(value = "/loginBytelephone")
    public ResponseModel loginByTelephone(@RequestBody Map<String,String> map,HttpSession session){
        ResponseModel responseModel = new ResponseModel();
        String telephone = map.get("telephone");
        String code = map.get("code");
        if (StringUtils.isEmpty(session.getAttribute(telephone).toString()) ||
                !session.getAttribute(telephone).toString().equals(code)
            ){
            responseModel.setStatus("500");
            responseModel.setMessage("验证码错误");
            return responseModel;
        }
        User u =userService.findByTelephone(telephone);
        if(u==null){
            String nickName = setNickName();
            System.out.println(nickName);
            Date time = new Date();
            userService.setUser2(nickName,telephone,time);
            User user = userService.findByTelephone(telephone);
            responseModel.setStatus("200");
            responseModel.setMessage("手机验证码注册成功！");
            responseModel.setModel(user);
            session.setAttribute("userInfo",user);

        }else {
            Date time = new Date();
            int userId = u.getUserId();
            userService.modifyState(time,userId);
            responseModel.setStatus("200");
            responseModel.setMessage("该手机号已注册，为您直接登录！");
            responseModel.setModel(u);
            session.setAttribute("userInfo",u);
        }
        return responseModel;
    }

    @ApiOperation(value = "",httpMethod = "PUT",notes = "")
    @RequestMapping(value = "/{u_id}",  method = RequestMethod.PUT)
    public User update(HttpSession session, @RequestBody User user, @PathVariable("u_id") Integer uId){
        Optional<User> userOld = userDao.findById(uId);
        if (userOld.isPresent() && userOld.get().getUserId() == user.getUserId()){
            userDao.saveAndFlush(user);
            return userDao.findById(uId).get();
        }else {
            return null;
        }
    }

    @ApiOperation(value = "",httpMethod = "GET",notes = "")
    @RequestMapping(value = "/{u_id}", method = RequestMethod.GET)
    public User get(HttpSession session, @PathVariable("u_id") Integer uId){
        return userDao.findById(uId).get();
    }
}



