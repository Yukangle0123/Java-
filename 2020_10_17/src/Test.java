import java.util.LinkedList;
import java.util.List;

public class Test {
    public static long pow(int n,int p){
        long num=1;
        for(int i=p;i>0;i-- ){
            num*=n;
        }
        return num;
    }
    static long num=0;
//   // public static long hexTo10进制(String hexString){
//        char[] chars = hexString.toCharArray();
//
//    }
}
