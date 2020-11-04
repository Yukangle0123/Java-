package leetcode_525;


import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int findMaxLength(int[] nums) {
        int count=0;
        int maxLen=0;
        //count index;
        Map<Integer,Integer> map=new HashMap();
        for(int i=0;i<nums.length;i++){
            count=count+(nums[i]==1?1:-1);
            if(map.containsKey(count)){
                maxLen=Math.max(maxLen,i-map.get(count));
            }else{
                map.put(count,i);
            }
        }
        return maxLen;
    }
}
