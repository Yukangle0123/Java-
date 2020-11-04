package LeetCode_84;

import java.util.Stack;

public class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack <Integer>stack=new Stack<>();
        int maxArea=0;
        for(int i=0;i<heights.length;i++){
            while(!stack.empty()&&heights[i]<stack.peek()){


            }
            stack.push(heights[i]);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int []heights={2,1,5,6,2,3};
        Solution s=new Solution();
        System.out.println(s.largestRectangleArea(heights));
    }
}
