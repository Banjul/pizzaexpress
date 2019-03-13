package com.springboot.pizzaexpress.model;

import javax.servlet.http.HttpSession;

public class UserModel {
    private String nickName;
    private String password;
    private String telephone;
    private HttpSession httpSession;

    public String getNickName() {
        return nickName;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getPassword() {
        return password;
    }

    public HttpSession getHttpSession() {
        return httpSession;
    }
}
