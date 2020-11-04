package leetcode_29;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int divide(int dividend,int divisor){
        if(dividend==Integer.MIN_VALUE&&divisor==-1){
            return Integer.MAX_VALUE;
        }
       boolean sign=(dividend>0&&divisor>0)||(dividend<0&&divisor<0);
       dividend=-Math.abs(dividend);
       divisor=-Math.abs(divisor);
       int ans=0;
       while(dividend<=divisor){
           int temp=divisor;
           int c=1;
           while(dividend-temp<=temp) {
               temp=temp<<1;
               c=c<<1;
           }
           dividend-=temp;
           ans+=c;


       }
       return sign?ans:-ans;

    }

    public static void main(String[] args) {
        Solution s=new Solution();
        System.out.println(s.divide(-2147483648, 2));
    }
}
