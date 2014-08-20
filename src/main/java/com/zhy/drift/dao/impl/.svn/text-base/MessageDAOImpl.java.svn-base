package com.zhy.drift.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.zhy.drift.bean.Message;
import com.zhy.drift.bean.MessageType;
import com.zhy.drift.dao.MessageDAO;
import com.zhy.drift.exception.DAOException;
import com.zhy.drift.exception.NullRecordsException;

public class MessageDAOImpl implements MessageDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Message> getRandomMessage(Long fromUserId) {
        String sql = "SELECT * FROM `message` WHERE `to_user_id` is null AND is_received = 0 AND `from_user_id` <> ? ORDER BY send_time DESC LIMIT ?";
        try {
            return jdbcTemplate.query(sql, new Object[] { fromUserId, 1 }, new MessageMapper());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Message getMessageById(Long id) throws NullRecordsException {
        return jdbcTemplate.queryForObject("SELECT * FROM `message` WHERE `id` = ?", new Object[] { id },
                new MessageMapper());
    }

    @Override
    public List<Message> getSendMessages5ByFromUserId(Long fromUserId) {
        try {
            return jdbcTemplate.query(
                    "SELECT * FROM `message` WHERE `from_user_id` = ? ORDER BY send_time DESC LIMIT ?", new Object[] {
                            fromUserId, 5 }, new MessageMapper());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getTodaySendMessagesByUserIdAndType(Long fromUserId, int[] codes){
    	String todayTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    	int result = 0;
    	StringBuffer buffer = new StringBuffer();
    	Object[] o = new Object[codes.length + 2];
    	for(int i = 0; i < codes.length; i++){
    		buffer.append("?,");
    		o[i + 2] = codes[i];
    	}
    	o[0] = fromUserId;
    	o[1] = todayTime;
    	String insideSQL = buffer.substring(0, buffer.length() - 1);
    	try {
    		result =  jdbcTemplate.queryForInt(
                    "SELECT COUNT(*) FROM `message` WHERE `from_user_id` = ? AND send_time > ? AND type IN ("+ insideSQL + ") ORDER BY send_time DESC", o, new MessageMapper());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
	}

    
    @Override
    public List<Message> getMessageByUserId(Long toUserId) throws NullRecordsException {
        String sql = "SELECT * FROM `message` WHERE `to_user_id` = ? AND `is_received` = 0 ORDER BY send_time ASC LIMIT ?";
        try {
            return jdbcTemplate.query(sql, new Object[] { toUserId, 3 }, new MessageMapper());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public List<Message> getBottlesByFromAndTo(Long fromUserId, Long toUserId) {
        String sql = "SELECT * FROM `message` WHERE `from_user_id` = ? AND `to_user_id` = ? ORDER BY `id` DESC LIMIT ?";
        try {
            return jdbcTemplate.query(sql, new Object[] { fromUserId, toUserId, 3 }, new MessageMapper());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int save(Message message) throws DAOException {
        String sql = "INSERT INTO `message`(`content`,`from_user_id`,`to_user_id`,`last_bottle`,`type`,`result`,`send_time`,`is_received`) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, new Object[] { message.getContent(), message.getFromUserId(),
                message.getToUserId(), message.getLastBottle(), message.getType().getCode(), message.getResult(), message.getSendTime(),
                message.getIsReceived() });
    }

    @Override
    public int updateReceivedMessages(Long[] ids) throws DAOException {
        String sql = "UPDATE `message` SET `is_received` = 1 WHERE `id` IN (parameters)";
        String parameters = "";
        // package parameters
        for (int i = 0; i < ids.length; i++) {
            parameters += "?,";
        }
        parameters = parameters.substring(0, parameters.length() - 1);
        sql = sql.replace("parameters", parameters);
        return jdbcTemplate.update(sql, ids);
    }

    @Override
    public int[] betchInsert(List<Object[]> parameters) {
        return jdbcTemplate.batchUpdate("INSERT INTO `message`(from_user_id, content) VALUES(?, ?)", parameters);
    }

}

class MessageMapper implements RowMapper<Message> {

    @Override
    public Message mapRow(ResultSet rs, int arg1) throws SQLException {
        Message message = new Message();
        message.setId(rs.getLong("id"));
        message.setContent(rs.getString("content"));
        message.setFromUserId(rs.getLong("from_user_id"));
        message.setToUserId(rs.getLong("to_user_id"));
        message.setLastBottle(rs.getLong("last_bottle"));
        message.setResult(rs.getString("result"));
        message.setSendTime(rs.getTimestamp(("send_time")));
        message.setIsReceived(rs.getBoolean(("is_received")));
        message.setType(MessageType.code2MessageType(rs.getInt("type")));
        return message;
    }
}
