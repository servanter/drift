package com.zhy.drift.dao;

import java.util.List;

import com.zhy.drift.bean.Message;
import com.zhy.drift.bean.MessageType;
import com.zhy.drift.exception.DAOException;
import com.zhy.drift.exception.NullRecordsException;

/**
 * 信息DAO层
 * @author zhy
 *
 */
public interface MessageDAO {

    /**
     * 获取随机信息
     * @param fromUserId
     * NOTE:目前是将最久的信息返回
     * @return
     */
    public List<Message> getRandomMessage(Long fromUserId);
    
    /**
     * 根据Id获取信息
     * @param id
     * @return
     * @throws NullRecordsException
     */
    public Message getMessageById(Long id) throws NullRecordsException;
    /**
     * 返回该用户发送的信息
     * NOTE:目前只返回5条,按时间由新到旧排序
     * @param fromUserId
     * @return
     */
    public List<Message> getSendMessages5ByFromUserId(Long fromUserId) throws NullRecordsException;
    
    /**
     * 返回该用户今天发送的信息数量
     * @param fromUserId
     * @return
     */
    public int getTodaySendMessagesByUserIdAndType(Long fromUserId, int[] codes);
    
    /**
     * 根据用户电话获取回复信息
     * NOTE:目前只返回3条,按时间由旧到新排序
     * @param toUserId
     * @return
     */
    public List<Message> getMessageByUserId(Long toUserId) throws NullRecordsException;
    
    /**
     * 获取两个人的聊天信息
     * @param fromUserId
     * @param toUserId
     * @return
     * @throws NullRecordsException
     */
    public List<Message> getBottlesByFromAndTo(Long fromUserId,Long toUserId) throws NullRecordsException;
    
    /**
     * 存储信息
     * @param message
     * @return
     */
    public int save(Message message) throws DAOException;
    
    /**
     * 将接收的信息设置为"已接受"
     * @param ids
     * @return
     * @throws DAOException
     */
    public int updateReceivedMessages(Long ids[]) throws DAOException;

    public int[] betchInsert(List<Object[]> parameters);
}
