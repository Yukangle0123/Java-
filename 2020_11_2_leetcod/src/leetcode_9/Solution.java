package leetcode_9;

public class Solution {
    public boolean isPalindrome1(int x) {
        if(x<0){
            return false;
        }
        String s=x+"";
        char[] chars = s.toCharArray();
        int left=0;
        int right=chars.length;
        while(left!=right){
            if(chars[left]==chars[right]){
                left++;
                right--;
            }else{
                return false;
            }
        }
        return true;
    }
    public boolean isPalindrome(int x){
        if(x<0||(x%10==0&&x!=0)){
            return false;
        }
        int reserveLast=0;
        while(x>reserveLast){
            reserveLast=reserveLast*10+x%10;
            x/=10;
        }
        return x==reserveLast||x==reserveLast%10;
    }

    public static void main(String[] args) {
        Solution s=new Solution();
        System.out.println(s.isPalindrome(123));
    }
}
