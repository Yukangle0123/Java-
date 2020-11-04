package LeetCode_496;


import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer>stack=new Stack<>();
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i< nums2.length;i++){
            while(!stack.empty()&&nums2[i]>stack.peek()){
                map.put(stack.pop(),nums2[i]);
            }
            stack.push(nums2[i]);
        }
        while(!stack.empty()){
            map.put(stack.pop(),-1);
        }

        int index=0;
        int[]ans=new int[nums1.length];
        for(int n:nums1){
            ans[index++]=map.get(n);
        }
        return ans;
    }
}
