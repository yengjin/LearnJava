package algorithm.sort;

public class QuickSort {

    private static void quickSort(int[] arr, int start, int end) {
        if (start > end) return;
        int k = partition(arr, start, end);
        quickSort(arr, start, k-1);
        quickSort(arr, k+1, end);

    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
    private static int partition(int[] arr, int L, int R) {
        int pivot = arr[L];   // 基准数
        int i = L, j = R;
        while (i < j) {
            while (i < j && arr[j] >= pivot) j--;
            while (i < j && arr[i] <= pivot) i++;
            if (i < j) swap(arr, i, j);
        }
        arr[L] = arr[i];
        arr[i] = pivot;
        return i;
    }

    public static void main(String[] args) {
        int[] arr = {3,4,6,7,9,1,5,2,8};
        quickSort(arr, 0, arr.length - 1);
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
