package binary_search_tree;

import javafx.scene.input.DataFormat;

import java.util.Date;

public class Node {
    Integer val;
    Node left;
    Node right;
    Node(int val){
        this.val=val;
    }
    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                '}';
    }

    public static void main(String[] args) {
        DataFormat da=new DataFormat();
        Date d=new Date();
        System.out.println(d);
        
    }
}
