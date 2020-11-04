import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    static class NL{
        TreeNode node;
        int level;
        NL(TreeNode node,int lev){
            this.node=node;
            this.level=lev;
        }
    }
    public List<List<Integer>> levelOrder(TreeNode root){
        List<List<Integer>>list=new LinkedList<>();
        if(root==null){
            return list;
        }
        Queue<NL>queue=new LinkedList<>();
        queue.add(new NL(root,0));
        while(!queue.isEmpty()){
            NL node=queue.remove();
            int lev=node.level;
            TreeNode node1=node.node;
            if(list.size()==lev){
               list.add(new LinkedList<>());
            }
            List<Integer> list1=list.get(lev);
            list1.add(node1.val);
            if(node1.left!=null){
                queue.add(new NL(node1.left,node.level+1));
            }
            if(node1.right!=null){
                queue.add(new NL(node1.right,node.level+1));
            }
        }
        return list;
    }
}
