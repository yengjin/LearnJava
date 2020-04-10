package algorithm.sort;

public class HeapSort {

    private static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];//先取出当前元素i
        for(int k=i*2+1;k<length;k=k*2+1){//从i结点的左子结点开始，也就是2i+1处开始
            if(k+1<length && arr[k]<arr[k+1]){//如果左子结点小于右子结点，k指向右子结点
                k++;    // 这里k指向左右子节点中较大的
            }
            if(arr[k] >temp){//如果子节点大于父节点，交换
                swap(arr, i, k);
                i = k;  // 这里有可能交换后, 对子树产生影响, 所以需要再判断一遍
            }else{
                // 不用交换, 也就说明根节点比左右节点更大,
                // 而左右节点在之前已经调整过, 所以当前根肯定是最大的, 最大堆构建成功
                // 关键点: 在大顶堆基础上, 从上到下, 从左到右进行调整.
                break;
            }
        }
    }
    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
    private static void heapSort(int[] arr) {
        // 1. 构建初始大顶堆
        for (int i = arr.length/2-1; i >= 0; i--) {
            // 从第一个非叶子节点从下往上, 从右到左调整结构
            adjustHeap(arr, i, arr.length);
        }
        // 2. 交换堆顶元素与末尾元素, 紧接着排除最后一个元素继续调整堆
        for (int j = arr.length - 1; j > 0; j--) {
            swap(arr, 0, j);    // 交换arr[0](堆顶), arr[j](末尾)
            adjustHeap(arr, 0, j);  // 调整[0, j)之间的最大堆
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
