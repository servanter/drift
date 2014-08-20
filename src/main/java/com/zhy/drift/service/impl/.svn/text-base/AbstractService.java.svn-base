package com.zhy.drift.service.impl;

import com.zhy.drift.bean.Message;
import com.zhy.drift.util.TemplateUtil;

/**
 * 业务层父类
 * 
 * @author zhy
 * 
 */
public class AbstractService {

    /**
     * 字数限制
     */
    public int limitCount;
    
    public void setLimitCount(int limitCount){
        this.limitCount = limitCount;
    }
    
    public int getLimitCount(){
        return limitCount;
    }

    /**
     * 获取当前result信息
     * 
     * @param message
     * @return
     */
    public String getMessageResult(Message message) {
        if (message.getResult() == null || message.getResult().length() == 0) {
            return "";
        }
        return message.getResult();
    }

    /**
     * 获取当前result长度
     * @param message
     * @return
     */
    public int getMessageResultLength(Message message) {
        return getMessageResult(message).length();
    }

    /**
     * 添加后缀帮助信息
     * 
     * @param message
     */
    public void addPostInfo(Message message) {
        message.setResult(getMessageResult(message) + TemplateUtil.getTemplateMessage("drift.info.post"));
    }
}
