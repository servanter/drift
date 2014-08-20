package com.zhy.drift.service;

import com.zhy.drift.bean.Message;

/**
 * 订购/开通业务
 * @author zhy
 *
 */
public interface OrderService extends CommonService{

    /**
     * 开通业务
     * @param message
     * @return
     */
    public Message order(Message message);
    
    /**
     * 取消业务
     * @param message
     * @return
     */
    public Message cancel(Message message);
    
}
