package com.zhy.drift.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.zhy.drift.bean.Message;
import com.zhy.drift.bean.MessageType;
import com.zhy.drift.bean.User;
import com.zhy.drift.service.OrderService;
import com.zhy.drift.service.UserService;
import com.zhy.drift.util.DriftUtil;
import com.zhy.drift.util.TemplateUtil;

/**
 * 开通/取消业务层
 * 
 * @author zhy
 * 
 */
public class OrderServiceImpl implements OrderService {

    /**
     * 开通代码
     */
    public static String ORDER_KT = "kt";

    public static String ORDER_ZL = "zl";

    /**
     * 取消代码
     */
    public static String ORDER_CANCEL = "qx";

    @Autowired
    private UserService userService;

    @Override
    public Message cancel(Message message) {
        if (message.getFromUserId() == null) {
            // send error message. return
            message.setResult(TemplateUtil.getTemplateMessage("system.error"));
        }
        User user = userService.getUserById(message.getFromUserId());
        if (userService.updateUserByInValid(user)) {
            message.setResult(TemplateUtil.getTemplateMessage("order.qx"));
        } else {
            message.setResult(TemplateUtil.getTemplateMessage("system.error"));
        }
        return message;

    }

    @Override
    public Message order(Message message) {

        User u = userService.getUserByWxUserName(message.getWxUserName());
        // 先去确定是否有这个唯一号
        if(u != null){
            
        	if(message.getContent().equals(ORDER_KT) || message.getContent().equals(ORDER_ZL)){
        		
        		// 已经开通了账号
        		message.setResult(TemplateUtil.getTemplateMessage("order.no.write.name", u.getName()));
        	}
            // 不可修改用户昵称
            message.setResult(TemplateUtil.getTemplateMessage("order.no.write.name", u.getName()));
            return message;
        }
        User user = new User(message.getWxUserName());
        // extract datas
        DriftUtil.extractInfo(message.getContent(), user);
        
        if (userService.save(user)) {
            message.setResult(TemplateUtil.getTemplateMessage("order.kt", user.getName()));
        } else {

            // 昵称已经被占用
            message.setResult(TemplateUtil.getTemplateMessage("order.exist.name", user.getName()));
        }
        return message;
    }

    @Override
    public Message invoke(Message message) {
        if (message.getContent().startsWith(ORDER_KT) || message.getContent().startsWith(ORDER_ZL)) {
            order(message);
            message.setType(MessageType.ORDER);
        } else if (message.getContent().equals(ORDER_CANCEL)) {
            cancel(message);
            message.setType(MessageType.CANEL);
        }
        return message;
    }

    @Override
    public boolean isTriggered(Message message) {
        if (message.getContent().startsWith(ORDER_KT) || message.getContent().equals(ORDER_CANCEL)
                || message.getContent().startsWith(ORDER_ZL)) {
            return true;
        }
        return false;
    }

}
