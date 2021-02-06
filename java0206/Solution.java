package java0206;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String str = sc.nextLine();
            System.out.println(jisuan(str));
        }
    }

    private static long jisuan(String str) {
        long res = 0;
        for(int i = 2; i < str.length();i++){
            int n = 0;
            char c = str.charAt(i);
            if(c >= 'A' && c <= 'F'){
                n = c - 'A' + 10;
            }else if(c >= 'a' && c <= 'z'){
                n = c - 'a' + 10;
            }else{
                n = c - '0';
            }
            res = res *16 + n;
        }
        return res;
    }
}
