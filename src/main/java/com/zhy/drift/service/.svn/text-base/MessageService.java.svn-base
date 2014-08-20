package com.zhy.drift.service;

import java.util.List;

import com.zhy.drift.bean.Message;
import com.zhy.drift.exception.DAOException;

/**
 * 信息业务层
 * 
 * @author zhy
 * 
 */
public interface MessageService {

    /**
     * 获取随机漂流瓶
     * @param fromUserId 该用户的电话号码
     */
    public List<Message> getRandomBottle(Long fromUserId);

    /**
     * 根据Id获取漂流瓶
     * @param id
     * @return
     */
    public Message getMessageById(Long id);

    /**
     * 返回该用户发送的信息
     * @param fromUserId
     * @return
     */
    public List<Message> getSendBottlesByFromUserId(Long fromUserId);

    /**
     * 根据发送到这个用户的电话,获取回复信息列表(to_phone)
     * 
     * @param userId
     * @return
     */
    public List<Message> getBottlesByUserId(Long userId);

    /**
     * 获取两个人的聊天记录
     * @param fromUserId
     * @param toUserId
     * @return
     */
    public List<Message> getBottlesByFromAndTo(Long fromUserId, Long toUserId);

    /**
     * 存储信息
     * 
     * @param message
     * @return
     */
    public boolean save(Message message) throws DAOException;

    /**
     * 批量更新已经接收的漂流瓶
     * @param ids
     * @return
     */
    public boolean updateReceivedBottles(Long ids[]);

    public boolean betchSave(List<Object[]> parameters);
    
    /**
     * 今天是否可以查看瓶子
     * 
     * @param userId
     * @return true can send else if not 
     */
    public boolean canSend(Long userId);
}
