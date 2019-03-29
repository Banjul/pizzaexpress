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

    public void setHttpSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
