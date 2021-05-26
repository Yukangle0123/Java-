package com.dao;

import javax.management.relation.Role;
import java.util.List;

public interface IRoleDao {
    List<Role> findAll();
}
