package java_0202;

import java.util.Scanner;

public class Main {
    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String yinfu = sc.next();
            String shifu = sc.next();
            String res = calculation(yinfu,shifu);
            System.out.println(res);
        }
    }

    private static String calculation(String yinfu, String shifu) {
        String[] str1 = yinfu.split("\\.");
        String[] str2 = shifu.split("\\.");
        int[] nums1 = new int[3];
        int[] nums2 = new int[3];
        long num1 = 0;
        long num2 = 0;
        int[] res = new int[3];
        for(int i = 0; i < 3; i++){
            nums1[i] = Integer.parseInt(str1[i]);
            nums2[i] = Integer.parseInt(str2[i]);
        }
        num1 = ((nums1[0] * 17) + nums1[1]) *29 + nums1[2];
        num2 = ((nums2[0] * 17) + nums2[1]) *29 + nums2[2];
        long flg = num2 - num1;
        StringBuilder sb = new StringBuilder();
        if(flg < 0){
            flg = -flg;
            sb.append("-");
        }

        long galleon = flg / (17*29);
        long sickle = (flg %(17*29)/29);
        long knut = flg % 29;
        sb.append(galleon+"."+sickle+"."+knut);

        return sb.toString();
    }
    public static int countNumberOf2s(int n) {
        int res = 0;
        for(int i = 0; i <= n; i++){
            if( i % 10 == 2){
                res++;
            }
            if(i / 10 == 2){
                res++;
            }
        }
        return res;
    }
    public int numIslands(char[][] grid) {
        int row = grid.length;
        if(row == 0){
            return 0;
        }
        int col = grid[0].length;
        int res = 0;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(grid[i][j] == '1'){
                    DFS(grid,row,col,i,j);
                    res++;
                }
            }
        }
        return res;
    }

    private void DFS1(char[][] grid, int row, int col, int x, int y) {
        int[][] path = {{-1,0},{1,0},{0,-1},{0,1}};
        grid[x][y] = '2';
        for(int i = 0; i< 4; i++){
            int nx = x + path[i][0];
            int ny = y + path[i][1];
            if(ny < 0 || ny >= col || nx < 0 || nx >= row
                    || grid[nx][ny] !='1'){
                continue;
            }
            DFS(grid,row,col,nx,ny);
        }
    }
    private void DFS(char[][] grid, int row, int col, int x, int y) {
        grid[x][y] = '2';
        if(y < 0 || y>= col || x < 0 || x >= row
                || grid[x][y] !='1'){
            return;
        }
        DFS(grid,row,col,x+1,y);
        DFS(grid,row,col,x-1,y);
        DFS(grid,row,col,x,y-1);
        DFS(grid,row,col,x,y+1);
    }
    public void solve(char[][] board) {
        int row = board.length;
        if(row == 0){
            return;
        }
        int col = board[0].length;
        for(int i = 0; i < col; i++){
            if(board[0][i] == 'O'){
                DFS2(0,i,row,col,board);
            }
            if(board[row-1][i] == 'O'){
                DFS2(row-1,i,row,col,board);
            }
        }
        for(int j = 1; j < row-1; j++){
            if(board[j][0] =='O'){
                DFS2(j,0,row,col,board);
            }
            if(board[j][col-1] == 'O'){
                DFS2(j,col-2,row,col,board);
            }
        }
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
                if(board[i][j] == 'a'){
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void DFS2(int x, int y, int row, int col, char[][] board) {
        if(x < 0 || x >= row ||y < 0 || y >= col || board[x][y] != 'O'){
            return;
        }
        board[x][y] = 'a';
        DFS2(x,y+1,row,col,board);
        DFS2(x,y-1,row,col,board);
        DFS2(x+1,y,row,col,board);
        DFS2(x-1,y,row,col,board);
    }
    public int longestIncreasingPath(int[][] matrix) {
        int row = matrix.length;
        if(row == 0){
            return 0;
        }
        int col = matrix.length;
        int res = 0;
        int[][] dp = new int[row][col];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                res = Math.max(res,search(i,j,Integer.MIN_VALUE,dp,row,col,matrix));
            }
        }
        return res;
    }
    public int search(int x,int y,int minValue,int[][] dp,int row, int col,int[][] matrix){
        if(x < 0 || x >= row || y < 0 || y >= col || matrix[x][y] <= minValue){
            return 0;
        }
        if(dp[x][y]!=0){
            return dp[x][y];
        }
        int max = 0;
        max = Math.max(max,search(x,y+1,matrix[x][y],dp,row,col,matrix));
        max = Math.max(max,search(x,y-1,matrix[x][y],dp,row,col,matrix));
        max = Math.max(max,search(x+1,y,matrix[x][y],dp,row,col,matrix));
        max = Math.max(max,search(x-1,y,matrix[x][y],dp,row,col,matrix));
        dp[x][y] = max+1;
        return max+1;
    }
    public int JumpFloorII(int target) {
        if(target == 0 || target == 1 || target == 2){
            return target;
        }
        return 2*JumpFloorII(target-1);
    }

    public static void main(String[] args) {
        System.out.println((554%(29*17))/29);
    }
}
