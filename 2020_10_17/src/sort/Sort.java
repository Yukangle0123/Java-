package sort;

import java.util.Arrays;

public class Sort {
    public static void insertSort(long[]array){//11,22,55,7,55,36,51,20
        for(int i=0;i<array.length-1;i++){
            long key=array[i+1];
            int j;
            for(j=i;j>=0;j--){
                if(key<array[j]){
                    array[j+1]=array[j];
                }else {
                    break;
                }
            }
            //说明key大于或者等于array[j]
            array[j+1]=key;
        }
    }
    public static void selectSort(long[]array){
        for(int i=array.length-1;i>=0;i--){
            int index=0;
            for(int j=0;j<=i;j++){
                if(array[index]<array[j]){
                    index=j;
                }
            }
            long t=array[i];
            array[i]=array[index];
            array[index]=t;
        }
    }
    private static void adjustDown(long[]array,int size,int index){
        while(index * 2 + 1<size) {
            int maxIndex = index * 2 + 1;
            int rightIndex=maxIndex+1;
            if(rightIndex<size&&array[maxIndex]<array[rightIndex]){
                maxIndex=rightIndex;
            }
            if(array[index]>=array[maxIndex]){
                return;
            }
            long t=array[maxIndex];
            array[maxIndex]=array[index];
            array[index]=t;
            index=maxIndex;
        }

    }
    private static void buildHeap(long[]array){
        int index=array.length-1;
        int parentIndex=(index-1)/2;
        for(int i=parentIndex;i>=0;i--){
            adjustDown(array,index,i);
        }
    }
    public static void heapSort(long[]array){
        buildHeap(array);
        for(int i=0;i<array.length-1;i++){
            long t=array[0];
            array[0]=array[array.length-i-1];
            array[array.length-i-1]=t;
            adjustDown(array,array.length-1-i,0);
        }
    }
    private static void insertSortGap(long[]array,int gap) {
        for (int i = gap; i < array.length; i++) {
            long key=array[i];
            int j;
            for( j=i-gap;j>=0;j=j-gap){
                if(array[j]>key){
                    array[j+gap]=array[j];
                }else{
                    break;
                }
            }
            array[j+gap]=key;
        }
    }
    public static void shellSort(long[]array){
        int gap = array.length / 2;
        while (true) {
            insertSortGap(array, gap);
            if (gap == 1) {
                break;
            }
            gap = gap / 2;
        }
    }
    private static int 数位数(long n){
        int r=0;
        if(n==0){
            return 1;
        }
        while(n>0){
            n/=10;
            r++;
        }
        return r;
    }
    public static int[]转换(long n){
        int num=数位数(n);
        int s=(int)Math.pow(10,num-1);
        int []array=new int[num];
        for(int i=0;i<num;i++){
            array[i]=(int)n/s;
            n%=s;
            s/=10;
        }
        return array;
    }

    public static void main(String[] args) {
      int []a=转换(0);
        System.out.println(Arrays.toString(a));
    }
}
