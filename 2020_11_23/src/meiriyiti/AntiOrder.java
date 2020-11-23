package meiriyiti;

public class AntiOrder {
    public int count(int[] A, int n) {
        int count=0;
        for(int i=1;i<A.length;i++){
            for (int j=0;j<i;j++) {
                if(A[j]>A[i]){
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        AntiOrder a=new AntiOrder();
        int[]A=new int[]{1,2,3,4,5,6,7,0};
        System.out.println(a.count(A, 8));
    }
}
