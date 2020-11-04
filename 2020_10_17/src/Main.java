import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
        public String toString(){
            return String.format("TreeNode{%d}",this.val);
        }
    }
    public class Main {
//        private static List<Character> arrayToList(String str){
//
//        }
        public static TreeNode buildTree(List<Character> in, List<Character> out){
            if(in.isEmpty()){
                return null;
            }
            Character c=in.remove(0);
            TreeNode root=new TreeNode(c);
            if(c=='#'){
                out.addAll(in);
                return null;
            }
            List<Character>innerList=new ArrayList<>();
            root.left=buildTree(in,innerList);
            root.right=buildTree(innerList,out);
            return root;
        }


        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
 //           List<Character> in=arrayToList(s);
            List<Character>out=new LinkedList<>();

  //          TreeNode root = buildTree(in, out);
//            preOrder(root);

        }
    }
