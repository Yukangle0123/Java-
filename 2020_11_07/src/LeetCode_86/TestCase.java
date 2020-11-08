package LeetCode_86;

public class TestCase {
    public static void main(String[] args) {
        ListNode node1=new ListNode(4);
        ListNode node2=new ListNode(1);
        ListNode node3=new ListNode(3);
        ListNode node4=new ListNode(2);
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        Solution s=new Solution();
        s.sortList(node1);

    }
}
