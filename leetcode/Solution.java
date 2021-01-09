package com.code.leetcode;

import org.junit.Test;

import java.util.HashMap;
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
    @Test
    public void test(){
        System.out.println(Integer.MAX_VALUE);//586268941
                                              //1000000008
    }
}
