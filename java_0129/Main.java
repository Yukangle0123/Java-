package java_0129;

import java.util.*;

public class Main {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> list = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums){
        subsetsHelper(nums,0);
        return res;
    }

    private void subsetsHelper(int[] nums, int index) {
        if(index == nums.length){
            res.add(new ArrayList<>(list));
            return;
        }
        list.add(nums[index]);
        subsetsHelper(nums,index+1);
        list.remove(list.size()-1);
        subsetsHelper(nums,index+1);
    }
    public int longestIncreasingPath(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row][col];//从(i,j)出发，最长的子串长度
        int res = 0;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                res = Math.max(res,search(matrix,i,j,Integer.MIN_VALUE,dp)+1);
            }
        }
        return res;
    }

    private int search(int[][] matrix, int i, int j, int minValue,int[][]dp) {
        int row = matrix.length;
        int col = matrix[0].length;
        if(i >= row || j >= col || i < 0 || j < 0 || minValue > matrix[i][j]){
            return 0;
        }
        if(dp[i][j] !=0){
            return dp[i][j];
        }
        int max = 0;
        max = Math.max(max,search(matrix,i,j+1,matrix[i][j],dp));
        max = Math.max(max,search(matrix,i,j-1,matrix[i][j],dp));
        max = Math.max(max,search(matrix,i+1,j,matrix[i][j],dp));
        max = Math.max(max,search(matrix,i-1,j,matrix[i][j],dp));
        return max;
    }
    public void DFS(int[] box, int[] used, int index ){
        if(index == box.length){
            for(int i = 1; i < box.length; i++){
                System.out.print(box[i]);
            }
            System.out.println();
            return;
        }
        for(int i = 1; i < box.length; i++){
            if(used[i] == 0){
                used[i] = 1;
                box[index] = i;
                DFS(box,used,index+1);
                used[i] = 0;
            }
        }
    }
    public int getImportance(List<Employee> employees, int id) {
        Map<Integer,Employee> map = new HashMap<>();
        for(Employee e : employees){
            map.put(e.id,e);
        }
        return DFS(map,id);
    }

    private int DFS(Map<Integer, Employee> map, int id) {
        int curImp = map.get(id).importance;
        for(Integer i : map.get(id).subordinates){
            curImp += DFS(map,i);
        }
        return curImp;
    }
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int row = image.length;
        int col = image[0].length;
        int[][] visit = new int[row][col];
        int oldColor = image[sr][sc];
        floodFillHelp(image,oldColor,newColor,visit,row,col,sr,sc);
        return image;
    }

    private void floodFillHelp(int[][] image, int oldColor, int newColor, int[][] visit, int row, int col,int x,int y) {
        if(x < 0 || y < 0 || x >= row || y >= col || visit[x][y] == 1 || image[x][y] != oldColor){
            return;
        }
        image[x][y] = newColor;
        visit[x][y] = 1;
        floodFillHelp(image,oldColor,newColor,visit,row,col,x,y+1);
        floodFillHelp(image,oldColor,newColor,visit,row,col,x,y-1);
        floodFillHelp(image,oldColor,newColor,visit,row,col,x+1,y);
        floodFillHelp(image,oldColor,newColor,visit,row,col,x-1,y);
    }

    public static void main(String[] args) {
        Main m = new Main();
        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int[] box = new int[n+1];
//        int[] used = new int[n+1];
//        m.DFS(box,used,1);
      //  m.subsets(new int[]{1,2,3});
        m.floodFill(new int[][]{{0,0,0},{0,1,0}},1,1,2);
    }
}
