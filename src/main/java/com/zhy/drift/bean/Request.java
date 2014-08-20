package com.zhy.drift.bean;

import java.sql.Timestamp;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;

public class Request {

    private Long id;

    private String msgId;

    private String toUserName;

    private String fromUserName;

    private MsgType type;

    private Event event = Event.NULL;
    
    private Timestamp requestTime;

    private String content;
    
    private String rawContent;
    
    private String result;

    public Request(){
        
    }
    public String getContent() {
        return content;
    }

    public Request setContent(String content) {
        this.content = content;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsgId() {
        return msgId;
    }

    public Request setMsgId(String msgId) {
        this.msgId = msgId;
        return this;
    }

    public String getToUserName() {
        return toUserName;
    }

    public Request setToUserName(String toUserName) {
        this.toUserName = toUserName;
        return this;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public Request setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
        return this;
    }

    public MsgType getType() {
        return type;
    }

    public Request setType(MsgType type) {
        this.type = type;
        return this;
    }

    public Timestamp getRequestTime() {
        return requestTime;
    }

    public Request setRequestTime(Timestamp requestTime) {
        this.requestTime = requestTime;
        return this;
    }
    
    public Event getEvent() {
        return event;
    }

    public Request setEvent(Event event) {
        this.event = event;
        return this;
    }

    @SuppressWarnings("unchecked")
    public static Request generate(Document document) {
        Request request = new Request();
        request.setRawContent(document.asXML());
        Element root = document.getRootElement();
        Iterator<Element> it = root.elementIterator();
        while (it.hasNext()) {
            Element e = it.next();
            if (e.getName().equals("ToUserName")) {
                request.setToUserName(e.getText());
            } else if (e.getName().equals("FromUserName")) {
                request.setFromUserName(e.getText());
            } else if (e.getName().equals("MsgType")) {
                request.setType(MsgType.valueOf(e.getText().toUpperCase()));
            } else if (e.getName().equals("Event")) {
                request.setEvent(Event.valueOf(e.getText().toUpperCase()));
            } else if (e.getName().equals("Content")) {
                request.setContent(e.getText());
            } else if (e.getName().equals("MsgId")) {
                request.setMsgId(e.getText());
            } else if (e.getName().equals("CreateTime")) {
                request.setRequestTime(new Timestamp(Long.parseLong(e.getText() + "000")));
            }
        }
        return request;
    }

    public String getRawContent() {
        return rawContent;
    }

    public Request setRawContent(String rawContent) {
        this.rawContent = rawContent;
        return this;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
