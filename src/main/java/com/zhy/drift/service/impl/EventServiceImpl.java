package com.zhy.drift.service.impl;

import org.apache.log4j.Logger;

import com.zhy.drift.bean.Event;
import com.zhy.drift.bean.Request;
import com.zhy.drift.service.EventService;
import com.zhy.drift.util.TemplateUtil;

public class EventServiceImpl implements EventService {

    public static Logger logger = Logger.getLogger(EventServiceImpl.class);

    @Override
    public String event(Request request) {
        switch (request.getEvent()) {
        case SUBSCRIBE:
            return subscribe(request);
        case UNSUBSCRIBE:
            return unSubscribe(request);
        default:
            return subscribe(request);
        }
    }

    @Override
    public boolean isTriggered(Request request) {
        if (request.getEvent() != Event.NULL) {
            return true;
        }
        return false;
    }

    @Override
    public String subscribe(Request request) {
        logger.info("[UserAdd]: username:" + request.getFromUserName());
        return TemplateUtil.getTemplateMessage("drift.event.subscribe.text");
    }

    @Override
    public String unSubscribe(Request request) {
        logger.info("[UserRemove]: username:" + request.getFromUserName());
        return TemplateUtil.getTemplateMessage("drift.event.unsubscribe.text");
    }

}
