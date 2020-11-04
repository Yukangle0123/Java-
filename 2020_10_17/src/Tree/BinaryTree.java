package Tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    public void preOrder(TreeNode root){
        if(root!=null){
            System.out.println(root.val);
            preOrder(root.left);
            preOrder(root.right);
        }
    }
    public void inOrder(TreeNode root){
        if(root!=null){
            inOrder(root.left);
            System.out.println(root.val);
            inOrder(root.right);
        }
    }
    public void postOrder(TreeNode root){
        if(root!=null){
            postOrder(root.left);
            postOrder(root.right);
            System.out.println(root.val);
        }
    }
    public void preOrder2(TreeNode root){
        if(root==null){
            return;
        }
        Deque<TreeNode>stack=new LinkedList<>();
        TreeNode current=root;
        while(!stack.isEmpty()||current!=null){
            while(current!=null){
                System.out.println(current.val);
                stack.push(current);
                current=current.left;
            }
            TreeNode node=stack.pop();
            current=node.right;

        }
    }
    public void inOrder2(TreeNode root){
        if(root==null){
            return;
        }
        Deque<TreeNode>stack=new LinkedList<>();
        TreeNode current=root;
        while(!stack.isEmpty()||current!=null){
            while(current!=null){
                stack.push(current);
                current=current.left;
            }
            TreeNode node=stack.pop();
            System.out.println(node.val);
            current=node.right;
        }
    }
    public void postOrder1(TreeNode root){
        if(root==null){
            return;
        }
        Deque<TreeNode>stack=new LinkedList<>();
        TreeNode current=root;
        TreeNode last=null;
        while(!stack.isEmpty()||current!=null){
            while(current!=null){
                stack.push(current);
                current=current.left;
            }

            TreeNode node=stack.peek();
            if(node.right==null){
                last=node;
                stack.pop();
                System.out.println(node.val);
            }else if(node.right==last){
                last=node;
                stack.pop();
                System.out.println(node.val);
            }else{
                current=node.right;
            }

        }
    }
    static int size=0;
    public void getSize1(TreeNode root){
        if(root!=null){
            size++;
            getSize1(root.left);
            getSize1(root.right);
        }
    }
    public int getSize(TreeNode root){
        if(root==null){
            return 0;
        }
        return getSize(root.left)+getSize(root.right)+1;
    }
    // 遍历思路-求叶子结点个数
    static int leafSize = 0;
    public void getLeafSize1(TreeNode root){
        if(root!=null){
            if(root.left==null&&root.right==null){
                leafSize++;
            }
            getLeafSize1(root.left);
            getLeafSize1(root.right);
        }
    }
    public int getLeafSize2(TreeNode root){
        if(root==null){
            return 0;
        }
        if(root.left==null&&root.right==null){
            return 1;
        }
        return getLeafSize2(root.left)+getLeafSize2(root.right);
    }
    public int getKLevelSize(TreeNode root,int k){
        if(root==null){
            return 0;
        } else if(k==1){
            return 1;
        }
        return getKLevelSize(root.left,k-1)+getKLevelSize(root.right,k-1);
    }
    public int getHeight(TreeNode root){
        if(root==null){
            return 0;
        }
        return 1+Math.max(getHeight(root.left),getHeight(root.right));
    }
    public boolean isBalanced(TreeNode root){
        if(root==null){
            return true;
        }
        if(Math.abs(getHeight(root.left)-getHeight(root.right))>1){
            return false;
        }
        if(!isBalanced(root.left)){
            return false;
        }
        return isBalanced(root.right);
    }
    public TreeNode find(TreeNode root, int val){
        if(root==null){
            return null;
        }
        if(root.val==val){
            return root;
        }
        TreeNode node=find(root.left,val);
        if(node!=null){
            return node;
        }
        return find(root.right,val);
    }
    public boolean isSameTree(TreeNode p, TreeNode q) {
        //当两棵树都是空树的时候这两棵树是相同的树
        if(p==null&&q==null){
            return true;
        }
        //程序能走到这里说明其中一棵树是空树一棵树不是空树，则不是相同的树
        if(p==null||q==null){
            return false;
        }
        //当两颗树的根的val相同，左右子树都相同时是相同的树，否则不是
        return p.val==q.val&&isSameTree(p.left,q.left)&&isSameTree(p.right,q.right);
    }
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if(s==null){
            return false;
        }
        if(isSameTree(s,t)){
            return true;
        }
        if(isSubtree(s.left,t)){
            return true;
        }
        return isSubtree(s.right,t);
    }
    public boolean isImageTree(TreeNode p, TreeNode q){
        if(p==null&&q==null){
            return true;
        }

        if(p==null||q==null){
            return false;
        }

        return p.val==q.val&&isSameTree(p.left,q.right)&&isSameTree(p.right,q.left);
    }
    public boolean isSymmetric(TreeNode root) {
        if(root==null){
            return true;
        }
        return isImageTree(root.left,root.right);
    }
    public void levelOrderTraversal(TreeNode root){
        if(root==null){
            return;
        }
        Queue<TreeNode>queue=new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.remove();
            System.out.println(node.val);
            if(node.left!=null){
                queue.add(node.left);
            }
            if(node.right!=null){
                queue.add(node.right);
            }
        }
    }
    public boolean isCompleteTree(TreeNode root){
        if(root==null){
            return false;
        }
        Queue<TreeNode>queue=new LinkedList<>();
        queue.add(root);
        while(true){
            TreeNode node = queue.remove();
            if(node==null){
                break;
            }
            queue.add(node.left);
            queue.add(node.right);
        }
        while(!queue.isEmpty()){
            TreeNode node=queue.remove();
            if(node!=null){
                return false;
            }
        }
        return true;
    }
}
