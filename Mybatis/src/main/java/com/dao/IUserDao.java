package com.dao;

import com.domain.User;

import java.util.List;

public interface IUserDao {
    //查找所有
    List<User> findAll();
    //增加用户
    void addUser(User user);
    //删除用户
    void deleteUser(Integer id);
    void updateUser(User user);
}
