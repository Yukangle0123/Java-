package leetcode;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.HashMap;
import java.util.Random;

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s.length()==0){
            return 0;
        }
        HashMap<Character,Integer> hm=new HashMap<>();
        int max=0;
        int left=0;//左边的窗
        for(int i=0;i<s.length();i++){
            if(hm.containsKey(s.charAt(i))){
                left=Math.max(left,hm.get(s.charAt(i))+1);
            }
            hm.put(s.charAt(i),i);//abba
            max=Math.max(max,i-left+1);
        }
        return max;
    }
    //babad
    public String longestPalindrome(String s) {
        if(s.length()==0) return s;
        int res=0;
        int start=0;
        int end=0;
        for(int i=0;i<s.length();i++){
            int l=i-1;
            int r=i+1;
            while(l>=0&&r<s.length()&&s.charAt(l)==s.charAt(r)){
                if((r-l+1)>res){
                    res=r-l+1;
                    start=l;
                    end=r;
                }
                l--;
                r++;
            }
            l=i;
            r=i+1;
            while(l>=0&&r<s.length()&&s.charAt(l)==s.charAt(r)){
                if((r-l+1)>res){
                    res=r-l+1;
                    start=l;
                    end=r;
                }
                l--;
                r++;
            }
        }
        return s.substring(start,end+1);
    }
    public int reverse(int x) {
        int newInt=0;
        int pop=0;
        while(x!=0){
            pop=x%10;
            if(newInt>Integer.MAX_VALUE/10||(newInt==Integer.MAX_VALUE&&pop>7))return 0;
            if(newInt<Integer.MIN_VALUE/10||(newInt==Integer.MIN_VALUE&&pop<-8))return 0;
            newInt=newInt*10+pop;
            x/=10;
        }
        return newInt;
    }
    public int myAtoi(String s) {
        int len=s.length();
        char[]chars=s.toCharArray();
        int index=0;
        while(index<len&&chars[index]==' '){
            index++;
        }
        if(index==len){
            return 0;
        }
        int sign=1;
        int res=0;
        int pop=0;
        if(chars[index]=='-'){
            sign=-sign;
            index++;
        }else if(chars[index]=='+'){
            index++;
        }
        for(int i=index;i<chars.length;i++){
            if(chars[i]>'9'||chars[i]<'0'){
                break;
            }
            pop=chars[i]-'0';
            if(res>Integer.MAX_VALUE/10||(res==Integer.MAX_VALUE&&pop>7))return Integer.MAX_VALUE;
            if(res<Integer.MIN_VALUE/10||(res==Integer.MIN_VALUE&&pop<-8))return Integer.MIN_VALUE;
            res=res*10+(pop*sign);
        }
        return res*sign;
    }

    public static void main(String[] args) {
        Solution s=new Solution();
        System.out.println(Integer.MIN_VALUE%10);
        
    }
}
