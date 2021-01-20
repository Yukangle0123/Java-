package com.code.leetcode;
class ListNode{
    ListNode next;
    int val;
    ListNode(int val){
        this.val=val;
    }
}

public class demo {
    public boolean chkPalindrome(ListNode head){
        if(head == null){
            return true;
        }
        ListNode fast = head;
        ListNode slow = head;
//        if(head.next != null) {
//            fast = head.next.next;
//            slow = head.next;
//        }
        while(fast != null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode cur= slow;
        ListNode curNext;
        while(cur != null){
            curNext = cur.next;
            cur.next=slow;
            slow=cur;
            cur=curNext;
        }
        cur = head;
        while(cur!=slow){
            if(cur.val != slow.val){
                return false;
            }
            if(cur.next == slow){
                return true;
            }
            cur = cur.next;
            slow = slow.next;
        }
        return true;
    }
}
