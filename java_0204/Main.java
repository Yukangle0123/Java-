package java_0204;

import java_0131.meiriyiti.TreeNode;
import java_0203.ListNode;

public class Main {
    public int NumberOf1(int n) {
        int flg = 1;
        int res = 0;
        for(int i = 0; i <= 31; i++){
            if((n & flg )!= 0) {
                res++;
            }
            flg <<= 1;
        }
        return res;
    }
    public void reOrderArray(int [] array) {
        int[] nums1 = new int[array.length];
        int[] nums2 = new int[array.length];
        int index1 = 0;
        int index2 = 0;
        for(int n : array){
            if(n % 2 == 0){
                nums2[index2++] = n;
            }else{
                nums1[index1++] = n;
            }
        }
        System.arraycopy(nums1,0,array,0,index1);
        System.arraycopy(nums2,0,array,index1,index2);
    }
    public ListNode ReverseList(ListNode head) {
        if(head == null){
            return null;
        }
        ListNode pre = null;//1,2,3
        ListNode cur = head;
        ListNode next = null;
        while(cur != null){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
    public ListNode Merge(ListNode list1,ListNode list2) {
        ListNode newHead = new ListNode(-1);
        ListNode cur = newHead;
        while(list1 != null && list2 != null){
            if(list1.val > list2.val){
                cur.next = new ListNode(list2.val);
                list2 = list2.next;
            }else{
                cur.next = new ListNode(list1.val);
                list1 = list1.next;
            }
            cur = cur.next;
        }
        if(list2 != null){
            cur.next = list2;
        }
        if(list1 != null){
            cur.next = list1;
        }
        return newHead.next;
    }
    public void Mirror(TreeNode root) {
        if(root == null) {
            return;
        }
        Helper(root.left,root.right);
    }
    public void Helper(TreeNode left, TreeNode right){
        if(left == null && right == null) {
            return;
        }
        TreeNode tmp = left;
        left = right;
        right = tmp;
        if(left != null) {
            Helper(left.left, left.right);
        }
        if(right != null) {
            Helper(right.left, right.right);
        }
    }
    public static void main(String[] args) {
        Main m = new Main();
        TreeNode root = new TreeNode(8);
        
    }
}
