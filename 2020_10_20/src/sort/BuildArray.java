package sort;

import java.util.Arrays;
import java.util.Random;
    public class BuildArray {
        public static long[] 构建随机数组() {
            Random random = new Random(20201018);
            long[] array = new long[10];
            for (int i = 0; i < 10; i++) {
                array[i] = random.nextInt(100);
            }
            return array;
        }

        public static long[] 构建完全有序数组() {
            long[] array = 构建随机数组();
            Arrays.sort(array);
            return array;
        }

        private static void swap(long[] array, int i, int j) {
            long t = array[i];
            array[i] = array[j];
            array[j] = t;
        }

        public static long[] 构建完全逆序的数组() {
            long[] array = 构建完全有序数组();
            swap(array, 0, 9);
            swap(array, 1, 8);
            swap(array, 2, 7);
            swap(array, 3, 6);
            swap(array, 4, 5);

            return array;
        }

        public static long[] 构建完全相等的数组() {
            long[] array = new long[10];
            for (int i = 0; i < 10; i++) {
                array[i] = 9;
            }

            return array;
        }
    }

