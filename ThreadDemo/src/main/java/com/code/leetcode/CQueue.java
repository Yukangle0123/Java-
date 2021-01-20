package com.code.leetcode;

import org.junit.Test;

import java.math.BigInteger;
import java.util.*;

class CQueue {
    public Stack<Integer> stack1 = null;//
    public Stack<Integer> stack2 = null;
    public CQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void appendTail(int value) {
        stack1.push(value);
    }

    public int deleteHead() {
        if(stack2.isEmpty()&&stack1.isEmpty()){
            return -1;
        }
        if (stack2.isEmpty()&&!stack1.isEmpty()){
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
    //机器人的运动范围
    public  int movingCount(int m, int n, int k) {
        boolean [][] isCome = new boolean[m][n];
        int ret=Sum(0,0,k,isCome);
        return ret;
    }
    private int Sum(int m, int n, int k, boolean[][] isCome) {
        if(m<0||n<0||m>=isCome.length||n>=isCome[0].length|| isCome[m][n]){
            return 0;
        }
        if(count(m)+count(n)>k){
            return 0;
        }
        isCome[m][n]=true;
        return 1+Sum(m-1,n,k,isCome)+Sum(m+1,n,k,isCome)+Sum(m,n+1,k,isCome)+Sum(m,n-1,k,isCome);
    }
    public int count(int value){
        int sum=0;
        while(value>0){
            sum +=value%10;
            value /=10;
        }
        return sum;
    }
    //剪绳子
    public int cuttingRope1(int n) {
        int[]dp = new int[n+1];
        for(int i = 2 ;i<=n;i++){
            for(int j = 1 ; j<i; j++){
                dp[i] = Math.max(dp[i],Math.max(j*(i-j),j*dp[i-j]));
            }
        }
        return dp[n];
    }
//    @Test
//    public int cuttingRope(int n){
//        BigInteger [] bigIntegers = new BigInteger[n+1];
//        for(int i = 2 ;i<=n;i++) {
//            for (int j = 1; j < i; j++) {
//                bigIntegers[i]=Math.max(bigIntegers[i],Math.max(j*(i-j),j*bigIntegers[i-j]));
//            }
//        }
//    }
    public int hammingWeight(int n) {
        int  ret = 0;
        for(int i =0 ; i<32;i++){
            if(((n>>i)&1)!=0){
                ret++;
            }
        }
        return ret;
    }
    public double myPow(double x, int n) {
       long N = n;
       return N>=0?myPowHelper(x,N):1.0/myPowHelper(x,-N);
    }

    private double myPowHelper(double x, long n) {
        if(n==0){
            return 1.0;
        }
        double flg = myPowHelper(x,n/2);
        return n%2==0?flg*flg:flg*flg*x;
    }
    public int[] exchange(int[] nums) {
        int first = 0;
        int last = nums.length-1;
        while(first<=last){

            while(first<=last&&nums[first]%2!=0){
                first++;
            }
            while(first<=last&&nums[last]%2==0){
                last--;
            }
            if(first>last) {
                break;
            }
                int flg = nums[first];
                nums[first] = nums[last];
                nums[last] = flg;

        }
        return nums;
    }
    public ListNode getKthFromEnd(ListNode head, int k) {
        if(head == null){
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        while(k-->0){
            fast = fast.next;
            if(k==0&&fast == null){
                return null;
            }
        }
        while(fast!=null){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
    public ListNode reverseList(ListNode head) {
        if(head == null){
            return null;
        }
        ListNode pre = null;
        ListNode cur = head;
        ListNode curNext = null;
        while(cur!=null){
            curNext = cur.next;
            cur.next = pre;
            pre = cur;
            cur= curNext;
        }
        return pre;
    }
    public ListNode deleteNode(ListNode head, int val) {
        if(head == null){
            return null;
        }
        ListNode pre = null;
        ListNode cur = head;
        while(cur.val!=val&& cur !=null){
            pre = cur;
            cur =cur.next;
        }
        if(pre==null){
            return head.next;
        }
        pre.next = cur.next;
        return head;
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1==null){
            return l2;
        }
        if(l2==null){
            return l1;
        }
        ListNode curL1 = l1;
        ListNode curL2 = l2;
        ListNode fakeNode = new ListNode(-1);
        ListNode cur = fakeNode;
        while(curL1!=null&&curL2!=null){
            if(curL1.val<=curL2.val){
                cur.next=new ListNode(curL1.val);
                cur = cur.next;
                curL1=curL1.next;
            }else if(curL2.val<curL1.val){
                cur.next=new ListNode(curL2.val);
                cur = cur.next;
                curL2 = curL2.next;
            }
        }
        if(curL1==null){
            cur.next = curL2;
        }else{
            cur.next =curL1;
        }
        return fakeNode.next;
    }
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null||headB == null){
            return null;
        }
        int a = sumNode(headA);
        int b = sumNode(headB);
        int flg = a-b;
        if(flg>0){
            while(flg-->0){
                headA = headA.next;
            }
        }else{
            flg = -flg;
            while(flg-->0){
                headB = headB.next;
            }
        }
        while(headA!=headB){
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }

    private int sumNode(ListNode head) {
        int ans = 0;
        while(head!=null){
            ans++;
        }
        return ans;
    }
    public int[] levelOrder(TreeNode root) {
        if(root==null){
            return new int[0];
        }
        Queue <TreeNode>queue= new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            list.add(node.val);
            if(node.left!=null){
                queue.add(node.left);
            }
            if(node.right!=null){
                queue.add(node.right);
            }
        }
        int [] ret = new int[list.size()];
        int index=0;
        for(int num: list){
            ret[index] = num;
            index++;
        }
        return ret;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode fakeNode = new ListNode(-1);
        ListNode cur = fakeNode;
        int sum =0;
        int carry = 0;//进位
        while(l1!=null && l2!=null){
            sum = l1.val + l2.val + carry;
            cur.next = new ListNode(sum%10);
            cur = cur.next;
            carry = sum/10;
            l1 = l1.next;
            l2 = l2.next;
        }
        if(l1 == null && l2 == null){
            if(carry != 0 ){
                cur.next =new ListNode(carry);
                return fakeNode.next;
            }
        }
        if(l1 == null && l2 !=null){
            while(l2!=null) {
                sum = l2.val + carry;
                cur.next = new ListNode(sum%10);
                carry = sum/10;
                l2 = l2.next;
                cur = cur.next;
            }
        }
        if(l1!=null){
            while(l1!=null){
                sum = l1.val + carry;
                cur.next = new ListNode(sum%10);
                carry = sum/10;
                l1 = l1.next;
                cur = cur.next;
            }
        }
        if(carry!=0){
            cur.next = new ListNode(carry);
        }
        return fakeNode.next;
    }

    public static void main(String[] args) {
      CQueue c = new CQueue();
        System.out.println(c.myPow(2.0, 2));

    }
}

