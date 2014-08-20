package com.zhy.drift.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.zhy.drift.bean.Message;
import com.zhy.drift.bean.Request;
import com.zhy.drift.bean.Response;
import com.zhy.drift.exception.DAOException;
import com.zhy.drift.service.CommonService;
import com.zhy.drift.service.DriftService;
import com.zhy.drift.service.EntranceService;
import com.zhy.drift.service.EventService;
import com.zhy.drift.service.HelpService;
import com.zhy.drift.service.InfoService;
import com.zhy.drift.service.ListService;
import com.zhy.drift.service.MessageService;
import com.zhy.drift.service.OrderService;
import com.zhy.drift.service.ResponseService;
import com.zhy.drift.service.UserService;
import com.zhy.drift.util.DriftUtil;
import com.zhy.drift.util.TemplateUtil;

/**
 * 总体业务层
 * 
 * @author zhy
 * 
 */
public class EntranceServiceImpl implements EntranceService {

    @Autowired
    private HelpService helpService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private DriftService driftService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private InfoService personService;

    @Autowired
    private ListService listService;

    @Autowired
    private ResponseService responseService;

    @Autowired
    private EventService eventService;

    private List<CommonService> services;

    @PostConstruct
    public void init() {
        services = new ArrayList<CommonService>();
        services.add(helpService);
        services.add(orderService);
        services.add(driftService);
        services.add(personService);
        services.add(listService);
    }

    @Override
    public Response dispatch(Request request) {
        Message message = new Message(request.getFromUserName(), request.getContent());
        message.setContent(DriftUtil.dueStr(message.getContent()));
        if (eventService.isTriggered(request)) {
        	
        	// 订阅或退订
            message.setResult(eventService.event(request));
        } else {
            boolean isExisted = true;
            List<Message> bottles = new ArrayList<Message>();
            if (!userService.isExisted(request.getFromUserName())) {
                if (!message.getContent().startsWith("zl")) {
                    // no register user
                    message.setResult(TemplateUtil.getTemplateMessage("order.no.user", request.getFromUserName()));
                    isExisted = false;
                    bottles.add(message);
                }
            }

            // 存在该用户并且上行信息不是注册开头，则数据库中有一条相对应的记录
            if (isExisted && !message.getContent().startsWith("zl")) {
                message.setFromUserId(userService.getUserByWxUserName(request.getFromUserName()).getId());
            }
            if (isExisted) {
            	
                boolean isTriggered = false;
                try {
                    for (CommonService service : services) {
                        if (service.isTriggered(message)) {
                            service.invoke(message);
                            isTriggered = true;
                            message.setIsReceived(true);
                            bottles.add(message);
                            break;
                        }
                    }
                    if (!isTriggered) {
                        bottles.addAll(driftService.sendAndGetBottle(message));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    message.setResult(TemplateUtil.getTemplateMessage("system.error"));
                }
            }
            try {
                for (Message bottle : bottles) {
                    messageService.save(bottle);
                }
            } catch (DAOException e) {
                e.printStackTrace();
            }
        }
        request.setResult(message.getResult());
        Response response = new Response(request);
        responseService.save(response);
        return response;
    }
}
