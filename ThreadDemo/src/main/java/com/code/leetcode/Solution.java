package com.code.leetcode;

import jdk.nashorn.internal.ir.CallNode;

import java.util.*;

class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};

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
    public int maxSubArray1(int[] nums) {
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
    //三角形最小路径和
    public int minimumTotal(List<List<Integer>> triangle) {
        int row =triangle.size();
        int[][] dp =new int[row][row];
        dp[0][0]=triangle.get(0).get(0);
        for(int i =1; i < row; i++){
            for(int j=0; j <= i ; j++){
                if(j == 0){
                    dp[i][j] = dp[i-1][j]+triangle.get(i).get(j);
                }else if(j == i){
                    dp[i][j] = dp[i-1][j-1] + triangle.get(i).get(j);
                }else{
                    dp[i][j] = triangle.get(i).get(j) + Math.min(dp[i-1][j],dp[i-1][j-1]);
                }
            }
        }
        int res = 0;
        for(int i = 0; i < dp.length; i++){
            res = Math.max(res,dp[row-1][i]);
        }
        return res;
    }
    public int minimumTotal2(List<List<Integer>> triangle) {
        int row =triangle.size();
        int[][] dp =new int[row][row];
        for(int i = 0 ;i < row; i++){
            dp[row-1][0] = triangle.get(row-1).get(i);
        }
        for(int i = row-2; i >= 0; i--){
            for(int j = 0; j <= i ; j++){
                dp[i][j] = triangle.get(i).get(j) + Math.min(dp[i+1][j],dp[i+1][j+1]);
            }
        }
       return dp[0][0];
    }
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row =obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int[][] dp = new int[row][col];
        for(int i = 0 ; i < row; i++){
            if(obstacleGrid[i][0] == 0){
                dp[i][0] = 1;
            }else {
                break;
            }
        }
        for(int i = 0;i < col; i++){
           if(obstacleGrid[0][i] == 0){
               dp[0][i] = 1;
           }else{
               break;
           }
        }
        for(int i = 1; i < row; i++){
            for(int j = 1; j < col; j++){
                if(obstacleGrid[i][j] == 1){
                    dp[i][j] = 0;
                }else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[row-1][col-1];
    }
    public int uniquePaths(int m, int n) {

        int[][] dp = new int[m][n];
        for(int i = 0 ; i < m;i++){
            dp[i][0] = 1;
        }
        for(int i = 0;i < n; i++){
            dp[0][i] = 1;
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    /**
     * 实现函数 ToLowerCase()，该函数接收一个字符串参数 str，
     * 并将该字符串中的大写字母转换成小写字母，之后返回新的字符串。
     * @param str
     * @return
     */
    public String toLowerCase(String str) {
        char[] chars = str.toCharArray();
        for(int i = 0 ; i<chars.length; i++){
            if(chars[i]>=65&&chars[i]<=90){
                chars[i]+= 32;
            }
        }
        return new String(chars);
    }

    /**
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        k %=len;

        reverse(nums,0,len-1);
        reverse(nums,0,k-1);
        reverse(nums,k,len-1);

    }

    private void reverse(int[] nums, int start, int end) {
        while(start < end){
            swap(nums,start,end);
            start++;
            end--;
        }
    }
    public void swap(int[] nums,int index1,int index2){
        int tep = nums[index1];
        nums[index1] =nums[index2];
        nums[index2] =tep;
    }
    /*递归
    Node pre = null;
    Node head = null;

    public Node treeToDoublyList(Node root) {
        if(root==null){
            return null;
        }
        dfs(root);
        head.left = pre;
        pre.right =head;

        return head;
    }

    private void dfs(Node cur) {
        if(cur == null){
            return;
        }
        dfs(cur.left);
        if(pre != null){
            pre.right = cur;
        }else{
            head = cur;
        }
        cur.left=pre;
        pre = cur;
        dfs(cur.right);
    }
*/
    /*
    非递归
     */
    public Node treeToDoublyList(Node root){
        if(root == null){
            return null;
        }
        Stack<Node> stack = new Stack<>();
        Node cur = root;
        Node head =null;
        Node pre = null;
        while(cur != null || !stack.isEmpty()){
            while(cur!=null){
                stack.push(cur);
                cur = cur.left;
            }
            //运行到这里时已经到了左子树的叶子结点
            cur = stack.pop();
            if(pre != null){
                pre.right = cur;
            }else{
                head = cur;
            }
            cur.left = pre;
            pre = cur;
            cur = cur.right;
        }
        head.left = pre;
        pre.right = head;
        return head;
    }

    /**
     * 统计一个数字在排序数组中出现的次数
     * @param nums
     * @param target
     * @return
     */
    public int search1(int[] nums, int target) {
       Map<Integer,Integer> map = new HashMap<>();
       for(int n: nums){
           int count=map.getOrDefault(n,0)+1;
           map.put(n,count);
       }
        Integer integer = map.get(target);
       if(integer!=null){
           return integer;
       }
       return 0;
    }
    public int search(int[] nums, int target) {
        int len =nums.length;
        if(len==0){
            return 0;
        }
        int left =0;
        int right = len-1;
        int mid = 0;
        while(left<=right){
            mid =(left+right)/2;
            if(nums[mid]<target){
                left = mid+1;
            }else if(nums[mid] > target){
                right = mid-1;
            }else{
                break;
            }
        }
        if(left>right){
            return 0;
        }
        left = mid-1;
        right = mid+1;
        int res = 1;
        while(left>=0&&nums[left] == target){
            res++;
            left--;
        }
        while(right<=len-1&&nums[right] == target){
            res++;
            right++;
        }
        return res;
    }
    public int[] singleNumbers(int[] nums) {
        //先得到两个只出现一次的数字的异或值
        int ret = 0;
        for(int n : nums){
            ret ^=n;
        }
        int div =1;
        while((div&ret)==0){
            div =div<<1;
        }
        int a = 0;
        int b =0;
        for(int n : nums){
            if((n&div)!=0){
                a ^=n;
            }else{
                b ^=n;
            }
        }
        return new int[]{a,b};
    }
    public int singleNumber(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int n : nums){
            int count = map.getOrDefault(n,0)+1;
            if(count>1){
                map.remove(n);
            }else {
                map.put(n, count);
            }
        }
        return (int)map.keySet().toArray()[0];
    }

    /**
     * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。
     * 你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。
     * 给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
     * @param grid [[1,2,5],
     *             [3,2,1]]
     * @return
     */
    public int maxValue(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int[][] maxValue = new int[row][col];
        maxValue[0][0] = grid[0][0];
        for(int i = 1;i < col; i++){
            maxValue[0][i] = maxValue[0][i-1] + grid[0][i];
        }
        for(int i = 1;i < row; i++){
            maxValue[i][0] = maxValue[i-1][0] + grid[i][0];
        }
        for(int i = 1; i < row; i++){
            for(int j = 1; j < col;j++){
                maxValue[i][j] = Math.max(maxValue[i-1][j]+grid[i][j],maxValue[i][j-1]+grid[i][j]);
            }
        }
        return maxValue[row-1][col-1];
    }

    /**
     * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
     * @param s
     * @return
     */
    public char firstUniqChar(String s) {
        int len = s.length();
        if(len == 0){
            return ' ';
        }
        if(len == 1){
            return s.charAt(0);
        }
        HashMap<Character,Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for(char c : chars){
            int count = map.getOrDefault(c,0)+1;
            map.put(c,count);
        }
        for (char c:chars) {
            int count = map.get(c);
            if(count == 1){
                return c;
            }
        }
        return ' ';
    }

    /**
     * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。
     * 比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
     * @param n 位数
     * @return
     */
    public int[] printNumbers(int n) {
        int end  = (int)Math.pow(10,n)-1;
        int[] res = new int[end];
        for(int i = 1;i < end ;i++){
            res[i] = i;
        }
        return res;
    }

    /**
     * 旋转数组中的最小数字
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        int left = 0;
        int right = numbers.length-1;
        while(left<right){
            int mid = left+(right-left)/2;
            if(numbers[right]>numbers[mid]){
                right =mid;
            }else if(numbers[right]<numbers[mid]){
                left = mid +1;
            }else {
                right-=1;
            }
        }
        return numbers[left];
    }
    public String reverseLeftWords(String s, int n) {
        int len = s.length();
        if(len == 0){
            return null;
        }
        char[] chars =s.toCharArray();
        n %=len;
        reverse(chars,0,len-1);
        reverse(chars,0,len-n-1);
        reverse(chars,len-n,len-1);
        return new String(chars);
    }
    private void reverse(char[] chars, int start, int end) {
        while(start<end){
            char tmp = chars[start];
            chars[start] = chars[end];
            chars[end] = tmp;
            start++;
            end--;
        }
    }

    /**
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。
     * 如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        while(left<right){
            int mid = (right+left)/2;
            if(nums[mid] == target){
                return mid;
            }else if(target<nums[mid]){
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
       if(nums[left]<target){
           return left+1;
       }
       return left;
    }
    public ListNode partition(ListNode pHead, int x) {
        if(pHead == null||pHead.next == null){
            return pHead;
        }
        ListNode fNode = new ListNode(-1);
        ListNode sNode = new ListNode(-1);
        ListNode cur = pHead;
        ListNode fCur = fNode;
        ListNode sCur = sNode;
        while(cur!=null){
            if(cur.val>=x){
                fCur.next = new ListNode(cur.val);
                fCur = fCur.next;
            }else{
                sCur.next = new ListNode(cur.val);
                sCur = sCur.next;
            }
            cur = cur.next;
        }
        cur = sNode.next;
        while(cur!=null&&cur.next!=null){
            cur = cur.next;
        }
        cur.next = fNode.next;
        return sNode.next;
    }
    public boolean canConstruct(String ransomNote, String magazine) {
        if(magazine.length()<ransomNote.length()){
            return false;
        }
        char[] ransomNotes = ransomNote.toCharArray();
        char[] magazines = magazine.toCharArray();

        int[] ret = new int[26];
        for(char c : magazines){
            ret[c-'a'] +=1;
        }
        for(char c : ransomNotes){
            ret[c-'a'] -= 1;
            if(ret[c-'a']<0){
                return false;
            }
        }
        return true;
    }
    public boolean isPalindrome(int x){
        if(x < 0 || (x % 10 == 0 && x!=0)){
            return false;
        }
        int res = 0;
        //12321
        while(x > res){
            res = res*10 + x%10;
            x /= 10;
        }
        return x == res || x == res/10;
    }
    public boolean isPalindrome1(int x){
        if(x<0||(x%10==0&&x!=0)){
            return false;
        }
        String str = x+"";
        int left = 0;
        int right = str.length();
        char[] chars = str.toCharArray();
        while(left<right){
            if(chars[left]!=chars[right]){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。
     * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     * @param preorder 前序遍历的结果集
     * @param inorder   中序遍历的结果集
     * @return 头结点
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int preLen = preorder.length;
        int inLen = inorder.length;
        if(preLen != inLen){
            throw new RuntimeException("data error");
        }
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < inLen; i++){
            map.put(inorder[i],i);
        }
        return buildTree(preorder,0,preLen-1,map,0,inLen-1);
    }

    /**
     * 构建二叉树
     * @param preorder 前序遍历序列
     * @param preLeft  前序遍历序列子区间的左边界，可以取到
     * @param preRight 前序遍历序列子区间的右边界，可以取到
     * @param map 中序遍历序列，数值和下标的对应关系
     * @param inLeft 中序遍历序列子区间的左边界，可以取到
     * @param inRight 中序遍历序列子区间的右边界，可以取到
     * @return 根结点
     */
    private TreeNode buildTree(int[] preorder, int preLeft, int preRight,
                               Map<Integer, Integer> map, int inLeft, int inRight) {
        if(preLeft > preRight || inLeft > inRight){
            return null;
        }
        int rootValue = preorder[preLeft];
        TreeNode root = new TreeNode(rootValue);
        int pIndex = map.get(rootValue);
        root.left =buildTree(preorder,preLeft+1,pIndex-inLeft+preLeft,map,inLeft,pIndex-1);
        root.right=buildTree(preorder,pIndex-inLeft+preLeft+1,preRight,map,pIndex+1,inRight);
        return root;
    }

    /**
     * 青蛙跳台阶问题(递归)
     * @param n  第几阶台阶
     * @return 一共有多少种跳法
     */
    public int numWays1(int n) {
        if(n <= 1 ){
            return 1;
        }
        int[] result = new int[n+1];
        result[0] = 1;
        result[1] = 1;
        for(int i = 2; i <= n; i++){
            result[i] = (result[i-1]+result[i-2])%1000000007;
        }
        return result[n];
    }
    /**
     * 青蛙跳台阶问题(非递归)
     * @param n  第几阶台阶
     * @return 一共有多少种跳法
     */
    public int numWays(int n) {
        if(n <= 1 ){
            return 1;
        }
        int pre = 1;
        int prePre = 1;
        int ret = pre+prePre;
        int cur = ret;
        for(int i = 2; i < n; i++){
            prePre = pre;
            pre = cur;
            ret = (pre+prePre)%1000000007;
            cur = ret;
        }
        return ret;
    }
    public boolean exist(char[][] board, String word) {
        int n = board.length;//网格的行数
        int m = board[0].length;//网格的列数
        char[] chars = word.toCharArray();
        boolean[][] visited = new boolean[n][m];//是否已经访问了某个格子
        for(int i = 0; i < n; i++){
            for(int j = 0; j< m; j++){
                boolean res = search(i,j,0,n,m,visited,chars,board);
                if(res){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean search(int i, int j,int len, int row, int col, boolean[][] visited, char[] chars,char[][] board) {
        if(len > chars.length){
            return true;
        }
        if(i >= row || j >= col || i<0 || j<0 || chars[len] != board[i][j]||visited[i][j]){
            return false;
        }
        visited[i][j] = true;
         boolean res = search(i+1,j,len+1,row,col,visited,chars,board) ||
                       search(i-1,j,len+1,row,col,visited,chars,board) ||
                       search(i,j+1,len+1,row,col,visited,chars,board) ||
                       search(i,j-1,len+1,row,col,visited,chars,board) ;
         visited[i][j] = false;
         return res;
    }

    /**
     * 二叉树的镜像
     * @param root
     * @return
     */
    public TreeNode mirrorTree(TreeNode root) {
//        if(root == null){
//            return null;
//        }
//        TreeNode tmp = root.left;
//        root.left = mirrorTree(root.right);
//        root.right = mirrorTree(tmp.left);
//        return root;
        if(root == null){
            throw new RuntimeException("error data");
        }
        Stack<TreeNode> stack =new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode pop = stack.pop();
            if(pop.left!=null){ stack.push(pop.left);}
            if(pop.right!=null){ stack.push(pop.right);}
            TreeNode tmp = pop.left;
            pop.left = pop.right;
            pop.right = tmp;
        }
        return root;
    }
    public boolean isSymmetric(TreeNode root) {
        if(root == null || (root.left == null&& root.right ==null)){
            return true;
        }
        return isSameTree(root.left,root.right);
    }

    private boolean isSameTree(TreeNode left, TreeNode right) {
        if((left ==null && right !=null)||(left !=null && right ==null)){
            return false;
        }
        if(left == null && right ==null){
            return true;
        }
        return left.val==right.val&& isSameTree(left.left,right.right)&&isSameTree(left.right,right.left);
    }

    /**
     * 顺时针打印矩阵
     * @param matrix 矩阵
     *   [1,2],
     *   [5,6]
     * @return
     */
    public int[] spiralOrder(int[][] matrix) {
        if(matrix == null || matrix.length ==0 || matrix[0] ==null || matrix[0].length ==0){
            return null;
        }
        int rowStart = 0;
        int rowEnd = matrix.length-1;
        int colStart = 0;
        int colEnd = matrix[0].length-1;
        int[] res = new int[(rowEnd+1) * (colEnd+1)];
        int index = 0;
        while(rowStart <= rowEnd && colStart <= colEnd ){
            for(int i = colStart; i <= colEnd ;i++){
                res[index++] = matrix[rowStart][i];
            }
            rowStart++;
            for(int i = rowStart; i <= rowEnd; i++){
                res[index++] = matrix[i][colEnd];
            }
            colEnd--;
            if (rowStart <= rowEnd) {
                for(int i = colEnd ;i >= colStart ;i--){
                    res[index++] = matrix[rowEnd][i];
                }
            }
            rowEnd --;
            if (colStart <= colEnd) {
                for(int i = rowEnd; i >= rowStart;i-- ){
                    res[index++] = matrix[i][colStart];
                }
            }
            colStart++;
        }
        return res;

    }


    /**
     * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> list = new LinkedList<>();
        if( root ==null ){
            return list;
        }
        Queue<LevelNode> queue = new LinkedList<>();
        queue.add(new LevelNode(root,0));
        while(!queue.isEmpty()){
            LevelNode node = queue.poll();
            int level = node.level;
            TreeNode curNode = node.node;
            if(level == list.size()){
                List<Integer> list1 = new LinkedList<>();
                list.add(list1);
            }
            List<Integer> list2 = list.get(level);
            list2.add(curNode.val);
            if(curNode.left!= null){
                queue.add(new LevelNode(curNode.left,level+1));
            }
            if(curNode.right!= null){
                queue.add(new LevelNode(curNode.right,level+1));
            }
        }
        return list;
    }
    public List<List<Integer>> levelOrder(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if(root != null){
            queue.add(root);
        }
        while(!queue.isEmpty()){
            LinkedList<Integer> list = new LinkedList<>();
            int size = queue.size();
                for(int i =0; i < size;i++){
                    TreeNode node = queue.poll();
                    if(res.size()%2 == 0){
                        list.addFirst(node.val);
                    }else{
                        list.addLast(node.val);
                    }
                    if(node.left!=null){
                        queue.add(node.left);
                    }
                    if(node.right!=null){
                        queue.add(node.right);
                    }

                }
            res.add(list);
        }
        return res;
    }
    public int[] twoSum(int[] nums, int target) {
        int start = 0;
        int end =nums.length-1;
        while(start < end){
            int ret = nums[start]+nums[end];
            if(ret>target){
                end--;
            }else if(ret<target){
                start++;
            }else{
                return new int[]{nums[start],nums[end]};
            }
        }
        return new int[0];
    }
    /**
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。
     * 假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列
     * {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
     * @param pushed  压入序列
     * @param popped  弹出序列
     * @return
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int index = 0;
        for(int n : pushed){
            if(!stack.isEmpty() && stack.peek() == popped[index]){
                stack.pop();
                index++;
            }
            stack.push(n);
        }
        return stack.isEmpty();
    }

    /**
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。
     * 如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
     * @param postOrder
     * @return
     */
    public boolean verifyPostOrder(int[] postOrder) {
        return helper(postOrder,0,postOrder.length-1);
    }

    private boolean helper(int[] postOrder, int i, int j) {
        if (i >= j){
            return true;
        }
        int l = i;//l指的是二叉搜索树的左子树的下标索引
        while(postOrder[l] < postOrder[j]){
            l++;
        }
        //(i,l)//左子树的下标区间
        int r = l;
        while(postOrder[r] > postOrder[i]){
            r++;
        }
        return r == j && helper(postOrder,i,l-1) && helper(postOrder,r,j-1);
    }
    public boolean verifyPostorder(int[] postOrder) {
        Stack<Integer> stack = new Stack<>();
        int root = Integer.MAX_VALUE;
        for(int i = postOrder.length-1; i >=0; i--){
            if(postOrder[i] > root){
                return false;
            }
            while(!stack.isEmpty() && stack.peek() > postOrder[i]){
                root = stack.pop();
            }
            stack.push(postOrder[i]);
        }
        return true;
    }
    public int majorityElement(int[] nums) {
        int x = 0;
        int count = 0;
        for(int n : nums){
            if(count == 0){
                x = n;
            }
            count += (x==n ? 1 : -1);
        }
        return x;
    }

    /**
     * 连续子数组的最大和
     * 数组前i个元素的最大和
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        int res = nums[0];
        for(int i = 1; i < len; i++){
            dp[i] = Math.max(nums[i],dp[i-1] + nums[i]);
            res =Math.max(dp[i],res);
        }
        return res;
    }
    public int[] getLeastNumbers1(int[] arr, int k) {
        int[] res = new int[k];
        if(k == 0){
            return res;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for(int i = 0; i < k; i++){
            queue.offer(arr[i]);
        }
        for(int i = k; i < arr.length; i++){
            if(arr[i] < queue.peek()){
                queue.poll();
                queue.offer(arr[i]);
            }
        }
        for(int i = 0; i < k; i++){
            res[i] = queue.poll();
        }
        return res;
    }
    public int[] getLeastNumbers(int[] arr, int k) {
        int[] res = new int[k];
        Arrays.sort(arr);
        for(int i = 0; i < k; i++){
            res[i] = arr[i];
        }
        return res;
    }

    /**
     * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
     * @param digits
     * @return
     */
    public static int[] plusOne(int[] digits) {
        int dig = 1;//1 2 3
        int len = digits.length-1;
        for(int i = len; i >=0; i--){
            int tmp = digits[i]+dig;
            digits[i] = (digits[i] + dig)%10;
            dig = tmp/10;
        }
        if(dig ==0){
            return digits;
        }
        int[] res = new int[digits.length+1];
        res[0] = 1;
        System.arraycopy(digits,0,res,1,digits.length);
        return res;
    }
    public int thirdMax(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return nums[0] > nums[1] ? nums[0] : nums[1];
        }

        long firstMax = Long.MIN_VALUE;
        long secondMax = Long.MIN_VALUE;
        long thirdMax = Long.MIN_VALUE;
        for (int n : nums) {
            if (n > firstMax) {
                thirdMax = secondMax;
                secondMax = firstMax;
                firstMax = n;
            } else if (firstMax == n){
                continue;
            }else  if (n > secondMax) {
                thirdMax = secondMax;
                secondMax = n;
            } else if (n == secondMax) {
                continue;
            } else if (n > thirdMax) {
                thirdMax = n;
            }
        }
        return thirdMax == Long.MIN_VALUE ? (int)firstMax : (int)thirdMax;
    }
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        return (A != null && B != null) && (isSubStructureHelper(A,B)||
                isSubStructure(A.left,B) || isSubStructure(A.right,B));
    }
    public boolean isSubStructureHelper(TreeNode A, TreeNode B){
        if(B == null){
            return true;
        }
        if(A == null || A.val != B.val){
            return false;
        }
        return isSubStructureHelper(A.left,B.left) && isSubStructureHelper(A.right,B.right);
    }
    public int cuttingRope(int n) {
        if (n < 4){
            return n-1;
        }
        int mod = (int)1e9+7;
        long res = 1;
        while(n > 4 ){
            res *= 3;
            res %= mod;
            n -=3;
        }
        return (int)(res*n%mod);
    }
    public static int add(int a, int b) {
        //两数和 S = (非进位和) + (进位和)
        // 8 + 5 = 10 + 3
        while(b != 0){
            int c = (a & b) << 1; //进位和
            a ^= b;//非进位和
            b = c;
        }
        return a;
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root ==p || root == q){
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        if(left == null && right == null){
            return null;
        }
        if(left == null){
            return right;
        }
        if(right == null){
            return left;
        }
        return root;
    }
    private static int getDeath(TreeNode root){
        if(root == null){
            return 0;
        }
        return 1 + Math.max(getDeath(root.left),getDeath(root.right));
    }
    public static boolean isBalanced(TreeNode root) {
        if(root == null){
            return true;
        }
        return Math.abs(getDeath(root.left) - getDeath(root.right)) <=1 && isBalanced(root.left)
                && isBalanced(root.right);
    }

    public static void main(String[] args) {
//        int[] nums = {9,9};
//        System.out.println(Arrays.toString(plusOne(nums)));
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        root.right = node1;
        node1.right = node2;
        System.out.println(isBalanced(root));
    }
    class LevelNode{
        TreeNode node;
        int level;
        LevelNode(TreeNode node , int level){
            this.level= level;
            this.node = node;
        }
    }
    class MinStack {
        Stack<Integer> stack1 = null;//放的是存入的数字
        Stack<Integer> stack2 = null;//放的是最小的数字

        public MinStack() {
            stack1 = new Stack<>();
            stack2= new Stack<>();
        }

        public void push(int x) {
            stack1.push(x);
            if(stack2.isEmpty()){
                stack2.push(x);
            }else{
                int val = stack2.peek();
                if(x < val){
                    stack2.push(val);
                }else{
                    stack2.push(x);
                }
            }
        }

        public void pop() {
            stack1.pop();
            stack2.pop();
        }

        public int top() {
            return stack1.peek();
        }

        public int min() {
            return stack2.peek();
        }
    }
}
