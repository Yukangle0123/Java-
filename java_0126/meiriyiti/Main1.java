package java_0126.meiriyiti;

import java.util.Scanner;

public class Main1 {
    public static int getSum(int num){
        int res = 0;
        while(num > 0){
            res += num % 10;
            num /=10;
        }
        return res;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int num = sc.nextInt();
            int res = getSum(num);
            System.out.println(res+" "+getSum(num*num));
        }
//        System.out.println(16*16);
//        System.out.println(getSum(16*16));
    }
}
