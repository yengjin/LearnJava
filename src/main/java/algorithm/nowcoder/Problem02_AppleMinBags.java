package algorithm.nowcoder;

// 例子: 107个苹果
public class Problem02_AppleMinBags {

	// 第一种: 普通解法(贪心)  O(1)
	// 先全用8个的袋子, 剩余的再用6个处理. 如果有剩余, 减少8袋子数
	public static int minBags(int apple) {
		if (apple < 0) {
			return -1;
		}
		int bag6 = -1;
		int bag8 = apple / 8;
		int rest = apple - 8 * bag8;
		// 如果剩余苹果大于24, 就不可能被整除了
		// 24是6和8的最小公倍数, 24的情况在之前就算过了. 同样情况下, 我们倾向选择8个袋子的. 所以24以上不可能是解
		while (bag8 >= 0 && rest < 24) {
			int restUse6 = minBagBase6(rest);
			if (restUse6 != -1) {
				bag6 = restUse6;
				break;
			}
			// 调整8个的袋子数, -1
			rest = apple - 8 * (--bag8);
		}
		return bag6 == -1 ? -1 : bag6 + bag8;
	}

	// 如果剩余苹果能被6个苹果袋子搞定, 返回袋子数量
	// 不能搞定, 返回-1
	public static int minBagBase6(int rest) {
		return rest % 6 == 0 ? (rest / 6) : -1;
	}

	// 根据值, 憋出代码 ==> 入参1个整数, 出参1个整数 成功率40%以上
	public static int minBagAwesome(int apple) {
		if ((apple & 1) != 0) {
			return -1;
		}
		if (apple < 18) {
			return apple == 0 ? 0 : (apple == 6 || apple == 8) ? 1
					: (apple == 12 || apple == 14 || apple == 16) ? 2 : -1;
		}
		return (apple - 18) / 8 + 3;
	}

	public static void main(String[] args) {
		int max = Integer.MAX_VALUE;
		int testTime = 100000000;
		for (int test = 0; test < testTime; test++) {
			int apple = (int) (Math.random() * max);
			if (minBags(apple) != minBagAwesome(apple)) {
				System.out.println("error");
			}
		}

	}

}
