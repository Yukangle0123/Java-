package model;

import Util.OrderSystemException;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DishDao {
    // 操作菜品表.
// 1. 新增菜品
    public void add(Dish dish) throws OrderSystemException {
        Connection connection=DBUtil.getConnection();
        String sql="insert into dish values(null,?,?)";
        PreparedStatement statement=null;
        try {
            statement=connection.prepareStatement(sql);
            statement.setString(1,dish.getName());
            statement.setInt(2,dish.getPrice());
            int res = statement.executeUpdate();
            if(res!=1){
                throw new OrderSystemException("res!=-1:新增菜品失败");
            }
//            System.out.println("新增菜品成功");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new OrderSystemException("catch:新增菜品失败");
        }finally {
            DBUtil.close(connection,statement,null);
        }
    }
    // 2. 删除菜品
    public void deleteDish(int dishId) throws OrderSystemException {
        Connection connection=DBUtil.getConnection();
        String sql="delete from dish where dishId=?";
        PreparedStatement statement=null;
        try {
            statement=connection.prepareStatement(sql);
            statement.setInt(1,dishId);
            int res = statement.executeUpdate();
            if(res!=1){
                throw new OrderSystemException("res!=-1:删除菜品失败");
            }
//            System.out.println("删除菜品成功");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new OrderSystemException("catch:删除菜品失败");
        }finally {
            DBUtil.close(connection,statement,null);
        }
    }
    // 3. 查询所有菜品
    public List<Dish> selectAllDish() throws OrderSystemException {
        List<Dish>dishes=new ArrayList<>();
        Connection connection=DBUtil.getConnection();
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
            e.printStackTrace();
            throw new OrderSystemException("catch:获取全部菜品失败");
        }finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return dishes;
    }
    // 4. 查询指定菜品
    public Dish selectByDishId(int dishId) throws OrderSystemException {
        Connection connection=DBUtil.getConnection();
        String sql="select * from dish where dishId=?";
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
            throw new OrderSystemException("按照 id 查找菜品出错");
        }finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return null;
    }
    // 修改菜品信息, 也是可以支持的. (主要就是改价格)
    public void updateDishPrice(int dishId){
        //TODO
    }

    public static void main(String[] args) throws OrderSystemException {
        DishDao dishDao=new DishDao();
        Dish dish=new Dish();
        dish.setName("红烧肉");
        dish.setPrice(3000);
        dishDao.add(dish);
//        dishDao.deleteDish(3);
//        System.out.println(dishDao.selectAllDish());
//        System.out.println(dishDao.selectByDishId(2));
    }
}
