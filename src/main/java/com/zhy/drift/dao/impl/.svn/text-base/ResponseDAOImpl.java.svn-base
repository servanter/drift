package com.zhy.drift.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zhy.drift.bean.Request;
import com.zhy.drift.bean.Response;
import com.zhy.drift.dao.ResponseDAO;

public class ResponseDAOImpl implements ResponseDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Response response) {
        Request request = response.getRequest();
        return jdbcTemplate
                .update(
                        "INSERT INTO `wechat_message`(msg_id, to_user_name, from_user_name, request_time, msg_type, event_type, content, raw_content, result) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)",
                        new Object[] { request.getMsgId(), request.getToUserName(), request.getFromUserName(),
                                request.getRequestTime(), request.getType().getCode(), request.getEvent().getCode(),
                                request.getContent(), request.getRawContent(), response.toWeChat() });
    }

}
