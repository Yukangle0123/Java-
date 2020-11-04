public class MyPriorityQueue {
    private Integer[]array;
    private int size;
    MyPriorityQueue(){
        this.array=new Integer[1000];
        this.size=0;
    }
    public int element(){
        if(size==0){
            throw  new RuntimeException("空的");
        }
        return array[0];
    }
    public int remove(){
        if(size==0){
            throw new RuntimeException("空的");
        }
        int t=array[0];
        array[0]=array[size-1];
        size--;
        addJustDown(0);
        return t;
    }
    private void addJustDown(int index) {
        while(true){
            int leftIndex=index*2+1;
            if(leftIndex>=size){
                return;
            }
            int rightIndex=leftIndex+1;
            int minIndex=leftIndex;
            if(rightIndex<size&&array[rightIndex]<array[leftIndex]){
                minIndex=rightIndex;
            }
            if(array[index]<array[minIndex]){
                return;
            }
            int t=array[index];
            array[index]=array[minIndex];
            array[minIndex]=t;
            index=minIndex;
        }
    }
    public boolean add(int num){
        if(size==array.length){
            return false;
        }
        array[size]=num;
        size++;
        addJustUp(size-1);
        return true;
    }

    private void addJustUp(int index) {
        while(true){
            if(index==0){
                return;
            }
            int parentIndex=(index-1)/2;
            if(array[parentIndex]<=array[index]){
                return;
            }
            int t=array[index];
            array[index]=array[parentIndex];
            array[parentIndex]=t;
            index=parentIndex;
        }
    }



}
