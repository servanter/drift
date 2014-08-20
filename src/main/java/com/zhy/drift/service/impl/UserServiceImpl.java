package com.zhy.drift.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zhy.drift.bean.User;
import com.zhy.drift.dao.UserDAO;
import com.zhy.drift.exception.DAOException;
import com.zhy.drift.service.UserService;

/**
 * 用户信息业务层
 * 
 * @author zhy
 * 
 */
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public boolean isExisted(String phone) {
        return getUserByWxUserName(phone) != null ? true : false;
    }

    @Override
    public User getUserByWxUserName(String wxUserName) {
        return userDAO.getUserByWxUserName(wxUserName);
    }

    @Override
    public boolean save(User user) {
        if (getUserByName(user.getName()) == null) {
            try {
                if (userDAO.save(user) != 0) {
                    return true;
                }
            } catch (DAOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public User getUserByName(String name) {
        User user = null;
        List<User> users = userDAO.getUserByName(name);
        if (users.size() > 0) {
            user = users.get(0);
        }
        return user;
    }

    @Override
    public boolean updateUserByInValid(User user) {
        return updateUserValid(user.getWxUserName(), false);
    }

    @Override
    public boolean updateUserByValid(User user) {
        return updateUserValid(user.getWxUserName(), true);
    }

    /**
     * 更新用户可用性
     * 
     * @param phone
     * @param isAvailable
     * @return
     */
    private boolean updateUserValid(String phone, boolean isAvailable) {
        if (userDAO.updateUserAvailable(isAvailable, phone) != 0) {
            return true;
        }
        return false;
    }

    @Override
    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public List<User> getRobots() {
        return userDAO.getRobots();
    }
}
