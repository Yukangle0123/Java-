package com.dao;

import com.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IUserDao {
    @Select("select * from user")
    List<User> findAll();
    @Select("select * from user where username=/'老王/'")
    User findOne();
}
