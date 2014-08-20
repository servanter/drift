package com.zhy.drift.service;

import com.zhy.drift.bean.Message;

/**
 * 帮助业务
 * @author zhy
 *
 */
public interface HelpService extends CommonService{

    /**
     * 帮助信息
     * @param message
     */
    public Message help(Message message);
}
