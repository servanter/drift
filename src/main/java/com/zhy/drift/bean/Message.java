package com.zhy.drift.bean;

import java.sql.Timestamp;

public class Message extends Object implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5445496972307670296L;

    private Long id;

    private String content;

    private String result;

    private Long fromUserId;

    private Long toUserId;
    
    private String wxUserName;

    /**
     * 回复漂流瓶ID
     */
    private Long lastBottle;
    
    private MessageType type = MessageType.DEFAULT;
    
    public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	/**
     * 是否已经有人接收
     */
    private Boolean isReceived = false;

    /**
     * 发送时间
     */
    private Timestamp sendTime;

    public Message() {

    }

    public Message(String wxUserName, String content) {
        this.wxUserName = wxUserName;
        this.content = content;
    }

    public Message(Message message) {
        this.fromUserId = message.getFromUserId();
        this.content = message.getContent();
        this.id = message.getId();
        this.isReceived = message.getIsReceived();
        this.lastBottle = message.getLastBottle();
        this.result = message.getResult();
        this.sendTime = message.getSendTime();
        this.toUserId = message.getToUserId();
        this.type = message.getType();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsReceived() {
        return isReceived;
    }

    public void setIsReceived(Boolean isReceived) {
        this.isReceived = isReceived;
    }

    public Timestamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

    public Long getLastBottle() {
        if (lastBottle == null) {
            return 0L;
        }
        return lastBottle;
    }

    public void setLastBottle(Long lastBottle) {
        this.lastBottle = lastBottle;
    }

    public Long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    public String getWxUserName() {
        return wxUserName;
    }

    public void setWxUserName(String wxUserName) {
        this.wxUserName = wxUserName;
    }

}
