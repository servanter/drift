package com.zhy.drift.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.zhy.drift.bean.User;
import com.zhy.drift.dao.UserDAO;
import com.zhy.drift.exception.DAOException;

public class UserDAOImpl implements UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User getUserByWxUserName(String wxUserName) {
        String sql = "SELECT * FROM `user` WHERE `wx_user_name` = ?";
        try {

            List<User> users = jdbcTemplate.query(sql, new Object[] { wxUserName }, new UserMapper());
            if (users != null && users.size() > 0) {
                return users.get(0);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getUserByName(String name) {
        return jdbcTemplate.query("SELECT * FROM `user` WHERE `name` = ?", new Object[] { name }, new UserMapper());
    }

    @Override
    public int save(User user) throws DAOException {
        // need calculate name from wx name
        String sql = "INSERT INTO `user`(`name`,`wx_user_name`,`sex`,`age`,`city`) VALUES(?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, new Object[] { user.getName(), user.getWxUserName(), user.getSex(),
                user.getAge(), user.getCity() });
    }

    @Override
    public int updateUserAvailable(boolean isAvailable, String wxUserName) {
        String sql = "UPDATE `user` set `is_valid` = ? WHERE `wx_user_name` = ?";
        return jdbcTemplate.update(sql, new Object[] { isAvailable, wxUserName });
    }

    @Override
    public User getUserById(Long id) {
        String sql = "SELECT * FROM `user` WHERE `id` = ?";
        try {
            List<User> users = jdbcTemplate.query(sql, new Object[] { id }, new UserMapper());
            if (users != null && users.size() > 0) {
                return users.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getRobots() {
        return jdbcTemplate.query("SELECT * FROM `user` WHERE is_robot = ?", new Object[] { 1 }, new UserMapper());
    }

}

class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int arg1) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        // not null
        user.setName(rs.getString("name").toString());
        user.setWxUserName(rs.getString("wx_user_name").toString());
        user.setAge(rs.getInt("age"));
        user.setSex(rs.getString("sex"));
        user.setCity(rs.getString("city"));
        user.setIsValid(rs.getBoolean("is_valid"));
        user.setIsRobot(rs.getBoolean("is_robot"));
        return user;
    }

}
