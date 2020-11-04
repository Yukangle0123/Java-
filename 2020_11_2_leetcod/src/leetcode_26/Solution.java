package leetcode_26;

import java.util.Arrays;

public class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
       int i=0;
       for(int j=1;j<nums.length;j++){
           if(nums[j]!=nums[i]){
               i++;
               nums[i]=nums[j];
           }
       }
       return i+1;
    }
    public int removeElement(int[] nums, int val) {
        int i=0;
        for(int j=0;j<nums.length;j++){
            if(nums[j]!=val){
                nums[i]=nums[j];
                i++;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        int []nums={0,1,2,4,5};
        Solution s=new Solution();
        System.out.println(s.removeElement(nums,5));
        System.out.println(Arrays.toString(nums));
        String n="adada";
        System.out.println(n.indexOf("de"));

    }
}
