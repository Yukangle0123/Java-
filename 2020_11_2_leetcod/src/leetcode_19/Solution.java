package leetcode_19;


public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
//        ListNode current=head;
//        int listLen=0;
//       while(current!=null){
//           current=current.next;
//           listLen++;
//       }
//     current=head;
//     ListNode pre=null;
//     int index=0;
//     while(index<listLen-n){
//         pre=current;
//         current=current.next;
//         index++;
//     }
//     if(current==head){
//         head=current.next;
//     }else{
//         pre.next=current.next;
//     }
//     return head;
        ListNode dump=new ListNode(0,head);
        ListNode first=head;
        ListNode second=dump;
        for(int i=0;i<n;i++){
            first=first.next;
        }
        while(first!=null){
            second=second.next;
            first=first.next;
        }
        second.next=second.next.next;
        return dump.next;

    }
}
