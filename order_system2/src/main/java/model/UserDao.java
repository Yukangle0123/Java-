package model;

import util.OrderSystemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    //用户注册
    public void add(User user) throws OrderSystemException {
        Connection connection=DBUtil.getConnection();
        String sql="insert into user values(null,?,?,?)";
        PreparedStatement statement=null;
        try {
            statement=connection.prepareStatement(sql);
            statement.setString(1,user.getName());
            statement.setString(2,user.getPassword());
            statement.setInt(3,0);
            int ret=statement.executeUpdate();
            if(ret!=1){
                throw new OrderSystemException("ret!=1:注册失败");
            }

        } catch (SQLException e) {
            throw new OrderSystemException("catch:注册失败");
        }finally {
            DBUtil.close(connection,statement,null);
        }
    }
    //用户登录
    public User selectByName(String name){
        Connection connection=DBUtil.getConnection();
        String sql="select * from user where userName=?";
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try {
            statement=connection.prepareStatement(sql);
            statement.setString(1,name);
            resultSet=statement.executeQuery();
            if(resultSet.next()){
                User user=new User();
                user.setUserId(resultSet.getInt("userId"));
                user.setName(resultSet.getString("userName"));
                user.setPassword(resultSet.getString("password"));
                user.setIsAdmin(resultSet.getInt("isAdmin"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return null;
    }

    public static void main(String[] args) throws OrderSystemException {
        UserDao userDao=new UserDao();
        User user=new User();
        user.setName("于康乐");
        user.setPassword("xiaoyu");
        user.setIsAdmin(1);
        userDao.add(user);
    }
}
