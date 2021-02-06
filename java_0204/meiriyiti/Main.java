package java_0204.meiriyiti;

import com.sun.corba.se.impl.interceptors.PICurrent;

import java.util.Scanner;

import static java.lang.Math.PI;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int x0 = sc.nextInt();
            int y0 = sc.nextInt();
            int z0 = sc.nextInt();
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int z1 = sc.nextInt();
            double r = 0.0;
            double v = 0.0;
            r = Math.sqrt(Math.pow(x0-x1,2)+Math.pow(y0-y1,2)+Math.pow(z0-z1,2));
            v = Math.pow(r,3)* PI *(4.0/3);
            System.out.format("%.3f %.3f\n",r,v);
        }
    }
}
