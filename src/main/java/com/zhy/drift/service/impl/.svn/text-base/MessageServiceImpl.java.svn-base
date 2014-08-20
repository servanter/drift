package com.zhy.drift.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zhy.drift.bean.Message;
import com.zhy.drift.bean.MessageType;
import com.zhy.drift.common.Constant;
import com.zhy.drift.dao.MessageDAO;
import com.zhy.drift.exception.DAOException;
import com.zhy.drift.exception.NullRecordsException;
import com.zhy.drift.service.MessageService;

/**
 * 消息业务层
 * 
 * @author zhy
 * 
 */
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDAO messageDAO;

    @Override
    public List<Message> getRandomBottle(Long fromUserId) {
        return messageDAO.getRandomMessage(fromUserId);
    }

    @Override
    public Message getMessageById(Long id) {
        try {
            return messageDAO.getMessageById(id);
        } catch (NullRecordsException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Message> getSendBottlesByFromUserId(Long fromUserId) {
        try {
            return messageDAO.getSendMessages5ByFromUserId(fromUserId);
        } catch (NullRecordsException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Message> getBottlesByUserId(Long userId) {
        try {
            return messageDAO.getMessageByUserId(userId);
        } catch (NullRecordsException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Message> getBottlesByFromAndTo(Long fromUserId, Long toUserId) {
        try {
            return messageDAO.getBottlesByFromAndTo(fromUserId, toUserId);
        } catch (NullRecordsException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean save(Message message) {
        try {
            int bottle = messageDAO.save(message);
            if (bottle == 0) {
                return true;
            }
            return false;
        } catch (DAOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateReceivedBottles(Long[] ids) {
        try {
            int updateBottles = messageDAO.updateReceivedMessages(ids);
            if (updateBottles != 0) {
                return true;
            }
            return false;
        } catch (DAOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean betchSave(List<Object[]> parameters) {
        return messageDAO.betchInsert(parameters) != null;
    }

	@Override
	public boolean canSend(Long userId) {
		return messageDAO.getTodaySendMessagesByUserIdAndType(userId, new int[]{MessageType.HELP.getCode()}) < Constant.MESSAGE_TODAY_COUNT_LIMIT;
	}
}
