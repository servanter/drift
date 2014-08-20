package com.zhy.drift.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.zhy.drift.bean.Message;
import com.zhy.drift.bean.MessageType;
import com.zhy.drift.bean.User;
import com.zhy.drift.service.InfoService;
import com.zhy.drift.service.UserService;
import com.zhy.drift.util.TemplateUtil;

public class InfoServiceImpl implements InfoService {

    @Autowired
    private UserService userService;

    /**
     * 个人信息代码
     */
    public static String PERSON_XX = "xx";

    @Override
    public Message getPersonalInfo(Message message) {
        String userName = message.getContent().replace("xx", "").trim();
        if (userName.length() == 0) {
            message.setResult(TemplateUtil.getTemplateMessage("user.noname"));
        } else {
            User user = userService.getUserByName(userName);
            // package user information
            String userInfo = TemplateUtil.getTemplateMessage("user.info", user.getName(), user.getCity(), String
                    .valueOf(user.getAge()), user.getSex());
            message.setResult(userInfo);
        }
        return message;
    }

    @Override
    public Message invoke(Message message) {
    	message.setType(MessageType.INFO);
        return getPersonalInfo(message);
    }

    @Override
    public boolean isTriggered(Message message) {
        if (message.getContent().startsWith(PERSON_XX)) {
            return true;
        }
        return false;
    }

}
