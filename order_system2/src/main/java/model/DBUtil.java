package model;



import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
    private static final String URL="jdbc:mysql://127.0.0.1:3306/order_system2?characterEncoding=utf8&useSSL=false";
    private static final String USER="root";
    private static final String PASSWORD="";

    private static DataSource dataSource=null;
    private static DataSource getDataSource(){
        if(dataSource==null){
            synchronized (DBUtil.class){
                if(dataSource==null){
                    dataSource= new MysqlDataSource();
                    ((MysqlDataSource)dataSource).setURL(URL);
                    ((MysqlDataSource)dataSource).setUser(USER);
                    ((MysqlDataSource)dataSource).setPassword(PASSWORD);
                    return dataSource;
                }
            }
        }
        return dataSource;
    }
    public static Connection getConnection(){
        try {
            Connection connection = DBUtil.getDataSource().getConnection();
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void close(Connection connection, PreparedStatement statement, ResultSet resultSet){
        try{
            if(resultSet!=null){
                resultSet.close();
            }
            if(statement!=null){
                statement.close();
            }
            if(connection!=null){
                connection.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
