package com.zhy.drift.task;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhy.drift.bean.User;
import com.zhy.drift.service.MessageService;
import com.zhy.drift.service.UserService;
import com.zhy.drift.util.TemplateUtil;

/**
 * 定时发送漂流瓶<br>
 * 
 * @author zhanghongyan@outlook.com
 * 
 */
public class MessageTaskScheduling {

    public static Logger logger = Logger.getLogger(MessageTaskScheduling.class);
    
    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    public void start() {
        
        logger.info("[MessageTask]:start.");
        // 获取机器人
        List<User> robots = userService.getRobots();
        if (robots != null && robots.size() > 0) {
            List<Object[]> parameters = new ArrayList<Object[]>();
            for (User user : robots) {
                Object[] every = new Object[2];
                every[0] = user.getId();
                every[1] = TemplateUtil.getRobotMessage();
                parameters.add(every);
            }
            messageService.betchSave(parameters);
        }

    }
}
