package com.zhy.drift.bean;

import java.sql.Timestamp;

import com.zhy.drift.util.TemplateUtil;

/**
 * 相应
 * 
 * @author zhanghongyan@outlook.com
 * 
 */
public class Response implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 7265412584553971982L;

    private Request request;

    private Timestamp responseTime;

    public Response(Request request) {
        this.request = request;
    }

    public Request getRequest() {
        return request;
    }

    public Response setRequest(Request request) {
        this.request = request;
        return this;
    }

    public String toWeChat() {
        String result = "";
        Long now = System.currentTimeMillis();
        responseTime = new Timestamp(now);
        switch (request.getType()) {
        case EVENT:
        case TEXT:
            result = TemplateUtil.getTemplateMessage("drift.response.text.xml", request.getFromUserName(), request
                    .getToUserName(), String.valueOf(now).substring(0, String.valueOf(now).length() - 3),
                    "text", request.getResult());
            break;
        case NEWS:
            break;
        }
        return result;
    }

    public Timestamp getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Timestamp responseTime) {
        this.responseTime = responseTime;
    }
    
    @Override
    public String toString() {
        return toWeChat();
    }

}
