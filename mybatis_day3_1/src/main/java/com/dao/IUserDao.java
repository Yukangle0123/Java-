package com.dao;

import com.domain.QueueVo;
import com.domain.User;
import java.util.*;

public interface IUserDao {
    /**
     * 根据条件查询
     * @return
     */
    List<User> findUserByCondition(User user);

    List<User> findInIds(QueueVo vo);
}
