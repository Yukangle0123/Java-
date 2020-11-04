package leetcode_24;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换
 */

public class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head==null){
            return null;
        }
        if(head.next==null){
            return head;
        }
        ListNode newHead=head.next;
        ListNode fakeNode=new ListNode(0,head);
        ListNode preNode=head;
        ListNode curNode=head.next;
        while(preNode!=null&&curNode!=null){
            ListNode node=curNode.next;

            fakeNode.next=curNode;
            preNode.next=curNode.next;
            curNode.next=preNode;

            fakeNode=preNode;
            preNode=node;
            if(node!=null) {
                curNode = node.next;
            }
        }
        return newHead;
    }
    /*
    剑指offer 18 删除链表中的结点
     */
    public ListNode deleteNode(ListNode head, int val) {
        ListNode fakeNode=new ListNode(0,head);
        ListNode pre=fakeNode;
        ListNode cur=head;
        while(cur!=null){
            if(cur.val==val){
                pre.next=cur.next;
            }
            pre=cur;
            cur=cur.next;
        }
        return fakeNode.next;
    }
    /*
    复杂链表的复制
     */
    public Node copyRandomList(Node head) {
        Node cur=head;
        while(cur!=null){
            Node node=new Node(cur.val);

            node.next=cur.next;
            cur.next=node;

            cur=node.next;
        }
        cur=head;
        while(cur!=null){
            Node newCurrent=cur.next.next;
            if(cur.random!=null){
                cur.next.random=cur.random.next;
            }
            cur=newCurrent;
        }
        Node newHead=head.next;
        Node oldCurrent=head;
        Node newCurrent=head.next;
        while(oldCurrent!=null){
            Node node = newCurrent.next;

            oldCurrent.next=node;
            if(node!=null) {
                newCurrent.next = node.next;
            }
            oldCurrent=oldCurrent.next;
            if(oldCurrent!=null) {
                newCurrent = oldCurrent.next;
            }

        }
    return newHead;
    }
    public int[] reversePrint(ListNode head) {
        Deque<Integer> stack = new LinkedList<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur.val);
            cur = cur.next;
        }
        int[] nums = new int[stack.size()];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = stack.pop();
        }
        return nums;
    }

    public static void main(String[] args) {
        ListNode node1=new ListNode(1);
        ListNode node2=new ListNode(2);
        ListNode node3=new ListNode(3);
        ListNode node4=new ListNode(4);
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        int[] ints = new Solution().reversePrint(node1);
        for(int n:ints){
            System.out.println(n);
        }

    }
}