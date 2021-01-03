package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import util.OrderSystemException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/logout")
public class Logout extends HttpServlet {
    private Gson gson=new GsonBuilder().create();
    static class Response{
        public int ok;
        public String message;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Response response=new Response();
        try{
            HttpSession session=req.getSession(false);
            if(session==null){
                throw new OrderSystemException("尚未登录");
            }
            session.removeAttribute("user");
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
