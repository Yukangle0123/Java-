package java_0203;

import java.util.*;

public class Main {

    public static void main1(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        Map<Character,Integer> map1 = new HashMap<>();//统计甲
//        Map<Character,Integer> map2 = new HashMap<>();//统计乙
//
//        while(sc.hasNext()){
//            int n = sc.nextInt();
//            for(int i = 0; i < n; i++){
//                String s = sc.nextLine();
//                String[] strings = s.split(" ");
//                char[] c = count((strings[0].toCharArray())[0],(strings[1].toCharArray())[1]);
//            }
//        }
        System.out.println(countWays(3));
    }

//    private static char[] count(char c1, char c2) {
//        if()
//    }
    public static int countWays(int n) {
        if(n == 1){
            return 1;
        }
        if(n == 2){
            return 2;
        }
        if(n == 3){
            return 4;
        }
        long[] dp = new long[n];
        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 4;
        for(int i = 3; i < n; i++){
            dp[i] = (dp[i-1] + dp[i-2] + dp[i-3])% 1000000007;
        }
        return (int)dp[n-1];
    }

    /**
     * [1,2,8,9]
     * [2,4,9,12]
     * [4,7,10,13]
     * [6,8,11,15]
     */
    public static boolean Find(int target, int [][] array) {
        int row = array.length;
        if(row == 0) {
            return false;
        }
        int col = array[0].length;
        int i = 0;
        int j = col - 1;
        while(j >= 0 && i < row){
            while( i < row && j >= 0 && array[i][j] < target) {
                i++;
            }
            while(i < row && j >= 0&& array[i][j] > target) {
                j--;
            }
            if(i < row && j >= 0 && array[i][j] == target){
                return true;
            }

        }
        return false;
    }
    public String replaceSpace(StringBuffer str) {
        String s = str.toString();
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < str.length(); i++){
            if(s.charAt(i) == ' '){
               res.append("%20");
               continue;
            }
            res.append(s.charAt(i));
        }
        return res.toString();
    }
    public ArrayList<Integer> printListFromTailToHead1(ListNode listNode) {
        if(listNode == null){
            return null;
        }
        Deque<Integer> deque = new ArrayDeque<>();
        ListNode cur = listNode;
        while(cur != null){
            deque.addFirst(cur.val);
            cur = cur.next;
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.addAll(deque);
        return arrayList;
    }
    ArrayList<Integer> array = new ArrayList<>();
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if(listNode != null){
            printListFromTailToHead1(listNode.next);
            array.add(listNode.val);
        }
        return array;
    }
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if(in.length == 0 || pre.length != in.length){
            return null;
        }
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < in.length; i++){
            map.put(in[i],i);
        }
        return reConstructBinaryTreeHelper(pre,0,pre.length-1,map,0,in.length-1);
    }

    private TreeNode reConstructBinaryTreeHelper(int[] pre,
                                                 int preLeftIndex,
                                                 int preRightIndex,
                                                 Map<Integer, Integer> map,
                                                 int inLeftIndex,
                                                 int inRightIndex) {
        if(preLeftIndex > preRightIndex || inLeftIndex > inRightIndex){
            return null;
        }
        int rootValue = pre[preLeftIndex];
        int index = map.get(rootValue);//拿到头结点在中序遍历中的坐标位置
        TreeNode root = new TreeNode(rootValue);
        root.left = reConstructBinaryTreeHelper(pre,preLeftIndex+1,
                preRightIndex+index-inRightIndex,
                map,inLeftIndex,index-1);
        root.right = reConstructBinaryTreeHelper(pre,preRightIndex+index-inRightIndex+1,
                preRightIndex,map,index+1,inRightIndex);
        return root;
    }
    public int minNumberInRotateArray(int [] array) {
        int left = 0;
        int right = array.length-1;
        while(left < right){
            int mid = left + (right - left)/2;
            if(array[mid] > array[right]){
                left = mid+1;
            }else{
                right = mid;
            }
        }
        return array[left];
    }
    public static int Fibonacci(int n) {
        if(n <= 1) {
            return n;
        }
        int[] dp = new int[2];
        dp[1] = 1;
        int res = 0;
        for(int i = 2; i <= n; i++){
            res = dp[0] + dp[1];
            dp[1] = res;
            dp[0] = dp[1];
        }
        return res;
    }
    public int rectCover(int target) {
        if(target == 0 || target == 1 || target == 2){
            return target;
        }
        return rectCover(target-1) + rectCover(target - 2);
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};
        Fibonacci(5);
    }
}
