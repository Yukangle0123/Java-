package com.code.lintcode;

import java.util.ArrayList;
import java.util.List;

class ListNode{
    int val;
    ListNode next;
    ListNode(int val){
        this.val = val;
    }
}

public class Solution {
    public int backPackII(int m, int[] A, int[] V) {
        if (m == 0 || A.length == 0) {
            return 0;
        }
        // write your code here
//        int n = A.length;
//        int[][] dp = new int[n + 1][m + 1];
//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= m; j++) {
//                if (A[i - 1] <= j) {
//                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - A[i - 1]] + V[i - 1]);
//                } else {
//                    dp[i][j] = dp[i - 1][j];
//                }
//            }
//        }
//        System.out.println("...");
//        return dp[n][m];
        int[] dp = new int[m + 1];
        int n = A.length;
        for (int i = 0; i < n; i++) {
            for (int j = m; j >= A[i]; j--) {
                dp[j] = Math.max(dp[j], V[i] + dp[j - A[i]]);
            }
        }
        return dp[m];
    }
    public int minCut(String s) {
        int len =s.length();
        if(len < 2){
            return 0;
        }
        boolean[][] pMat = palMat(s);
        int[] dp =new int[len];
        for(int i = 0; i <len; i++){
            dp[i] = i;
        }
        for(int i = 1;i <len; i++){
            if(pMat[0][i]){
                dp[i] = 0;
                continue;
            }
            for(int j = 0; j < i; j++){
               if(pMat[j+1][i]){
                   dp[i] = Math.min(dp[i],dp[j]+1);
               }
           }
        }
        return dp[len-1];
    }
    public boolean isPalindrome(String s,int head,int tail){
        char[] chars =s.toCharArray();
        while(head <= tail){
            if(chars[head]!= chars[tail]){
                return false;
            }
            tail--;
            head++;
        }
        return true;
    }
    public boolean[][] palMat(String s){
        int len = s.length();
        boolean[][] pMat = new boolean[len][len];
        for(int i = len-1; i >= 0 ;i--){
            for(int j = i; j < len; j++){
                if(i == j ){
                    pMat[i][j] = true;
                }else if(i+1 == j) {
                    if(s.charAt(j) == s.charAt(i)){
                        pMat[i][j] = true;
                    }
                }else{
                    if(pMat[i+1][j-1]&&s.charAt(i) == s.charAt(j)){
                        pMat[i][j] = true;
                    }
                }
            }
        }
        return pMat;
    }
    public int countSubstrings(String s) {
       int len = s.length();
       boolean[][] pMat = new boolean[len][len];
       int res = 0;
       for(int i = len-1 ;i >= 0; i--){
           for(int j = i; j < len; j++){
               if( i == j){
                   pMat[i][j] = true;
                   res++;
               }else if(i+1 == j && s.charAt(i) == s.charAt(j)){
                   pMat[i][j] = true;
                   res++;
               }else{
                   if(pMat[i+1][j-1] && s.charAt(i) == s.charAt(j)){
                       pMat[i][j] = true;
                       res++;
                   }
               }
           }
       }
       return res;
    }
    public boolean isPalindrome(ListNode head) {
        if(head == null){
            return true;
        }
        List <String>ret = new ArrayList<>();
        while(head != null){
            ret.add(head.val+"");
            head = head.next;
        }
        int left =0;
        int right =ret.size()-1;

        while(left < right){
            if(!ret.get(left).equals(ret.get(right))){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    public boolean isPalindrome2(ListNode head){
        if(head  == null){
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = null;
        ListNode curNext = null;
        while(fast != null && fast.next !=null){
            pre =slow;
            slow = slow.next;
            fast = fast.next.next;
        }
       while(slow != null ) {
           curNext =slow.next;
           slow.next =pre;
           pre = slow ;
           slow = curNext;
       }
       ListNode start = head;
       ListNode end =pre;
       while(true){
           if(start.val!=end.val){
               return false;
           }
           if(start == end){
               return true;
           }
           if(end.next == start){
               return true;
           }
           start = start.next;
           end = end.next;
       }
    }
    public void reverseLinkedList(ListNode head){
        if(head == null){
            return;
        }
        ListNode pre = null;//要反转结点的前驱结点
        ListNode cur = head;//要反转的结点
        ListNode curNext = null;//要反转结点的后继结点
        while(cur != null){
            curNext =cur.next;
            cur.next = pre;
            pre = cur ;
            cur = curNext;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
//        s.backPackII(10,new int[]{2, 3, 5, 7},new int[]{1, 5, 2, 4});
        ListNode listNode = new ListNode(1);
        ListNode listNode1 = new ListNode(0);
        ListNode listNode2 =new ListNode(0);
        listNode.next =listNode1;
        listNode1.next =listNode2;
        System.out.println(s.isPalindrome(listNode));
    }
}
