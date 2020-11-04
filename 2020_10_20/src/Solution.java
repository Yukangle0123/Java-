import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class NL{
    TreeNode node;
    int sum;
    NL(TreeNode node,int sum){
        this.node=node;
        this.sum=sum;
    }
}

public class Solution {
    public int lastStoneWeight(int[] stones) {
        if (stones.length == 1) {
            return stones[0];
        }
        Arrays.sort(stones);
        while (stones[stones.length - 2] != 0) {
            stones[stones.length - 1] = stones[stones.length - 1] - stones[stones.length - 2];
            stones[stones.length - 2] = 0;
            Arrays.sort(stones);
        }
        return stones[stones.length - 1];
    }
    public int[] twoSum(int[] nums, int target) {
        int n=nums.length;
       for(int i=0;i<n;i++){
           for(int j=i+1;j<n;j++){
               if(target==nums[i]+nums[j]){
                   return new int[]{i,j};
               }
           }
       }
       return new int[0];
    }
    List<List<Integer>>list=new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if(root==null){
            return list;
        }
        pathSumTraversal(root,sum,new ArrayList<>());
        return list;
    }

    private void pathSumTraversal(TreeNode root, int target ,List<Integer> path) {
        if(root==null){
            return;
        }
        path.add(root.val);
        target-=root.val;
        if(target==0&&root.left==null&&root.right==null){
            list.add(new ArrayList(path));
        }else {
            pathSumTraversal(root.left, target, path);
            pathSumTraversal(root.right, target, path);
        }
        path.remove(path.size()-1);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2){
        ListNode head=null;
        ListNode current=null;
        int num=0;
        int size=0;
        while(l1!=null&&l2!=null){
            num=l1.val+l2.val+size;
            if(head==null){
                head=new ListNode(num%10);
                current=head;
                size=num/10;
            }else{
                current.next=new ListNode(num%10);
                current=current.next;
                size=num/10;
            }
            l1=l1.next;
            l2=l2.next;
        }
        if(l1==null&&l2==null){
            if(size>0){
                current.next=new ListNode(size);
            }
            return head;
        }else if(l1==null){
            while(l2!=null){
                num=l2.val+size;
                current.next=new ListNode(num%10);
                current=current.next;
                size=num/10;
                l2=l2.next;
            }
        }else{
            while(l1!=null){
                num=l1.val+size;
                current.next=new ListNode(num%10);
                current=current.next;
                size=num/10;
                l1=l1.next;
            }
        }
        if(size!=0){
          current.next =new ListNode(size);
      }
        return head;
    }


    public int widthOfBinaryTree(TreeNode root) {
        if(root==null){
            return 0;
        }
        Deque<NL>stack=new LinkedList<>();
        stack.offer(new NL(root,1));
        int ans=0;
        while(!stack.isEmpty()){
            int width=0;
            NL first=stack.peekFirst();
            NL last=stack.peekLast();
            width=last.sum-first.sum+1;
            ans=Math.max(ans,width);
            int size=stack.size();
            while(size-->0){
                NL node=stack.poll();
                if(node.node.left!=null){
                    stack.offer(new NL(node.node.left,2*(node.sum-1)+1));
                }
                if(node.node.right!=null){
                    stack.offer(new NL(node.node.right,2*(node.sum-1)+2));
                }
            }
        }
        return ans;
    }
    public boolean isCompleteTree(TreeNode root) {
            if(root==null){
                return true;
            }
            Queue <TreeNode>queue=new LinkedList<>();
            queue.add(root);
            while(!queue.isEmpty()){
                TreeNode node=queue.remove();
                if(node==null){
                    break;
                }
                queue.add(node.left);
                queue.add(node.right);
            }
            if(!queue.isEmpty()){
               while(!queue.isEmpty()){
                   TreeNode n=queue.poll();
                   if(n!=null){
                       return false;
                   }
               }
            }
            return true;
    }
    List<Integer>list1=new LinkedList<>();
    public TreeNode increasingBST(TreeNode root) {
        inOrder(root);
        TreeNode newRoot=new TreeNode(list1.remove(0));
        TreeNode cur=newRoot;
        while(!list1.isEmpty()){
            cur.right=new TreeNode(list1.remove(0));
            cur=cur.right;
        }
        return newRoot;
    }

    private void inOrder(TreeNode root) {
        if(root!=null){
            inOrder(root.left);
            list1.add(root.val);
            inOrder(root.right);
        }
    }
    private void inOrder1(TreeNode root) {
        if(root!=null){
            System.out.println(root.val);
            inOrder(root.left);
            inOrder(root.right);
        }
    }
    TreeNode root=null;
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1==null){
            return t2;
        }
        if(t2==null){
            return t1;
        }
        TreeNode root=new TreeNode(t1.val+t2.val);
        root.left=mergeTrees(t1.left,t2.left);
        root.right=mergeTrees(t2.right,t2.right);
        return root;
    }

    public static void main(String[] args) {
        TreeNode node1=new TreeNode(1);
        TreeNode node2=new TreeNode(2);
        TreeNode node3=new TreeNode(3);
        TreeNode node4=new TreeNode(4);
        TreeNode node5=new TreeNode(5);
        TreeNode node6=new TreeNode(6);
        TreeNode node7=new TreeNode(7);
        TreeNode node8=new TreeNode(8);
        TreeNode node9=new TreeNode(9);

        node5.left=node3;
        node3.left=node2;node3.right=node4;
        node2.left=node1;
        node5.right=node6;
        node6.right=node8;
        node8.left=node7;
        node8.right=node9;
        Solution s=new Solution();
        TreeNode node=s.increasingBST(node5);
        s.inOrder1(node);


    }
}
