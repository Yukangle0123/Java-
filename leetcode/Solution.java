package com.code.leetcode;

import java.util.LinkedList;
import java.util.List;
//在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
// 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
// 请找出数组中任意一个重复的数字。

public class Solution {
    public int findRepeatNumber(int[] nums) {
      int len = nums.length;
      int [] res = new int[len];
      for(int n :  nums){
          if(res[n] == 0){
              res[n]+=1;
          }else{
              return n;
          }
      }
      return 0;
    }
    //在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，
    // 每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，
    // 输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
//[
    //  [1,   4,  7, 11, 15],
    //  [2,   5,  8, 12, 19],
    //  [3,   6,  9, 16, 22],
    //  [10, 13, 14, 17, 24],
    //  [18, 21, 23, 26, 30]
    //]
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if(matrix.length==0) {
            return false;
        }
        int row = 0;//行
        int col = matrix[0].length-1;//列
        int maxRow= matrix.length;
        while(true){
            if(col<0||row>=maxRow){
                break;
            }
            if(col>=0&&row<maxRow&&target>matrix[row][col]){
                row++;
            }
            if(col>=0&&row<maxRow&&target<matrix[row][col]){
                col--;
            }
            if(col>=0&&row<maxRow&&target==matrix[row][col]){
                return true;
            }
        }
        return false;
    }
    public String replaceSpace(String s) {
        char[] chars = s.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for(char c: chars){
            if(c==' '){
                stringBuilder.append("%20");
            }else{
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }

    public int fib(int n) {
        int [] res=new int [n+1];
        res[0] = 0;
        res[1] = 1;
        for(int i= 2 ;i<= n;i++){
            res[i]=res[i-1]+res[i-2];
            if(res[i]>1000000007){
                res[i]%=1000000007;
            }
        }
        return res[n];
    }
    public int maxProfit(int[] prices) {
        //解法一：
//        int res = 0;
//        int buy = Integer.MAX_VALUE;
//        for(int price : prices){
//            buy = Math.min(buy,price);
//            res = Math.max(res,price-buy);
//        }
//        return res;
        //解法二：dp
        if(prices.length==0){
            return 0;
        }
        int []dp =new int[prices.length];
        int minBuy = prices[0];
        for(int i = 1;i<prices.length;i++){
            minBuy = Math.min(minBuy,prices[i]);
            dp[i] = Math.max(dp[i-1],prices[i]-minBuy);
        }
        return dp[prices.length-1];
    }
    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
//        int [] dp =new int[len];
//        dp[0] = cost[0];
//        dp[1] = cost[1];
        int a = cost[0];
        int b = cost[1];
        int t = 0;
        for(int i = 2; i<cost.length;i++){
            t = cost[i]+Math.min(a,b);
            a=b;
            b=t;
        }
        return Math.min(b,t);
    }
    public int climbStairs(int n) {
        int [] dp =new int [n];
        dp[0] = 1;
        dp[1] = 2;
        for(int i =2 ; i<n ;i++ ){
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n-1];
    }
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for(int i = 1;i<nums.length;i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
        }
        int res = dp[0];
        for(int i = 0;i < dp.length; i++){
            res = Math.max(res,dp[i]);
        }
        return res;
    }
    public int maxSubArray2(int[] nums){
        int pre = nums[0];
        int res = nums[0];
        for(int i =1; i < nums.length;i++){
            int tmp =Math.max(nums[i],nums[i]+pre);
            res =Math.max(res,tmp);
            pre = tmp;
        }
        return res;
    }
    public int rob(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return nums[0];
        }
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);
        for(int i = 2; i<len ;i++){
            dp[i] = Math.max(dp[i-1],nums[i]+dp[i-2]);
        }
        return dp[len-1];
    }
    public int minPathSum(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < col; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int i = 0; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[row - 1][col - 1];
    }
    public boolean wordBreak(String s, List<String> wordDict) {
        if(s.length()==0) {
            return false;
        }
        boolean[] canBreak =new boolean[s.length()+1];
        for(int i = 0; i <= s.length(); i++){
            //判断整体
            if(wordDict.contains(s.substring(0,i))){
                canBreak[i] = true;
                continue;
            }
            for(int j = i-1;j > 0 ;j--){
                if(canBreak[j] && wordDict.contains(s.substring(j,i))){
                    canBreak[i] = true;
                    break;
                }
            }
        }
        return canBreak[s.length()];
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        List <String> list = new LinkedList<>();
        list.add("leet");
        list.add("code");
        System.out.println(s.wordBreak("leetlcode", list));
    }
}
