package LeetCode_86;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode before_head=new ListNode(0);
        ListNode after_head=new ListNode(0);
        ListNode before=before_head;
        ListNode after=after_head;
        ListNode cur=head;
        while(cur!=null){
            if(cur.val>x){
                after.next=new ListNode(cur.val);
                after=after.next;
            }else{
                before.next=new ListNode(cur.val);
                before=before.next;
            }
        }
        before_head.next=after_head.next;
        return before_head.next;

    }
    public ListNode sortList(ListNode head) {
        PriorityQueue<Integer> queue=new PriorityQueue<>();
        ListNode cur=head;
        while(cur!=null){
            queue.add(cur.val);
            cur=cur.next;
        }
        ListNode fakeNode=new ListNode(-1);
        cur=fakeNode;
        while(!queue.isEmpty()){
            cur.next=new ListNode(queue.remove());
            cur=cur.next;
        }
        System.out.println("dede");
        return fakeNode.next;
    }
}
