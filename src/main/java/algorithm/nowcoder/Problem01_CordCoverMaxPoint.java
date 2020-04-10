package algorithm.nowcoder;

//给定一个有序数组arr，代表数轴上从左到右有n个点arr[0]、arr[1]...arr[n－1]，
//给定一个正数L，代表一根长度为L的绳子，求绳子最多能覆盖其中的几个点。
public class Problem01_CordCoverMaxPoint {
	// 第一种解法: 贪心
	// 默认将绳子右端贴到点arr[i]上, 再看最左端能到达的最远的点(0到i之间, 大于arr[i]-L最左位置)
	// 长度为L的绳子最多覆盖几个点，请保证arr有序
	public static int maxPoint(int[] arr, int L) {
		int res = 1;
		for (int i = 0; i < arr.length; i++) {
			int nearest = nearestIndex(arr, i, arr[i] - L);
			res = Math.max(res, i - nearest + 1);
		}
		return res;
	}

	// 在arr[0..R]范围上，找满足>=value的最左位置
	public static int nearestIndex(int[] arr, int R, int value) {
		int L = 0;
		int index = R;
		while (L < R) {
			int mid = L + ((R - L) >> 1);
			if (arr[mid] >= value) {
				index = mid;
				R = mid - 1;
			} else {
				L = mid + 1;
			}
		}
		return index;
	}

	// 第二种解法: 滑动窗口
	// 左侧压中点, 看右侧窗口最大到哪个点
	public static int maxPointWindow(int[] arr, int L) {

		int left = 0, right = 0, res = Integer.MIN_VALUE;
		for (int i = 0; i <= L-1; i++) {
			int sum = 0;
			left = i;
			right = i;
			while (sum <= L) {
				sum += (arr[right+1] - arr[right]);
				if (sum <= L) {
					right++;
				}
			}
			res = Math.max(res, right-left+1);
		}
		return res;
	}

	public static void main(String[] args) {
		int[] arr = { 0, 13, 24, 35, 46, 57, 60, 72, 87 };
		int[] arr2 = { 0, 1, 4, 5, 6, 70, 71, 72, 87 };
		int L = 6;

		System.out.println(maxPoint(arr2, L));
		System.out.println(maxPointWindow(arr2, L));

	}

}
