package model;

import Util.OrderSystemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    // 主要实现三个功能
// 1. 插入用户 - 注册的时候使用
// 2. 按名字查找用户 - 登陆时使用
// 3. 按照用户 id 查找 - 展示信息时使用
    public void add(User user) throws OrderSystemException {
        //获取数据库连接
        Connection connection = DBUtil.getConnection();
        //拼装sql
        String sql="insert into user values (null,?,?,?)";
        PreparedStatement statement =null;
        try {
            statement=connection.prepareStatement(sql);
            statement.setString(1,user.getUserName());
            statement.setString(2,user.getPassword());
            statement.setInt(3,user.getIsAdmin());

            int res = statement.executeUpdate();
            if(res!=1){
                throw new OrderSystemException("res!=1,插入用户失败");
            }
            System.out.println("插入用户成功");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new OrderSystemException("异常处理,插入用户失败");
        }finally {
            DBUtil.close(connection,statement,null);
        }
    }
    //2. 按名字查找用户 - 登陆时使用
    public User selectByName(String name) throws OrderSystemException {
        Connection connection=DBUtil.getConnection();
        String sql="select * from user where name=?";
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try {
            statement=connection.prepareStatement(sql);
            statement.setString(1,name);
            resultSet=statement.executeQuery();
            if(resultSet.next()){
                User user=new User();
                user.setUserId(resultSet.getInt("userId"));
                user.setUserName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setIsAdmin(resultSet.getInt("isAdmin"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new OrderSystemException("按姓名查找用户失败");
        }finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return null;
    }
    public User selectById(int id) throws OrderSystemException {
        Connection connection=DBUtil.getConnection();
        String sql="select * from user where userId=?";
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try {
            statement=connection.prepareStatement(sql);
            statement.setInt(1,id);
            resultSet=statement.executeQuery();
            if(resultSet.next()){
                User user=new User();
                user.setUserId(resultSet.getInt("userId"));
                user.setUserName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setIsAdmin(resultSet.getInt("isAdmin"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new OrderSystemException("按id查找用户失败");
        }finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return null;
    }
    //测试
    public static void main(String[] args) throws OrderSystemException {
        UserDao userDao=new UserDao();
        User user=new User();
        user.setUserName("于康乐");
        user.setPassword("xiaoyu");
        user.setIsAdmin(1);
        userDao.add(user);
//        System.out.println(userDao.selectByName("于康乐"));
//        System.out.println(userDao.selectById(1));
    }
}
