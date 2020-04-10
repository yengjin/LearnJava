package algorithm.nowcoder;


public class Problem03_ColorLeftRight {
	/**
	 * 基本思路: 分成两段 O(N^2)
	 * 左侧: G, 右侧: R
	 * 假设左侧部分大小为L, 右侧部分大小为 N-L
	 *
	 * for (int L = 0; L <= N; L++) {
	 *     if (L == 0) {
	 *         统计arr[0..N-1]一共有多少个G, 全部染成R (第一种极端情况)
	 *     } else if (L == N) {
	 *         统计arr[0..N-1]一共有多少个R, 全部染成G (第二种极端情况)
	 *     } else {
	 *         统计arr[0..L]一共有多少个G, 全部染成R + 统计arr[L+1..N-1]一共有多少个R, 全部染成G
	 *     }
 	 * }
	 *
	 */


	/**
	 * 最优方法: 基于上一种分区, 优化使用预处理辅助数组 O(N)
	 *  G R R G R G
	 * [3 3 2 2 1 0]	==> 从右到左0到某一个位置结束的R的数量
	 */
	// RGRGR -> RRRGG
	public static int minPaint(String s) {
		if (s == null || s.length() < 2) {
			return 0;
		}
		char[] chs = s.toCharArray();
		int[] right = new int[chs.length];
		// 初始化
		right[chs.length - 1] = chs[chs.length - 1] == 'R' ? 1 : 0;
		// 计算
		for (int i = chs.length - 2; i >= 0; i--) {
			right[i] = right[i + 1] + (chs[i] == 'R' ? 1 : 0);
		}
		int res = right[0];
		int left = 0;
		for (int i = 0; i < chs.length - 1; i++) {
			left += chs[i] == 'G' ? 1 : 0;
			res = Math.min(res, left + right[i + 1]);
		}
		res = Math.min(res, left + (chs[chs.length - 1] == 'G' ? 1 : 0));
		return res;
	}

	public static void main(String[] args) {
		String test = "GGGGGR";
		System.out.println(minPaint(test));

	}

}
