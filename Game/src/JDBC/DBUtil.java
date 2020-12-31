package JDBC;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
    private static final String URL="jdbc:mysql://127.0.0.1:3306/Game?characterEncoding=utf-8&useSSL=false";
    private static final String USER="root";
    private static final String PASSWORD="xiaoyu";

    private static DataSource dataSource=null;

    private static DataSource getDataSource(){
        if(dataSource==null){
            dataSource=new MysqlDataSource();
            ((MysqlDataSource)dataSource).setURL(URL);
            ((MysqlDataSource)dataSource).setUser(USER);
            ((MysqlDataSource)dataSource).setPassword(PASSWORD);
            return dataSource;
        }
        return dataSource;
    }
    public static Connection getConnection(){
        try {
            return DBUtil.getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void close(Connection connection, PreparedStatement statement, ResultSet resultSet) {
            try {
                if (connection != null) {
                    connection.close();
                }
                if(statement!=null){
                    statement.close();
                }
                if(resultSet!=null){
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
