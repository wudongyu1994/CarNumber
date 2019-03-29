package com.wdy.login;

import com.wdy.list.objects.SCItem;

public class People extends SCItem {
    int userInfo;
    String phone;
    String userTypeName;
    int corporation;
    int id;
    int userType;
    String userName;

    public int getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(int userInfo) {
        this.userInfo = userInfo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    public int getCorporation() {
        return corporation;
    }

    public void setCorporation(int corporation) {
        this.corporation = corporation;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
