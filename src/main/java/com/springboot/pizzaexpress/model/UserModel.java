package com.springboot.pizzaexpress.model;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

public class UserModel {
    private String nickName;
    private String password;
    private String telephone;
    private Map<String,String> address;
    private String status;
    private Date lastLoginTime;

    public String getNickName() {
        return nickName;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getPassword() {
        return password;
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

    public Map<String, String> getAddress() {
        return address;
    }

    public void setAddress(Map<String, String> address) {
        this.address = address;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
