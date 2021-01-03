package model;

import util.OrderSystemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DishDao {
    //添加菜品
    public void add(Dish dish) throws OrderSystemException {
        Connection connection = DBUtil.getConnection();
        String sql="insert into dish values(null,?,?)";
        PreparedStatement statement=null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,dish.getName());
            statement.setInt(2,dish.getPrice());
            int ret=statement.executeUpdate();
            if(ret!=1){
                throw new OrderSystemException("ret!=1:插入菜品失败");
            }
        }catch (SQLException e){
            throw new OrderSystemException("catch:插入菜品失败");
        }finally {
            DBUtil.close(connection,statement,null);
        }
    }
    public Dish selectDishById(int dishId) throws OrderSystemException {
        Connection connection=DBUtil.getConnection();
        String sql="select * from dish where dishId=?";
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try{
            statement=connection.prepareStatement(sql);
            statement.setInt(1,dishId);
            resultSet=statement.executeQuery();
            if(resultSet.next()){
                Dish dish=new Dish();
                dish.setDishId(resultSet.getInt("dishId"));
                dish.setName(resultSet.getString("name"));
                dish.setPrice(resultSet.getInt("price"));
                return dish;
            }
        }catch (SQLException e){
            throw new OrderSystemException("catch:通过Id查找菜品失败");
        }finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return null;
    }
    public List<Dish> selectAllDish() throws OrderSystemException {
        Connection connection=DBUtil.getConnection();
        List<Dish>dishes=new ArrayList<>();
        String sql="select * from dish";
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try {
            statement=connection.prepareStatement(sql);
            resultSet=statement.executeQuery();
            while(resultSet.next()){
                Dish dish=new Dish();
                dish.setDishId(resultSet.getInt("dishId"));
                dish.setName(resultSet.getString("name"));
                dish.setPrice(resultSet.getInt("price"));
                dishes.add(dish);
            }
        } catch (SQLException e) {
            throw new OrderSystemException("catch:获取菜品列表失败");
        }finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return dishes;
    }
    public void deleteDish(int dishId) throws OrderSystemException {
        Connection connection=DBUtil.getConnection();
        String sql="delete from dish where dishId=?";
        PreparedStatement statement=null;
        try{
            statement=connection.prepareStatement(sql);
            statement.setInt(1,dishId);
            int ret=statement.executeUpdate();
            if(ret!=1){
                throw new OrderSystemException("删除菜品失败");
            }
        }catch (SQLException e){
            throw new OrderSystemException("删除菜品失败");
        }finally {
            DBUtil.close(connection,statement,null);
        }
    }

    public static void main(String[] args) throws OrderSystemException {
        DishDao dishDao=new DishDao();
//        Dish dish=new Dish();
//        dish.setName("红烧茄子");
//        dish.setPrice(1500);
//        dishDao.add(dish);
//        System.out.println(dishDao.selectById(1));
//        System.out.println(dishDao.selectById(2));
//        System.out.println(dishDao.selectById(3));

    }
}
