package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.*;
import util.OrderSystemException;
import util.OrderSystemUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    Gson gson=new GsonBuilder().create();
    static class Response{
        public int ok;
        public String message;
    }
    //新增订单

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Response response = new Response();
        req.setCharacterEncoding("utf-8");
        try {
            // 1. 检查用户登陆状态.
            HttpSession session = req.getSession(false);
            if (session == null) {
                throw new OrderSystemException("您尚未登陆");
            }
            User user = (User) session.getAttribute("user");
            if (user == null) {
                throw new OrderSystemException("您尚未登陆");
            }
            // 2. 判断用户是否是管理员
            if (user.getIsAdmin() == 1) {
                // 管理员, 就禁止新增订单
                throw new OrderSystemException("您是管理员");
            }
            // 3. 读取 body 中的数据, 进行解析.
            String body = OrderSystemUtil.readBody(req);
            // 4. 按照 JSON 格式解析 body
            Integer[] dishIds = gson.fromJson(body, Integer[].class);
            Order order = new Order();
            order.setUserId(1);
            DishDao dishDao=new DishDao();
            List<Dish> dishes = new ArrayList<>();
            for (Integer dishId : dishIds) {
                Dish dish = dishDao.selectDishById(dishId);
                dishes.add(dish);
            }
            order.setDishes(dishes);
            // 6. 把 Order 对象插入到数据库中
            OrderDao orderDao = new OrderDao();
            orderDao.add(order);
            response.ok = 1;
            response.message = "";
        } catch (OrderSystemException e) {
            response.ok = 0;
            response.message = e.getMessage();
        } finally {
            resp.setContentType("application/json; charset=utf-8");
            String jsonString = gson.toJson(response);
            resp.getWriter().write(jsonString);
        }
    }

    //查看订单

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        List<Order>orders =new ArrayList<>();
        resp.setContentType("application/json; charset=utf-8");
        try{
            //检查登陆状态
            HttpSession session = req.getSession(false);
            if(session == null){
                throw new OrderSystemException("尚未登陆");
            }
            User user = (User)session.getAttribute("user");
            if(user == null){
                throw new OrderSystemException("尚未登陆");
            }
            //已经登陆
            String orderIdStr = req.getParameter("orderId");
            OrderDao orderDao = new OrderDao();
            if(orderIdStr==null){
                //orderIdStr==null说明是在查看订单
                //管理员允许查看所有的订单
                boolean flg=false;
                if(user.getIsAdmin() == 1){
                    //非管理员
                    //只允许查看自己的订单
                    orders = orderDao.selectAllOrder();
                }else{
                    orders=orderDao.selectByUserId(user.getUserId());
                }
//                Response response=new Response();
//                response.ok=0;
//                response.message=flg+" "+user.getIsAdmin();
//                String jsonString = gson.toJson(response);
                String jsonString = gson.toJson(orders);
                resp.getWriter().write(jsonString);
            }else{
                int orderId=Integer.parseInt(orderIdStr);
                Order order = orderDao.selectByOrderId(orderId);
                if(user.getIsAdmin() == 0 && user.getUserId()!= order.getUserId()){
                    throw new OrderSystemException("您的权限不够");
                }
                String jsonString = gson.toJson(order);
                resp.getWriter().write(jsonString);
            }
        }catch (OrderSystemException o){
//            Response response=new Response();
//            response.ok=0;
//            response.message=o.getMessage();
//            String jsonString = gson.toJson(response);
            String jsonString = gson.toJson(orders);
            resp.getWriter().write(jsonString);
        }
    }

    //修改订单状态

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Response response=new Response();
        String isDoneStr="0";
        String orderIdStr="2";
        try{
            HttpSession session=req.getSession(false);
            if(session==null){
                throw new OrderSystemException("您尚未登录");
            }
            User user=(User)session.getAttribute("user");
            if(user==null){
                throw new OrderSystemException("您尚未登录");
            }
            //判断是否为管理员
            if(user.getUserId()==0){
                throw new OrderSystemException("您不是管理员");
            }
            //读取请求中的字段
            orderIdStr = req.getParameter("orderId");
            isDoneStr = req.getParameter("isDone");
            if(orderIdStr==null||isDoneStr==null){
                throw new OrderSystemException("参数有误");
            }
            OrderDao orderDao=new OrderDao();
            int orderId=Integer.parseInt(orderIdStr);
            int isDon=Integer.parseInt(isDoneStr);
            orderDao.changeState(orderId,isDon);
            response.ok=1;
            response.message="";
        }catch (OrderSystemException e){
            response.ok=0;
            response.message=e.getMessage();
        }finally {
            String jsonString=gson.toJson(response);
            resp.setContentType("application/json; charset=utf-8");
            resp.getWriter().write(jsonString);
        }
    }
}
