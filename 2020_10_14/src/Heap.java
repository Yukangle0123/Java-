public class Heap {
    public static void addJustDown(int []array,int size,int index) {
    while (true){
        int leftIndex = index * 2 + 1;
        //index位置是叶子结点
        if (leftIndex >= size) {
            return;
        }
        //找最小孩子
        int minIndex = leftIndex;
        int rightIndex = leftIndex + 1;
        if (rightIndex<size && array[rightIndex]<array[leftIndex]) {
            minIndex=rightIndex;
        }
        if(array[index]<=array[minIndex]){
            return;
        }
        int t = array[index];
        array[index] = array[minIndex];
        array[minIndex] = t;
        index = minIndex;
        }
    }
    public static void createHeap(int []array ,int size){
        int leafIndex=size-1;//层序遍历的最后一个结点
        int parentIndex=(leafIndex-1)/2;
        for(int i=parentIndex;i>=0;i--){
            addJustDown(array,size,i);
        }
    }
    public static void addJustUp(int []array,int size,int index){

        while(true){
            if(index==0){
                return;
            }
            int parentIndex=(index-1)/2;
            if(array[parentIndex]<=array[index]){
                return;
            }
            int t=array[parentIndex];
            array[parentIndex]=array[index];
            array[index]=t;
            index=parentIndex;
        }

    }
}
