package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Dish;
import model.DishDao;
import model.User;
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

@WebServlet("/dish")
public class DishServlet extends HttpServlet {
    private Gson gson=new GsonBuilder().create();
    //新增菜品
    static class Request{
        public String name;
        public int price;
    }
    static  class Response{
        public int ok;
        public String message;
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        Response response=new Response();
        try{
            //检查登录状态
            HttpSession session=req.getSession(false);
            if(session==null){
                throw new OrderSystemException("您尚未登录");
            }
            User user=(User)session.getAttribute("user");
            if(user==null){
                throw new OrderSystemException("您尚未登录");
            }
            if(user.getIsAdmin()==0){
                throw new OrderSystemException("您不是管理员");
            }
            //获取请求的body
            String body= OrderSystemUtil.readBody(req);
            Request request=gson.fromJson(body,Request.class);
            //构建Dish对象
            Dish dish=new Dish();
            dish.setName(request.name);
            dish.setPrice(request.price);

            DishDao dishDao=new DishDao();
            //添加都数据库中
            dishDao.add(dish);
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

    //查看所有菜品

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Dish>dishes=new ArrayList<>();
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json; charset=utf-8");
        try{
            //检验登录状态
            HttpSession session=req.getSession(false);
            if(session==null){
                throw new OrderSystemException("您尚未登录");
            }
            User user=(User)session.getAttribute("user");
            if(user==null){
                throw new OrderSystemException("您尚未登录");
            }
            DishDao dishDao=new DishDao();
            dishes=dishDao.selectAllDish();
            String jsonString=gson.toJson(dishes);
            resp.getWriter().write(jsonString);
        }catch (OrderSystemException e){
            String jsonString=gson.toJson(dishes);
            resp.getWriter().write(jsonString);
        }
    }


    //删除菜品

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        req.setCharacterEncoding("utf-8");
        Response response=new Response();
        try{
            //检查登录状态
            HttpSession session=req.getSession(false);
            if(session==null){
                throw new OrderSystemException("您尚未登录");
            }
            User user=(User)session.getAttribute("user");
            if(user==null){
                throw new OrderSystemException("您尚未登录");
            }
            if(user.getIsAdmin()==0){
                throw new OrderSystemException("您不是管理员");
            }
            //获取要删除的菜品Id
            String dishIdStr=req.getParameter("dishId");
            if(dishIdStr==null){
                throw new OrderSystemException("参数不正确");
            }
            int dishId=Integer.parseInt(dishIdStr);
            DishDao dishDao=new DishDao();
            dishDao.deleteDish(dishId);
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
