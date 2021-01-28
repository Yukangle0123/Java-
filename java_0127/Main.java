package java_0127;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public int strToInt(String str) {
        str = str.trim();
        int sign = 1;
        int index = 0;
        if(str.charAt(index) == '-'){
            sign = -sign;
            index++;
        }
        int ret = 0;
        for(int i = index; i < str.length(); i++){
            int pop = str.charAt(i) - '0';
            if(pop > 9 || pop < 0){
                break;
            }
            if(ret > Integer.MAX_VALUE/10 ||
                    (ret == Integer.MAX_VALUE/10 && pop > Integer.MAX_VALUE % 10)){
                return Integer.MAX_VALUE;
            }
            if(ret < Integer.MIN_VALUE / 10 ||
                    (ret == Integer.MIN_VALUE /10 ) && pop > -(Integer.MIN_VALUE %10 )){
                return Integer.MIN_VALUE;
            }
            ret = (ret * 10 ) + (sign * pop);
        }
        return ret;
    }
    public int translateNum(int num) {
        String str  = num +"";
        int len = str.length();
        if(len < 2){
            return len;
        }
        char[] chars = str.toCharArray();
        int[] dp = new int[len];
        dp[0] = 1;
        for(int i = 1; i < len; i++){
            dp[i] = dp[i-1];
            int pop = (chars[i-1] - '0')*10 + chars[i]-'0';
            if(pop > 9 && pop < 26){
                if(i - 2 < 0){
                    dp[i]++;
                }else{
                    dp[i] = dp[i-1] + dp[i-2];
                }
            }
        }
        return dp[len-1];
    }
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        int index = 0;
        List<Integer> list = new ArrayList<>();
        while(index < len){
            int max = Integer.MIN_VALUE;
            for(int i = 0; i < 3; i++){
                max = Math.max(max,nums[i]);
            }
            list.add(max);
            index++;
        }
        int[] res = new int[list.size()];
        index = 0;
        for(int n : list){
            res[index++] = n;
        }
        return res;
    }
    public int sumNums(int n) {
        boolean flag = n > 0 && (n += sumNums(n-1))>0;
        return n;
    }
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        char[] chars = s.toCharArray();
        int len = s.length();
        int i = 0;
        int j = len-1;
        while(i < j){
            while(i < j && !Character.isLetter(chars[i]) && !isNum(chars[i])){
                i++;
            }
            while(i < j && !Character.isLetter(chars[j]) && !isNum(chars[j])){
                j--;
            }
            if(i < j && chars[i] != chars[j] ){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    private boolean isNum(char aChar) {
        return aChar <= '9' && aChar >='0';
    }

    public static void main(String[] args) {
        Main m = new Main();
        System.out.println(m.isPalindrome("ab2a"));
    }
}
