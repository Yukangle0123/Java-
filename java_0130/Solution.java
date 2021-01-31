package java_0130;

public class Solution {
    public boolean canPartition(int[] nums) {
        int len = nums.length;
        int sum = getSum(nums);
        if(sum % 2 != 0){
            return false;
        }
        int target = sum / 2;
        boolean[][] dp = new boolean[nums.length][target+1];
        dp[0][0] = true;
        if(nums[0] == target){
            dp[0][nums[0]] = true;
        }
        dp[0][0] = true;
        for(int i = 1; i < len; i++){
            for(int j = 0; j <= target; j++){
                dp[i][j] = dp[i-1][j];
                if(j >= nums[i]){
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]];
                }
            }
        }
        System.out.println("...");
        return dp[nums.length-1][target];
    }

    private int getSum(int[] nums) {
        int ret = 0;
        for(int n : nums){
            ret += n;
        }
        return ret;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.canPartition(new int[]{1, 2, 5}));
    }
}
