package model;

import util.OrderSystemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    public void add(Order order) throws OrderSystemException{
        addOrderUser(order);
        addOrderDish(order);
    }
    //往order_dish中添加数据
    private void addOrderDish(Order order) throws OrderSystemException{
        Connection connection=DBUtil.getConnection();
        String sql="insert into order_dish values(?,?,?,?)";
        PreparedStatement statement=null;
        try{
            connection.setAutoCommit(false);
            statement=connection.prepareStatement(sql);
            List<Dish> dishes= order.getDishes();
            for(Dish dish:dishes){
                statement.setInt(1,order.getOrderId());
                statement.setInt(2,dish.getDishId());
                statement.setString(3,dish.getName());
                statement.setInt(4,dish.getPrice());
                statement.addBatch();
            }
            statement.executeBatch();
            connection.commit();//该代码提交sql并执行
        }catch (SQLException e){
            e.printStackTrace();
            deleteOrderUser(order.getOrderId());
        }finally {
            DBUtil.close(connection,statement,null);
        }
    }
    //回滚操作
    private void deleteOrderUser(int orderId) throws OrderSystemException {
        Connection connection=DBUtil.getConnection();
        String sql="delete from order_user where orderId=?";
        PreparedStatement statement=null;
        try{
            statement=connection.prepareStatement(sql);
            statement.setInt(1,orderId);
            int ret=statement.executeUpdate();
            if(ret!=1){
                throw new OrderSystemException("ret!=1:回滚失败");
            }
        }catch (SQLException e){
            throw new OrderSystemException("catch:回滚失败");
        }
    }
    //往order_user中添加数据
    private void addOrderUser(Order order) throws OrderSystemException {
        Connection connection=DBUtil.getConnection();
//        String sql="insert into order_user values(null,?,now(),0)";
        String sql ="insert into order_user values (null,?,now(),0)";
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try {
            //PreparedStatement.RETURN_GENERATED_KEYS
            //该参数能获取自增长的值，order_user表中 orderId是一个自增长的值
            statement=connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setInt(1,order.getUserId());
            int ret=statement.executeUpdate();
            if(ret!=1){
                throw new OrderSystemException("ret!=1:添加订单失败");
            }
            resultSet=statement.getGeneratedKeys();
            if(resultSet.next()){
                order.setOrderId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new OrderSystemException("添加order_user失败");
        }finally {
            DBUtil.close(connection,statement,resultSet);
        }
    }
    //查询所有的订单
    public List<Order>selectAllOrder() {
        List<Order>orders=new ArrayList<>();
        Connection connection=DBUtil.getConnection();
        String sql="select * from order_user";
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try{
            statement=connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                Order order=new Order();
                order.setOrderId(resultSet.getInt("orderId"));
                order.setUserId(resultSet.getInt("userId"));
                order.setTime(resultSet.getTimestamp("time"));
                order.setIsDone(resultSet.getInt("IsDone"));
                orders.add(order);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return orders;
    }

    //查询某一用户的所有订单
    public List<Order> selectByUserId(int userId) throws OrderSystemException {
        Connection connection=DBUtil.getConnection();
        List<Order>orders=new ArrayList<>();
        String sql="select * from order_user where userId=?";
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try{
            statement=connection.prepareStatement(sql);
            statement.setInt(1,userId);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                Order order=new Order();
                order.setOrderId(resultSet.getInt("orderId"));
                order.setUserId(resultSet.getInt("userId"));
                order.setTime(resultSet.getTimestamp("time"));
                order.setIsDone(resultSet.getInt("isDone"));
                orders.add(order);
            }
        }catch (SQLException e){
            throw new OrderSystemException("查询订单失败");
        }
        return orders;
    }
    //查询某一订单的详细信息
    public Order selectByOrderId(int orderId) throws OrderSystemException {
        Order order=buildOrder(orderId);
        if(order==null){
            throw new OrderSystemException("获取Order失败");
        }
        List<Dish>dishes=selectDishList(order.getOrderId());
        order.setDishes(dishes);
        return order;
    }

    private List<Dish> selectDishList(int orderId) throws OrderSystemException {
        List<Dish>dishes=new ArrayList<>();
        Connection connection=DBUtil.getConnection();
        String sql="select * from order_dish where orderId=?";
        ResultSet resultSet=null;
        PreparedStatement statement=null;
        try{
            statement=connection.prepareStatement(sql);
            statement.setInt(1,orderId);
            resultSet=statement.executeQuery();
            while(resultSet.next()){
                Dish dish=new Dish();
                dish.setDishId(resultSet.getInt("dishId"));
                dish.setName(resultSet.getString("name"));
                dish.setPrice(resultSet.getInt("price"));
                dishes.add(dish);
            }

        }catch (SQLException e){
            throw new OrderSystemException("获取菜品列表失败");
        }finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return dishes;
    }

    private Order buildOrder(int orderId) throws OrderSystemException {
        Connection connection=DBUtil.getConnection();
        String sql ="select * from order_user where orderId=?";
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try{
            statement=connection.prepareStatement(sql);
            statement.setInt(1,orderId);
            resultSet=statement.executeQuery();
            if(resultSet.next()){
                Order order=new Order();
                order.setOrderId(orderId);
                order.setUserId(resultSet.getInt("userId"));
                order.setTime(resultSet.getTimestamp("time"));
                order.setIsDone(resultSet.getInt("isDone"));
                return order;
            }
        }catch (SQLException e){
            throw new OrderSystemException("构建Order对象失败");
        }finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return null;
    }
    //修改订单的状态
    public void changeState(int orderId,int state ) throws OrderSystemException {
        Connection connection=DBUtil.getConnection();
        String sql="update order_user set isDone=? where orderId=?";

        PreparedStatement statement=null;
        try{
            statement=connection.prepareStatement(sql);
            statement.setInt(1,state);
            statement.setInt(2,orderId);
            int ret=statement.executeUpdate();
            if(ret!=1){
                throw new OrderSystemException("ret!=1:修改订单状态失败");
            }
        }catch (SQLException e){
            throw new OrderSystemException("修改订单状态失败!");
        }finally {
            DBUtil.close(connection,statement,null);
        }
    }

    public static void main(String[] args) throws OrderSystemException {

//        System.out.println(orderDao.selectDish(1));
        OrderDao orderDao=new OrderDao();
//        List<Dish> dishes = orderDao.selectDishList(6);
//        System.out.println(dishes);
//        Order order = orderDao.selectByOrderId(6);
//        System.out.println(order);
        List<Order> orders = orderDao.selectByUserId(1);
        System.out.println(orders);
        List<Order> orders1 = orderDao.selectAllOrder();
        System.out.println(orders1);
    }

}
