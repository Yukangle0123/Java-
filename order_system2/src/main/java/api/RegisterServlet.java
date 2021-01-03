package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.User;
import model.UserDao;
import util.OrderSystemException;
import util.OrderSystemUtil;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private Gson gson=new GsonBuilder().create();
    static class Request{
        public String name;
        public String password;
    }
    static class Response{
        public int ok;
        public String message;
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
       req.setCharacterEncoding("utf-8");
       Response response=new Response();
       try{
           String body=OrderSystemUtil.readBody(req);
           Request request=gson.fromJson(body,Request.class);
           UserDao userDao=new UserDao();
           User existsUser=userDao.selectByName(request.name);
           if(existsUser!=null){
               throw new OrderSystemException("当前用户名已经存在");
           }
           if(request.password.equals("")||request.name.equals("")){
               throw new OrderSystemException("用户名或密码为空");
           }
           User user=new User();
           user.setName(request.name);
           user.setPassword(request.password);
           userDao.add(user);
           response.ok=1;
           response.message="";
       } catch (OrderSystemException e) {
           response.ok=0;
           response.message=e.getMessage();
       }finally {
           String JsonString =gson.toJson(response);
           resp.setContentType("application/json; charset=utf-8");
           resp.getWriter().write(JsonString);
       }
    }
}
