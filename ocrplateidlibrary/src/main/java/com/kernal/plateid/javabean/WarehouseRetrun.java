package com.kernal.plateid.javabean;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class WarehouseRetrun {
    private String msg;
    private JSONObject content;
    private Integer status;

    public String getMsg() {
        return msg;
    }

    public JSONObject getContent() {
        return content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setContent(JSONObject content) {
        this.content = content;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}