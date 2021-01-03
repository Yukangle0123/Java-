package api;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.User;
import model.UserDao;
import util.OrderSystemException;
import util.OrderSystemUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private Gson gson=new GsonBuilder().create();
    static class Request{
        public String name;
        public String password;
    }
    static class Response{
        public int ok;
        public String message;
        public String name;
        public int isAdmin;

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        Response response=new Response();
        try{
            String body= OrderSystemUtil.readBody(req);
            Request request=gson.fromJson(body,Request.class);

            UserDao userDao=new UserDao();
            User user = userDao.selectByName(request.name);
            if(user==null||!user.getPassword().equals(request.password)){
                throw new OrderSystemException("用户名或者密码错误");
            }
            //获取请求中的session  如果没有则创建
            HttpSession session=req.getSession(true);

            session.setAttribute("user",user);
            response.ok=1;
            response.message="";
            response.name=user.getName();
            response.isAdmin=user.getIsAdmin();

        }catch (OrderSystemException e){
            response.ok=0;
            response.message=e.getMessage();
        }finally {
            String jsonString=gson.toJson(response);
            resp.setContentType("application/json; charset=utf-8");
            resp.getWriter().write(jsonString);
        }
    }
//检查登录状态
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Response response=new Response();
        try{
            HttpSession session=req.getSession(false);
            if(session==null){
                throw new OrderSystemException("当前未登录");
            }
            User user=(User)session.getAttribute("user");
            if(user==null){
                throw new OrderSystemException("当前未登录");
            }
            response.ok=1;
            response.message="";
            response.name=user.getName();
            response.isAdmin=user.getIsAdmin();
        }catch(OrderSystemException e){
            response.ok=0;
            response.message=e.getMessage();
        }finally {
            String jsonString =gson.toJson(response);
            resp.setContentType("application/json; charset=utf-8");
            resp.getWriter().write(jsonString);
        }
    }
}
