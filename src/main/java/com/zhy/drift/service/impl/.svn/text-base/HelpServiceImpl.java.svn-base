package com.zhy.drift.service.impl;

import com.zhy.drift.bean.Message;
import com.zhy.drift.bean.MessageType;
import com.zhy.drift.service.HelpService;
import com.zhy.drift.util.TemplateUtil;

public class HelpServiceImpl implements HelpService {

    /**
     * 帮助代码
     */
    public static String HELP_BZ = "bz";

    /**
     * 帮助代码
     */
    public static String HELP_H = "h";

    
    @Override
    public Message help(Message message) {
        message.setResult(TemplateUtil.getTemplateMessage("help.bz"));
        return message;
    }

    @Override
    public Message invoke(Message message) {
    	message.setType(MessageType.HELP);
        return help(message);
    }

    @Override
    public boolean isTriggered(Message message) {
        if (message.getContent().equals(HELP_BZ) || message.getContent().equals(HELP_H)) {
            return true;
        }
        return false;
    }

}
