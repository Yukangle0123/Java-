package java_0129.meiriyiti;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int n = sc.nextInt();
            float res = fun(n) *100;
            System.out.println(String.format("%.2f",res)+"%");
        }
    }

    private static float fun(int n) {
        return fun1(n)/fun2(n);
    }

    private static float fun1(int n) {
        if(n == 1){
            return 0;
        } else if(n == 2){
            return 1;
        }else{
            return (n-1)*(fun1(n-1)+fun1(n-2));
        }

    }

    private static float fun2(int n) {
        if(n == 1){
            return 1;
        }
        return n * fun2(n-1);
    }
}
