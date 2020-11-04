package Tree;

public class Test {
    public static void main(String[] args) {
        TreeNode node1=new TreeNode(1);
        TreeNode node2=new TreeNode(2);
        TreeNode node3=new TreeNode(3);
        TreeNode node4=new TreeNode(4);
        TreeNode node5=new TreeNode(5);
        TreeNode node6=new TreeNode(6);
        node1.left=node2;
        node2.left=node4;
        node2.right=node5;
        node1.right=node3;
        node3.right=node6;
//        BinaryTree b=new BinaryTree();
//        b.levelOrderTraversal(node1);

        System.out.printf("%c   %c\n",'v','v');
        System.out.printf(" %c%c\n",'v','v');
        System.out.printf("  %c\n",'v');


    }
}
