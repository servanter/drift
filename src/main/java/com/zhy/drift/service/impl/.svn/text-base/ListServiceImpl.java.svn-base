package com.zhy.drift.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zhy.drift.bean.Message;
import com.zhy.drift.bean.MessageType;
import com.zhy.drift.bean.User;
import com.zhy.drift.service.ListService;
import com.zhy.drift.service.MessageService;
import com.zhy.drift.service.UserService;
import com.zhy.drift.util.DriftUtil;
import com.zhy.drift.util.TemplateUtil;

public class ListServiceImpl extends AbstractService implements ListService {

    /**
     * 清单代码
     */
    public static String LIST_QD = "qd";

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Override
    public Message listOwn(Message message) {
        // 1. this user send messages.
        // 2. other users send messages to this user.
        List<Message> bottles = messageService.getSendBottlesByFromUserId(message.getFromUserId());
        String result = "";
        if (bottles != null) {
            for (Message bottle : bottles) {
                if (limitCount >= DriftUtil.removeRemain(bottle.getContent()).length() + 1) {
                    result += bottle.getContent() + "\n";
                }
            }
        }
        message.setResult(result);
        addPostInfo(message);
        return message;
    }

    @Override
    public Message listOther(Message message) {
        String name = DriftUtil.removeRemain(message.getContent().replace(LIST_QD, ""));
        User user = userService.getUserByName(name);
        if (user == null) {
            // no user
            message.setResult(getMessageResult(message) + TemplateUtil.getTemplateMessage("list.no.user"));
        } else {

            // 颠倒顺序
            // QD小岩 result: "小岩说的话" 某人回复您"话"
            List<Message> bottles = messageService.getBottlesByFromAndTo(user.getId(), message.getFromUserId());
            if (bottles != null && bottles.size() > 0) {
                for (Message bottle : bottles) {
                    // Get user by phone
                    String bottleMessage = bottle.getContent();
                    // get last bottle message
                    Message lastBottle = null;
                    if (bottle.getLastBottle() != 0L) {
                        lastBottle = messageService.getMessageById(bottle.getLastBottle());
                    }
                    String lastPartContent = null;
                    if (lastBottle != null) {
                        if (lastBottle.getContent().length() < 10) {
                            lastPartContent = lastBottle.getContent();
                        } else {
                            lastPartContent = lastBottle.getContent().substring(0, 10) + "..";
                        }
                    }
                    if (lastPartContent != null) {
                        // 7 length : contains 3 chinese words
                        if (limitCount - lastPartContent.length() - getMessageResultLength(message) - name.length()
                                - bottleMessage.length() - 7 > 0) {

                            // "It's wonderful day!"aaa回复您:yes!
                            message.setResult(getMessageResult(message) + "\"" + lastPartContent + "\"" + name + "回复您:"
                                    + bottleMessage + "\n");
                        }
                    } else {
                        if (limitCount - getMessageResultLength(message) - name.length() - bottleMessage.length() - 5 > 0) {

                            // aaa回复您:yes!
                            message.setResult(getMessageResult(message) + name + "回复您:" + bottleMessage + "\n");
                        }
                    }
                }
            } else {
                // no replys
                message.setResult(getMessageResult(message) + TemplateUtil.getTemplateMessage("drift.no.reply"));
            }
        }
        addPostInfo(message);
        return message;
    }

    @Override
    public Message invoke(Message message) {
        if (message.getContent().length() == 2) {
        	message.setType(MessageType.LISTOWN);
            return listOwn(message);
        } else {
        	message.setType(MessageType.LISTOTHER);
            return listOther(message);
        }
    }

    @Override
    public boolean isTriggered(Message message) {
        if (message.getContent().startsWith(LIST_QD)) {
            return true;
        }
        return false;
    }
}
