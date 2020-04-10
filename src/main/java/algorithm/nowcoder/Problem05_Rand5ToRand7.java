package algorithm.nowcoder;

/**
 *
 * 问题:
 * (1) 给定一个函数f，可以1～5的数字等概率返回一个。请加工出1～7的数字等概率返回一个的函数g。
 * (2) 给定一个函数f，可以a～b的数字等概率返回一个。请加工出c～d的数字等概率返回一个的函数g。
 * (3) 给定一个函数f，以p概率返回0，以1-p概率返回1。请加工出等概率返回0和1的函数g
 *
 * 等概率返回思路: 利用**二进制**思想
 * 前提: 有0~1的随机发生器
 * 比如知道13~21, 求30~59.
 * 13~21可以分为 13 14 15 16 和 17 18 19 20 21.  21不是等概率(两边需要相同个数), 出现就重掷
 * 就可以利用13~21的等概率, 变成0~1的等概率发生器.
 * 30~59 可转化为 0~29. 利用5个二进制位可以表示最大 1 + 2 + 4 + 8 + 16 = 31. 那么出现29往上, 重掷.
 * 将结果加上30, 即可.
 *
 * 例如: 需要1~7的随机数, 那么相当于0~6随机 + 1
 * 一共需要随机出3个01二进制位 (可以表示0~7).
 * 如果结果出现7, 重掷即可.
 *
 *
 * 问: 给定一个函数f，以p概率返回0，以1-p概率返回1。请加工出等概率返回0和1的函数g.
 * 利用两个二进制位表示, 掷两次
 * 01 相当于 p * (1 - p)
 * 10 相当于 (1-p) * p, 上面两行**等概率**
 * 00 重掷
 * 11 重掷
 */

public class Problem05_Rand5ToRand7 {

	public static int rand1To5() {
		return (int) (Math.random() * 5) + 1;
	}

	public static int rand1To7() {
		int num = 0;
		do {
			num = (rand1To5() - 1) * 5 + rand1To5() - 1;
		} while (num > 20);
		return num % 7 + 1;
	}

	public static int rand01p() {
		// you can change p to what you like, but it must be (0,1)
		double p = 0.83;
		return Math.random() < p ? 0 : 1;
	}

	public static int rand01() {
		int num;
		do {
			num = rand01p();
		} while (num == rand01p());
		return num;
	}

	public static int rand0To3() {
		return rand01() * 2 + rand01();
	}

	public static int rand1To6() {
		int num = 0;
		do {
			num = rand0To3() * 4 + rand0To3();
		} while (num > 11);
		return num % 6 + 1;
	}

	public static int rand1ToM(int m) {
		return (int) (Math.random() * m) + 1;
	}

	public static int rand1ToN(int n, int m) {
		int[] nMSys = getMSysNum(n - 1, m);
		int[] randNum = getRanMSysNumLessN(nMSys, m);
		return getNumFromMSysNum(randNum, m) + 1;
	}

	// ��valueת��m���Ƶ���
	public static int[] getMSysNum(int value, int m) {
		int[] res = new int[32];
		int index = res.length - 1;
		while (value != 0) {
			res[index--] = value % m;
			value = value / m;
		}
		return res;
	}

	// �ȸ����������һ��0~nMsys��Χ�ϵ�����ֻ������m���Ʊ��ġ�
	public static int[] getRanMSysNumLessN(int[] nMSys, int m) {
		int[] res = new int[nMSys.length];
		int start = 0;
		while (nMSys[start] == 0) {
			start++;
		}
		int index = start;
		boolean lastEqual = true;
		while (index != nMSys.length) {
			res[index] = rand1ToM(m) - 1;
			if (lastEqual) {
				if (res[index] > nMSys[index]) {
					index = start;
					lastEqual = true;
					continue;
				} else {
					lastEqual = res[index] == nMSys[index];
				}
			}
			index++;
		}
		return res;
	}

	// ��m���Ƶ���ת��10����
	public static int getNumFromMSysNum(int[] mSysNum, int m) {
		int res = 0;
		for (int i = 0; i != mSysNum.length; i++) {
			res = res * m + mSysNum[i];
		}
		return res;
	}

	public static void printCountArray(int[] countArr) {
		for (int i = 0; i != countArr.length; i++) {
			System.out.println(i + " appears " + countArr[i] + " times");
		}
	}

	public static void main(String[] args) {
		int testTimes = 1000000;
		int[] countArr1 = new int[8];
		for (int i = 0; i != testTimes; i++) {
			countArr1[rand1To7()]++;
		}
		printCountArray(countArr1);

		System.out.println("=====================");

		int[] countArr2 = new int[7];
		for (int i = 0; i != testTimes; i++) {
			countArr2[rand1To6()]++;
		}
		printCountArray(countArr2);

		System.out.println("=====================");

		int n = 17;
		int m = 3;
		int[] countArr3 = new int[n + 1];
		for (int i = 0; i != 2000000; i++) {
			countArr3[rand1ToN(n, m)]++;
		}
		printCountArray(countArr3);

		System.out.println("=====================");

	}

}
