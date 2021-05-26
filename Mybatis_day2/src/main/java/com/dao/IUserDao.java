package com.dao;

import com.domain.QueryVo;
import com.domain.User;

import java.util.List;

public interface IUserDao {
    /**
     * 查询所有
     * @return
     */
    List<User> findAll();

    /**
     * 插入用户
     * @param user
     */
    boolean addUser(User user);

    /**
     * 通过Id查询
     * @param id
     * @return
     */
    User selectById(int id);

    /**
     * 通过Id删除
     * @param id
     * @return
     */
    int deleteById(int id);

    /**
     * 更新
     * @param user
     * @return
     */
    boolean updateUser(User user);

    List<User> selectByName(String name);

    int selectTotal();

    List<User> selectByVo(QueryVo vo);
}
