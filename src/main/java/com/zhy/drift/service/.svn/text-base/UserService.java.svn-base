package com.zhy.drift.service;

import java.util.List;

import com.zhy.drift.bean.User;

/**
 * 用户业务层
 * @author zhy
 *
 */
public interface UserService {

    /**
     * 是否存在该用户
     * @param phone
     */
    public boolean isExisted(String phone);

    /**
     * 根据电话号码获取该用户
     * @param wxUserName
     * @return
     */
    public User getUserByWxUserName(String wxUserName);
    
    /**
     * 根据用户id获取用户信息
     * 
     * @param id
     * @return
     */
    public User getUserById(Long id);

    /**
     * 根据代号返回该用户电话
     * @param name
     * @return phone
     */
    public User getUserByName(String name);

    /**
     * 存储用户信息
     * @param phone
     * @return
     */
    public boolean save(User user);

    /**
     * 激活用户
     * @param message
     * @return
     */
    public boolean updateUserByValid(User user);

    /**
     * 退订用户
     * @param message
     * @return
     */
    public boolean updateUserByInValid(User user);
    
    /**
     * 获取机器人
     * @return
     */
    public List<User> getRobots();
}
