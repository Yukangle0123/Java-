package com.domain;
import com.dao.IUserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class test {
    public static void main(String[] args) throws IOException {
        InputStream in = Resources.getResourceAsStream("");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        System.out.println();
    }
}
