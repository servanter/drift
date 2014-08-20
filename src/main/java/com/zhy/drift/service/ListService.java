package com.zhy.drift.service;

import com.zhy.drift.bean.Message;

/**
 * 清单业务
 * @author zhy
 *
 */
public interface ListService extends CommonService{

    /**
     * 列举用户发送的漂流瓶
     * @param message
     * @return
     */
    public Message listOwn(Message message);
    
    /**
     * 列举用户与某人的漂流瓶
     * @param message
     * @return
     */
    public Message listOther(Message message);
}
