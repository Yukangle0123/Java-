package model;

import Util.OrderSystemException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// 操作订单
// 1. 新增订单
// 2. 查看所有订单(管理员, 商家)
// 3. 查看指定用户的订单(普通用户, 顾客)
// 4. 查看指定订单的详细信息
// 5. 修改订单状态(订单是否已经完成)
public class OrderDao {
    // 1. 新增订单
    public void add(Order order) throws OrderSystemException {
        //新增订单涉及到两个表
        //1.order_user
        //2.order_dish  可能插入多条数据  一个订单可能涉及多个菜品
        addOrderUser(order);
        addOrderDish(order);
    }

    private void addOrderDish(Order order) throws OrderSystemException {
        Connection connection=DBUtil.getConnection();
        String sql="insert into order_dish values(?,?)";
        PreparedStatement statement=null;
        try {
            //关闭自动提交
            connection.setAutoCommit(false);
            statement=connection.prepareStatement(sql);
            List<Dish> dishes=order.getDishes();
            for(Dish dish:dishes){
                statement.setInt(1,order.getOrderId());
                statement.setInt(2,dish.getDishId());
                statement.addBatch();//给sql加一个新片段
            }
            statement.executeBatch();
            // 6. 发送给服务器 (真的执行), commit 可以去执行多个 SQL, 一次调用 commit 统一发给服务器.
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            deleteOrderUser(order.getOrderId());
        }finally {
            DBUtil.close(connection,statement,null);
        }
    }

    private void deleteOrderUser(int orderId) throws OrderSystemException {
        Connection connection=DBUtil.getConnection();
        String sql="delete from order_user where orderId=?";
        PreparedStatement statement=null;
        try {
            statement=connection.prepareStatement(sql);
            statement.setInt(1,orderId);
            int res = statement.executeUpdate();
            if(res!=1){
                throw new OrderSystemException("res!=-1:回滚失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new OrderSystemException("catch:回滚失败");
        }finally {
            DBUtil.close(connection,statement,null);
        }
    }

    private void addOrderUser(Order order) throws OrderSystemException {
        Connection connection=DBUtil.getConnection();
        String sql="insert into order_user values(null,?,now(),0)";
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try {
            statement=connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1,order.getUserId());
            int ret=statement.executeUpdate();
            if(ret!=1){
                throw new OrderSystemException("ret!=1:addOrderUser失败");
            }
            resultSet=statement.getGeneratedKeys();
            if(resultSet.next()){
                // 理解参数 1. 读取 resultSet 的结果时, 可以使用列名, 也可以使用下标.
                // 由于一个表中的自增列可以有多个. 返回的时候都返回回来了. 下标填成 1
                // 就表示想获取到第一个自增列生成的值.
                order.setOrderId(resultSet.getInt(1));
            }
//            System.out.println("addOrderUser成功");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new OrderSystemException("catch:addOrderUser失败");
        }finally {
            DBUtil.close(connection,statement,resultSet);
        }
    }
    //查看所有订单
    public List<Order> selectAllOrder(){
        List<Order>orders=new ArrayList<>();
        Connection connection=DBUtil.getConnection();
        String sql="select * from order_user";
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try {
            statement=connection.prepareStatement(sql);
            resultSet=statement.executeQuery();
            while(resultSet.next()){
                Order order=new Order();
                order.setOrderId(resultSet.getInt("orderId"));
                order.setUserId(resultSet.getInt("userId"));
                order.setTime(resultSet.getTimestamp("time"));
                order.setIsDone(resultSet.getInt("isDone"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return orders;
    }
    // 3. 查看指定用户的订单(普通用户, 顾客)
    public List<Order> selectByUserId(int userId){
        List<Order>orders=new ArrayList<>();
        Connection connection=DBUtil.getConnection();
        String sql="select * from order_user where userId=?";
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try {
            statement=connection.prepareStatement(sql);
            statement.setInt(1,userId);
            resultSet=statement.executeQuery();
            while(resultSet.next()){
                Order order=new Order();
                order.setOrderId(resultSet.getInt("orderId"));
                order.setUserId(resultSet.getInt("userId"));
                order.setTime(resultSet.getTimestamp("time"));
                order.setIsDone(resultSet.getInt("isDone"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return orders;
    }
    // 4. 查看指定订单的详细信息
    public Order selectByOrderId(int orderId) throws OrderSystemException {
        //先根据orderId获取到一个OrderId
        Order order=buildOrder(orderId);
        //根据orderId获取dishId列表
        List<Integer>dishIdes=selectByDishId(orderId);

        order=getTotalInf(order,dishIdes);
        return order;
    }

    private Order getTotalInf(Order order, List<Integer> dishIdes) throws OrderSystemException {
        List<Dish>dishes=new ArrayList<>();
        DishDao dishDao=new DishDao();
        for(Integer e:dishIdes){
            dishes.add(dishDao.selectByDishId(e));
        }
        order.setDishes(dishes);
        return order;
    }

    private List<Integer> selectByDishId(int orderId) {
        List<Integer>dishes=new ArrayList<>();
        Connection connection=DBUtil.getConnection();
        String sql="select * from order_dish where dishId=?";
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try {
            statement=connection.prepareStatement(sql);
            statement.setInt(1,orderId);
            resultSet=statement.executeQuery();
            while(resultSet.next()){
               dishes.add(resultSet.getInt("dishId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return dishes;
    }

    private Order buildOrder(int orderId) {
        Connection connection=DBUtil.getConnection();
        String sql="select * from order_user";
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try {
            statement=connection.prepareStatement(sql);
            resultSet=statement.executeQuery();
            if(resultSet.next()){
                Order order=new Order();
                order.setOrderId(resultSet.getInt("orderId"));
                order.setUserId(resultSet.getInt("userId"));
                order.setTime(resultSet.getTimestamp("time"));
                order.setIsDone(resultSet.getInt("isDone"));
                return order;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return null;
    }
    public void changeState(int orderId,int isDon) throws OrderSystemException {
        Connection connection=DBUtil.getConnection();
        String sql="update order_user set isDone=? where orderId=?";
        PreparedStatement statement=null;

        try {
            statement=connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1,isDon);
            statement.setInt(2,orderId);
            int ret=statement.executeUpdate();
            if(ret!=1){
                throw new OrderSystemException("ret!=1:changeState失败");
            }
 //            System.out.println("changeState成功");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new OrderSystemException("catch:changeState失败");
        }finally {
            DBUtil.close(connection,statement,null);
        }
    }

    public static void main(String[] args) throws OrderSystemException {
//        List<Dish>dishes=new ArrayList<>();
//        Dish dish=new Dish();
//        dish.setDishId(1);
//        dish.setName("红烧肉");
//        dish.setPrice(3000);
//        dishes.add(dish);
//        Order order=new Order();
//        order.setUserId(1);
//        order.setDishes(dishes);
        OrderDao dao=new OrderDao();
//        dao.add(order);
//        System.out.println(dao.selectAllOrder());
//        System.out.println(dao.selectByOrderId(5));
//        System.out.println(dao.selectByUserId(1));
        dao.changeState(5,1);
    }

}
