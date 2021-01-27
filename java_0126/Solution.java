package java_0126;


import java.util.*;

class TreeNode{
    TreeNode left;
    TreeNode right;
    int val;
}

public class Solution {
    public int myAtoi(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        int index = 0;
        while(index < len && chars[index] == ' ' ){
            index ++;
        }
        if(index == len ){
            return 0;
        }
        int flg = 1;
        if(chars[index] == '-' ){
            flg = -1;
            index++;
        }else if(chars[index] == '+'){
            index++;
        }
        int res = 0;
        int pop = 0;
        for(int i = index+1; i < len; i++){
            if(chars[i] > '9' || chars[i] < '0'){
                break;
            }
            pop = chars[i] - '0';
            if(res > Integer.MAX_VALUE/10 ||
                    (res == Integer.MAX_VALUE/10 && pop > Integer.MAX_VALUE % 10)){
                return Integer.MAX_VALUE;
            }
            if(res < Integer.MIN_VALUE/10 ||
                    (res == Integer.MIN_VALUE/10 && pop > -(Integer.MIN_VALUE % 10))){
                return Integer.MIN_VALUE;
            }
            res = (res * 10) + (flg * pop );
        }
        return res;
    }
    public int[] searchRange1(int[] nums, int target) {
        int len = nums.length;
        int left = 0;
        int right = 0;
        int index = 0;
        if(len == 1 || len == 0){
            return new int[]{-1,-1};
        }
        while (left < len && nums[left] != target) {
            left++;
        }
        right = left;
        while(right < len && nums[right] == target){
            right++;
        }
        return new int[]{left,right-1};
    }
    public int[] searchRange(int[] nums, int target){
        int len = nums.length;
        if(len == 0){
            return new int[] {-1,-1};
        }
        int leftIndex = binaryFirstSearch(nums,0,len-1, target);
        if(leftIndex == -1 ){
            return new int[]{-1,-1};
        }
        int rightIndex = binaryLastSearch(nums,leftIndex,len-1, target);
        return new int[]{leftIndex,rightIndex};
    }

    private int binaryFirstSearch(int[] nums, int leftIndex, int rightIndex,int target) {
        while(leftIndex < rightIndex){
            int mid = (leftIndex + rightIndex) / 2;
            if(nums[mid] < target ){
                leftIndex = mid + 1;
            }else if(nums[mid] > target){
                rightIndex = mid - 1;
            }else{
                rightIndex = mid;
            }
        }
        if(nums[leftIndex] == target){
            return leftIndex;
        }
        return -1;
    }
    private int binaryLastSearch(int[] nums, int leftIndex, int rightIndex,int target){
        while(leftIndex < rightIndex){
            int mid = (leftIndex + rightIndex+1) / 2;//5,7,7,8,8,10
            if(nums[mid] < target ){
                leftIndex = mid + 1;
            }else if(nums[mid] > target){
                rightIndex = mid - 1;
            }else{
                leftIndex = mid;
            }
        }
        return rightIndex;
    }
    List<Integer> list = new ArrayList<>();
    public int kthLargest(TreeNode root, int k) {
        if(root == null){
            return 0;
        }
        traversal(root);

        int len = list.size();
        if(k > len || k <= 0){
            return 0;
        }
        return list.get(k-1);

    }

    private void traversal(TreeNode root) {
        if(root == null){
            return;
        }
        traversal(root.left);
        list.add(root.val);
        traversal(root.right);
    }
    List<Integer> list1 = new ArrayList<>();
    int index = 0;
    public int lastRemaining(int n, int m) {
        int res = 0;
        for(int i = 1; i <= n; i++){
            res = (res + m ) % i;
        }
        return res;
    }
    public boolean isStraight1(int[] nums) {
        int count = 0;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length-1; i++){
            if(nums[i] == 0){
                count++;
            }else if(nums[i] == nums[i+2]){
                return false;
            }
        }
        return nums[4]- nums[count] > 5;
    }
    public boolean isStraight(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int max = 0;
        int min = 15;
        for(int n : nums){
            if(n == 0){
                continue;
            }
            max = Math.max(n,max);
            min = Math.min(n,min);
            if(set.contains(n)){
                return false;
            }
            set.add(n);

        }
        return max - min < 5;
    }
    public int missingNumber(int[] nums) {
        int i = 0 , j = nums.length-1;
        while(i <= j){
            int mid = (i+j)/2;
            if(nums[mid] == mid){
                i = mid + 1;
            }else{
                j = mid-1;
            }
        }
        return i;
    }
    public String reverseWords(String s){
        String[] strings = s.split(" ");
        StringBuilder res = new StringBuilder();
        for(int i = s.length()-1; i >=0; i++){
            if(strings[i].equals("")){
                continue;
            }
            res.append(strings[i] + " ");
        }
        return res.toString().trim();
    }
    public String reverseWords2(String s){
        s = s.trim();
        int i = s.length()-1;
        int j = i;
        StringBuilder ret = new StringBuilder();
        while(i >=0){
            while(i >= 0 && s.charAt(i) != ' '){
                i--;
            }
            ret.append(s.substring(i+1,j+1)+" ");
            while(i >= 0 && s.charAt(i) == ' '){
                i--;
            }
            j = i;
        }
        return ret.toString().trim();
    }
    public String reverseWords1(String s) {
        Stack<String> stack = new Stack<>();
        int index = 0;
        StringBuilder res = new StringBuilder();
        while(index < s.length()){
            while(index < s.length() && s.charAt(index) == ' ') {
                index++;
            }
            while(index < s.length() && s.charAt(index) != ' '){
                res.append(s.charAt(index++));
            }
            stack.push(res.toString());
            res = new StringBuilder();

        }
        while(!stack.isEmpty()){
            String str= stack.pop();
            if (str.equals("")) {
                continue;
            }
            res.append(str);
            if(stack.size() != 1){
                res.append(" ");
            }
        }
        return res.toString();

    }


    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{0,0,2,2,5};
        s.reverseWords("  hello world!  ");
    }
}
