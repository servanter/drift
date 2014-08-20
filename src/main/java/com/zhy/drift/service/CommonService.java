package com.zhy.drift.service;

import com.zhy.drift.bean.Message;

public interface CommonService {

    /**
     * 是否被触发
     * @param message
     * @return
     */
    public boolean isTriggered(Message message);

    /**
     * 进入业务流程
     * @param message
     * @return
     */
    public Message invoke(Message message);
    
}
