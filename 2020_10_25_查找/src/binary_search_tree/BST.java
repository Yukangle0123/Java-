package binary_search_tree;
public class BST {
    private Node root;

    /**
     * 搜索树的查找
     * @param key 要查找的值
     * @return
     */
    public boolean search(Integer key){
        Node current=root;
        while(current!=null){
            int tmp=key.compareTo(current.val);
            if(tmp==0){
                return true;
            }else if(tmp<0){
                current=current.left;
            }else{
                current=current.right;
            }
        }
        return false;
    }

    /**
     *
     * @param key
     */
    public void insert(Integer key){
        if(root==null){
            root=new Node(key);
            return;
        }
        Node current=root;
        Node flg=null;//记录上一个current的值
        int tmp=0;
        while(current!=null){
            tmp=key.compareTo(current.val);
            flg=current;
            if(tmp==0){
                throw new RuntimeException("BTS 中不能插入相同的值！");
            }else if(tmp<0){
                current=current.left;
            }else{
                current=current.right;
            }
        }
        if(tmp<0){
            flg.left=new Node(key);
        }else{
            flg.right=new Node(key);
        }
    }
    public boolean remove(Integer key){
        if(root==null){
            return false;
        }
        //找到val=key的结点 node表示要删除的结点 parent表示该结点的父节点
        Node node=root;
        Node parent=null;
        int tmp=0;
        while(node!=null){
            parent=node;
            tmp=key.compareTo(node.val);
            if(tmp==0){
                removeInternal(node,parent);
                return true;
            }else if(tmp<0){
                node=node.left;
            }else{
                node=node.right;
            }
        }
        return false;
    }
    private void removeInternal(Node node, Node parent) {
        if(node.left==null&&node.right==null){
            if(node==root){
                root=null;
            }else if(node==parent.left){
                parent.left=null;
            }else{
                parent.right=null;
                }
        }else if(node.left!=null&&node.right==null){
            if(node==root){
                root=node.left;
            }else if (parent.left==node) {
                parent.left = node.left;
            } else {
                parent.right = node.left;
            }
        }else if(node.left==null&&node.right!=null){
            if(node==root){
                root=node.right;
            }else if (parent.left==node) {
                parent.left = node.right;
            } else {
                parent.right = node.right;
            }
        }else{
            //替换法 删除node结点  在node 的左子树中找个最大的替换node结点
            Node ghost=node.left;
            Node ghostParent=node;
            while(ghost.right!=null){
                ghostParent=node;
                ghost=ghost.right;
            }
            //循环出来说明ghost已经到了要删除结点的左子树中最大结点处,用该结点替换的val替换node的val;
            node.val=ghost.val;
            //即ghost的右子树为空树
            if(ghostParent==node){
                node.left=ghost.left;
            }else{
                node.right=ghost.left;
            }
        }
    }

    public static void main(String[] args) {
        BST tree=new BST();
        tree.insert(3);
        tree.insert(5);
        tree.insert(7);
        tree.insert(6);
        tree.insert(4);
        tree.insert(9);
        tree.insert(2);
        tree.insert(1);
     for(int i=-1;i<=11;i++){
         System.out.println(i+" "+tree.search(i));
     }
        for(int i=-1;i<=11;i++){
            System.out.println(i+" "+tree.remove(i));
        }
    }

}
