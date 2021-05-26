package com.dao;

import com.domain.User;

import java.util.*;
public interface IAccountDao {
    /**
     * 查询所有账户同时获得 该账户的所属人的姓名和地址
     * @return
     */
    List<User> findAll();
}
