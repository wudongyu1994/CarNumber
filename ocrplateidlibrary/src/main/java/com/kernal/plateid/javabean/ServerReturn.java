package com.kernal.plateid.javabean;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;



public class ServerReturn {
    @JSONField(name = "msg")
    private String msg;
    @JSONField(name = "content")
    private JSONObject content;
    @JSONField(name = "status")
    private Integer status;

    public String getMsg() {
        return msg;
    }

    public int getStatus() {
        return status;
    }

    public JSONObject getContent() {
        return content;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setContent(JSONObject content) {
        this.content = content;
    }
}
