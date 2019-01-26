package com.kernal.plateid.objects;

public class Warehouse extends SCItem{
    private String address,name,phone;
    private int admin,corporation,id;
    private long createTime,updateTime;
    private boolean ifDeleted;

    public Warehouse(String name) {
        this.name = name;
    }

    public Warehouse(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Warehouse(String address, String name, int id) {
        this.address = address;
        this.name = name;
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public void setCorporation(int corporation) {
        this.corporation = corporation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public void setIfDeleted(boolean ifDeleted) {
        this.ifDeleted = ifDeleted;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public int getAdmin() {
        return admin;
    }

    public int getCorporation() {
        return corporation;
    }

    public int getId() {
        return id;
    }

    public long getCreateTime() {
        return createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public boolean isIfDeleted() {
        return ifDeleted;
    }
}
