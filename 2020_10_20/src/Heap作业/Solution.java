package Heap作业;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public int lastStoneWeight(int[] stones) {
        int len=stones.length-1;
        if(stones.length==1){
            return stones[0];
        }
        Arrays.sort(stones);
        while(stones[len-1]!=0){
            stones[len-1]=stones[len]-stones[len-1];
            stones[len]=0;
            Arrays.sort(stones);
        }
        return stones[len];
    }
    public static void main(String[] args) {
        int []array={2,7,4,1,8,1};
        Solution s=new Solution();
        System.out.println(s.lastStoneWeight(array));
    }
}
