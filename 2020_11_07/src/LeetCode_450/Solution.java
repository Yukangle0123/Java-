package LeetCode_450;

public class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode cur=root;
        TreeNode parent=null;
        while(cur!=null){
            int sign=key-cur.val;
            if(sign==0){
               return deleteInternal(root,cur,parent);
            }else if(sign<0){
                parent=cur;
                cur=cur.left;
            }else{
                parent=cur;
                cur=cur.right;
            }
        }
        return root;
    }

    private TreeNode deleteInternal(TreeNode root,TreeNode cur, TreeNode parent) {
        if(cur.left==null&&cur.right==null){
            if(cur==root){
                return null;
            }if(parent.left==cur){
                parent.left=null;
            }else{
                parent.right=null;
            }
            return root;
        }
        if(cur.left==null&&cur.right!=null){
            if(cur==root){
                root=root.right;
                return root;
            }
            if(parent.left==cur){
                parent.left=cur.right;
            }else{
                parent.right=cur.right;
            }
            return root;
        }
        if(cur.left!=null&&cur.right==null){
            if(cur==root){
                root=root.left;
                return root;
            }
            if(parent.left==cur){
                parent.left=cur.left;
            }else{
                parent.right=cur.left;
            }
            return root;
        }
        TreeNode ghostParent=cur;
        TreeNode ghost=cur.right;
        while(ghost.left!=null){
            ghostParent=ghost;
            ghost=ghost.left;
        }
        cur.val=ghost.val;
        if(ghostParent==cur){
            ghostParent.right=ghost.right;
        }else{
            ghostParent.left=ghost.right;
        }
        return root;
    }
}
