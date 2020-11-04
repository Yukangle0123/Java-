package Map;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
class Node{
    Node next;
    int val;
    Node(int val){
        this.val=val;
    }
}

public class Solution {
    public int singleNumber(int[] nums) {
        Map<Integer,Integer>m=new TreeMap<>();
        for(int n:nums){
            int count = m.getOrDefault(n,0);
            count++;
            m.put(n,count);
        }
        Set<Map.Entry<Integer, Integer>> entries = m.entrySet();
        for(Map.Entry<Integer, Integer> e:entries){
            int key=e.getKey();
            int value=e.getValue();
            if(value==1){
                return key;
            }
        }
        return -1;
    }
    public Node copy(Node head){
        Node cur=head;
        Node newHead=new Node(-1);
        Node node=newHead;
        while(cur!=null){
            node.next=new Node(cur.val);
            cur=cur.next;
            node=node.next;
        }
        return newHead.next;

    }
}
