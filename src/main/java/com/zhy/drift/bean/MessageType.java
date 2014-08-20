package com.zhy.drift.bean;

/**
 * 信息类型
 * 
 * @author zhanghongyan@outlook.com
 *
 */
public enum MessageType {

	/**
	 * 默认信息
	 */
	DEFAULT(0),
	
	/**
	 * 漂流信息
	 */
	DRIFT(1),
	
	HELP(100),
	
	INFO(101),
	
	LISTOWN(102),
	
	LISTOTHER(103),
	
	ORDER(104),
	
	CANEL(105);
	private int code;
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	private MessageType(int code){
		this.code = code;
	}

	public static MessageType code2MessageType(int code) {
		MessageType[] types = MessageType.values();
		for(MessageType type : types){
			if(type.getCode() == code){
				return type;
			}
		}
		return null;
	}
	
}
