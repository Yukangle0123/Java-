package nonrecursive;

import java.util.Deque;
import java.util.LinkedList;

public class TreeNonRecursive {
    public static void preOrder(TreeNode root){
        TreeNode current=root;
        Deque<TreeNode>stack=new LinkedList<>();
        while(!stack.isEmpty()||current!=null){
            while(current!=null){
                System.out.printf("%d ",current.val);
                stack.push(current);
                current=current.left;
            }
            TreeNode node=stack.pop();
            current=node.right;
        }
    }
    public static void inOrder(TreeNode root){
        TreeNode current=root;
        Deque<TreeNode>stack=new LinkedList<>();
        while(!stack.isEmpty()||current!=null){
            while(current!=null){
                stack.push(current);
                current=current.left;
            }
            TreeNode node=stack.pop();
            System.out.printf("%d ",node.val);
            current=node.right;
        }
    }
    public static void postOrder(TreeNode root){
        TreeNode current=root;
        TreeNode last=null;
        Deque<TreeNode>stack=new LinkedList<>();
        while(!stack.isEmpty()||current!=null){
            while(current!=null){
                stack.push(current);
                current=current.left;
            }
            TreeNode node=stack.peek();
            if(node.right ==null||node.right==last){
                stack.pop();
                last=node;
                System.out.printf("%d ",node.val);
            } else{
                current=node.right;
            }

        }
    }
    public static void main(String[] args) {
        TreeNode node1=new TreeNode(1);
        TreeNode node2=new TreeNode(2);
        TreeNode node3=new TreeNode(3);
        TreeNode node4=new TreeNode(4);
        TreeNode node5=new TreeNode(5);
        TreeNode node6=new TreeNode(6);
        node1.left=node2;
        node2.left=node3;
        node2.right=node4;
        node1.right=node5;
        node5.right=node6;
        TreeNode root=node1;
        preOrder(root);
        System.out.println();
        inOrder(root);
        System.out.println();
        postOrder(root);
    }
}
