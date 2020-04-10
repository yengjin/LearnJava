package algorithm.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

public class HeapSortPractice {

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
    // 调整 arr[i, arr)
    private static void adjust(int[] arr, int i, int length) {
        int temp = arr[i];  // 先保存最大节点
        for (int k = i*2+1; k < length; k = k * 2) {
            if (k+1 < length && arr[k] < arr[k+1]) {
                k++;    // k指向较大节点
            }
            // 比较根节点和左右子节点的较大值, 看哪个最大
            if (arr[k] > temp) {
                swap(arr, k, i);    // 交换
                i = k;  // 交换后需要调整发生变化的子树 (没发生变化的本身就是大根堆, 不需要调整)
            } else {
                break;
            }
        }
    }



    // 堆排序
    private static void heapSort(int[] arr) {
        // 1. 构建最大堆, 从第一个非叶子节点, 从下往上调整结构
        // 建初始堆, 从下到上, (以下方的堆为基础, 调整)
        for (int i = arr.length / 2 + 1; i >= 0; i--) {
            adjust(arr, i, arr.length);
        }

        // 调整堆, 从上到下, (以初始堆为基础, 从上到下调整)
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, 0, i);
            adjust(arr, 0, i);
        }

    }

    public static void main(String[] args) {
        int[] arr = {3,4,6,7,9,1,5,2,8};
        heapSort(arr);
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
