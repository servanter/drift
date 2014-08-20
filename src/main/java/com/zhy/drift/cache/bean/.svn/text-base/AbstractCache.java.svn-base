package com.zhy.drift.cache.bean;

import com.zhy.drift.util.Time;

public class AbstractCache {

	private Long createTime = System.currentTimeMillis();
	
	private int expireTime = Time.DAY;
	
	public AbstractCache(){
		
	}
	
	public AbstractCache(int time){
		this.expireTime = time;
	}
	
	public boolean isOver(){
		return (createTime + expireTime) < System.currentTimeMillis();
	}
}
