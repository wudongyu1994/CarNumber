package com.android.querywarn.data_class;

public class Warn_class {
    private Number id,corporation,outStorageForm,truck,createTime,truckLog,gPSX,gPSY;
    private String status="",warnType="";
    public String getQueryURL() {
        String postParam_truck ;
        if ((""+truck).equals("null")){
            postParam_truck = "";
        }else {
            postParam_truck = "truck=" + truck;
        }
        return "http://120.76.219.196:8082/ScsyERP/Warn/query?" +
                postParam_truck +
                "&status=" + status +
                "&warnType=" + warnType ;
    }
    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    public Number getCorporation() {
        return corporation;
    }

    public void setCorporation(Number corporation) {
        this.corporation = corporation;
    }

    public Number getOutStorageForm() {
        return outStorageForm;
    }

    public void setOutStorageForm(Number outStorageForm) {
        this.outStorageForm = outStorageForm;
    }

    public Number getTruck() {
        return truck;
    }

    public void setTruck(Number truck) {
        this.truck = truck;
    }

    public Number getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Number createTime) {
        this.createTime = createTime;
    }

    public Number getTruckLog() {
        return truckLog;
    }

    public void setTruckLog(Number truckLog) {
        this.truckLog = truckLog;
    }

    public Number getgPSX() {
        return gPSX;
    }

    public void setgPSX(Number gPSX) {
        this.gPSX = gPSX;
    }

    public Number getgPSY() {
        return gPSY;
    }

    public void setgPSY(Number gPSY) {
        this.gPSY = gPSY;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWarnType() {
        return warnType;
    }

    public void setWarnType(String warnType) {
        this.warnType = warnType;
    }
}
