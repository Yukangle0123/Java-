package LeetCode_94;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {
//    private class TN{
//        TreeNode node;
//        int lev;
//        TN(TreeNode node,int lev){
//            this.node=node;
//            this.lev=lev;
//        }
//    }
    public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer>list=new ArrayList<>();
            Stack<TreeNode>stack=new Stack<>();
            TreeNode current=root;
            while(current!=null||!stack.empty()){
                if(current==null){
                    TreeNode node=stack.pop();
                    list.add(node.val);
                    current=node.right;
                }else {
                    stack.push(current);
                    current = current.left;
                }
            }
            return list;
    }
}
