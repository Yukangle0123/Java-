package java0205;

import java_0131.meiriyiti.TreeNode;

import java.util.*;

public class Main {
    /**
     * 顺时针打印矩阵
     */
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        int row = matrix.length;
        ArrayList<Integer> array = new ArrayList<>();
        if(row == 0){
            return array;
        }
        int col = matrix[0].length;
        int rowStart = 0;
        int rowEnd = row - 1;
        int colStart = 0;
        int colEnd = col - 1;
        while(rowStart <= rowEnd && colStart <= colEnd){
            for(int i = colStart; i <= colEnd; i++){
                array.add(matrix[rowStart][i]);
            }
            rowStart++;
            for(int i = rowStart; i <= rowEnd; i++){
                array.add(matrix[i][colEnd]);
            }
            colEnd--;
            if (rowStart <= rowEnd) {
                for(int i = colEnd; i >= colStart; i--){
                    array.add(matrix[rowEnd][i]);
                }
            }


            rowEnd--;
            if (colStart <= colEnd) {
                for(int i = rowEnd; i >= rowStart; i--){
                    array.add(matrix[i][colStart]);
                }
            }

            colStart++;
        }

        return array;
    }
    /*
     *  栈的压入，弹出序列
     * [1,2,3,4,5],[4,3,5,2,1]
     */
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        Stack<Integer> stack = new Stack<>();
        int index = 0;
        for(int n : pushA){
            stack.push(n);
            while(!stack.isEmpty() && stack.peek() == popA[index]){
                stack.pop();
                index++;
            }
        }
        return stack.isEmpty();
    }
    /*
     *从上到下打印二叉树
     */
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        if(root == null){
            return arrayList;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            arrayList.add(node.val);
            if(node.left != null){
                queue.add(node.left);
            }
            if(node.right != null){
                queue.add(node.right);
            }
        }
        return arrayList;
    }
    public boolean VerifySquenceOfBST(int [] sequence) {
        int len = sequence.length-1;
        return Helper(sequence,0,len);
    }

    private boolean Helper(int[] sequence, int left, int right) {
        if(left >= right){
            return true;
        }
        int index = left;
        int root = sequence[right];
        while(sequence[index] < root){
            index++;
        }
        int tmp = index;
        while(tmp < right){
            if(sequence[tmp] < root){
                return false;
            }
            tmp++;
        }
        return Helper(sequence,left,index-1) && Helper(sequence,left,right-1);
    }

    public static void main(String[] args) {
        Main m = new Main();
        int[] nums = new int[]{1,2,3,4,5};
        int[] nums2 = new int[]{4,3,5,2,1};
        System.out.println(m.IsPopOrder(nums, nums2));
    }
}
