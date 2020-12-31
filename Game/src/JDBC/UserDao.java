package JDBC;

import util.GameException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    //注册
    public void add(User user) throws GameException {
        Connection connection= DBUtil.getConnection();
        String sql="insert into user values(null,?,?)";
        PreparedStatement statement=null;
        try {
             statement=connection.prepareStatement(sql);
             statement.setString(1,user.getName());
             statement.setString(2,user.getPassword());
            int ret = statement.executeUpdate();
            if(ret!=1){
                throw new GameException("新建用户失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,statement,null);
        }
    }
    //通过用户名查找用户(登录)
    public User selectByName(String useName) throws GameException {
        Connection connection=DBUtil.getConnection();
        String sql="select * from user where userName=?";
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try{
            statement=connection.prepareStatement(sql);
            statement.setString(1,useName);
            resultSet=statement.executeQuery();
            if(resultSet.next()){
                User user=new User();
                user.setUserId(resultSet.getInt("userId"));
                user.setName(resultSet.getString("userName"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        }catch (SQLException e){
            throw new GameException("查找用户失败");
        }finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return null;

    }
    public void addScore(int score) throws GameException {
        Connection connection=DBUtil.getConnection();
        String sql="insert into scores values(?)";
        PreparedStatement statement=null;
        try {
            statement=connection.prepareStatement(sql);
            statement.setInt(1,score);
            int ret=statement.executeUpdate();
            if(ret!=1){
                throw new GameException("插入成绩失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,statement,null);
        }
    }
    public List<Integer> selectAllLevel(){
        Connection connection=DBUtil.getConnection();
        String sql="select distinct * from scores  order by score desc limit 5 ";
        PreparedStatement statement=null;
        List<Integer>scores=new ArrayList<>();
        ResultSet resultSet=null;
        try{
            statement=connection.prepareStatement(sql);
            resultSet=statement.executeQuery();
            while(resultSet.next()){
                scores.add(resultSet.getInt("score"));
            }
            return scores;

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return scores;
    }
}
