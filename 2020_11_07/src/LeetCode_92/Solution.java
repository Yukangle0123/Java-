package LeetCode_92;

public class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head==null){
            return null;
        }
        ListNode pre=null;
        ListNode cur=head;
        while(m>1){
            pre=cur;
            cur=cur.next;
            m--;
            n--;
        }
        ListNode tail=cur,con=pre;
        ListNode third=null;
        while(n>0){
            third=cur.next;
            cur.next=pre;

            pre = cur;
            cur=third;

            n--;
        }
        if(con!=null) {
            con.next = pre;
        }else{
            head=pre;
        }

        tail.next=cur;

        return head;
    }
    public static void main(String[] args) {

    }
}
