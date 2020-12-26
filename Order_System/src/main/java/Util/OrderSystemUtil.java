package Util;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class OrderSystemUtil {
    public static String readBody(HttpServletRequest req) throws UnsupportedEncodingException {
        int length=req.getContentLength();
        byte[]buffer=new byte[length];
        try(InputStream inputStream=req.getInputStream()){
            inputStream.read(buffer,0,length);
        }catch (IOException e){
            e.printStackTrace();
        }
        return new String(buffer,"utf-8");
    }
}
