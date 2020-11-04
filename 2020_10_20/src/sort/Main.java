package sort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//       long[] array = BuildArray.构建随机数组();
        long []array={1,2,3,5,7,8,4,6,9,11,8,6,4,44,22};
        System.out.println(Arrays.toString(array));
        Sort.分割(array);
        System.out.println(Arrays.toString(array));
    }
}
