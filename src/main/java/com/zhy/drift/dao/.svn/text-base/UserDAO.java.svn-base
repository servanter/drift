package com.zhy.drift.dao;

import java.util.List;

import com.zhy.drift.bean.User;
import com.zhy.drift.exception.DAOException;

/**
 * 用户信息DAO层
 * @author zhy
 *
 */
public interface UserDAO {

    /**
     * 根据微信名获取该用户
     * @param wxUserName
     * @return
     */
    public User getUserByWxUserName(String wxUserName);

    /**
     * 存储用户信息
     * @param phone
     * @return
     */
    public int save(User user) throws DAOException;

    /**
     * 根据昵称获取用户
     * @param name
     * @return
     */
    public List<User> getUserByName(String name);

    /**
     * 更新用户可用性
     * @param isAvailable
     * @param phone
     * @return
     */
    public int updateUserAvailable(boolean isAvailable, String phone);

    public User getUserById(Long id);

    public List<User> getRobots();

}
