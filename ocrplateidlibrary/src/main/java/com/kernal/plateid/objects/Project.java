package com.kernal.plateid.objects;

public class Project extends SCItem{
    private String projectNumber,name;
    private int id,admin,consignee,corporation,customer,manufacturer;
    private long createTime,updateTime;
    private boolean ifDeleted;

    public Project(String projectNumber, String name, int id) {
        this.projectNumber = projectNumber;
        this.name = name;
        this.id = id;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public void setConsignee(int consignee) {
        this.consignee = consignee;
    }

    public void setCorporation(int corporation) {
        this.corporation = corporation;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public void setManufacturer(int manufacturer) {
        this.manufacturer = manufacturer;
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

    public String getProjectNumber() {
        return projectNumber;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getAdmin() {
        return admin;
    }

    public int getConsignee() {
        return consignee;
    }

    public int getCorporation() {
        return corporation;
    }

    public int getCustomer() {
        return customer;
    }

    public int getManufacturer() {
        return manufacturer;
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
