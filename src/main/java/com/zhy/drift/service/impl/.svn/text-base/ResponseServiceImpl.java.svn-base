package com.zhy.drift.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.zhy.drift.bean.Response;
import com.zhy.drift.dao.ResponseDAO;
import com.zhy.drift.service.ResponseService;

/**
 * 发送信息实现类
 * @author zhy
 *
 */
public class ResponseServiceImpl implements ResponseService {

    @Autowired
    private ResponseDAO responseDAO;
    
    @Override
    public boolean save(Response response) {
        return responseDAO.save(response) > 0;
    }

}
