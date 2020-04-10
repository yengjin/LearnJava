package algorithm.nowcoder;

// 暴力: O(N^4) --- 找正方形: 枚举左上角点O(N^2)和边长O(N)    +   验证: O(N)

// 最优解: 使用预处理 -- 找正方形O(N^3) + 验证: O(1)
// right[][]: 包含自身在内, 右侧有几个连续的1
// down[][]: 包含自身在内, 下方有几个连续的1
public class Problem04_MaxOneBorderSize {

	public static void setBorderMap(int[][] m, int[][] right, int[][] down) {
		int r = m.length;
		int c = m[0].length;
		if (m[r - 1][c - 1] == 1) {
			right[r - 1][c - 1] = 1;
			down[r - 1][c - 1] = 1;
		}
		for (int i = r - 2; i != -1; i--) {
			if (m[i][c - 1] == 1) {
				right[i][c - 1] = 1;
				down[i][c - 1] = down[i + 1][c - 1] + 1;
			}
		}
		for (int i = c - 2; i != -1; i--) {
			if (m[r - 1][i] == 1) {
				right[r - 1][i] = right[r - 1][i + 1] + 1;
				down[r - 1][i] = 1;
			}
		}
		for (int i = r - 2; i != -1; i--) {
			for (int j = c - 2; j != -1; j--) {
				if (m[i][j] == 1) {
					right[i][j] = right[i][j + 1] + 1;
					down[i][j] = down[i + 1][j] + 1;
				}
			}
		}
	}

	public static int getMaxSize(int[][] m) {
		int[][] right = new int[m.length][m[0].length];
		int[][] down = new int[m.length][m[0].length];
		setBorderMap(m, right, down);
		for (int size = Math.min(m.length, m[0].length); size != 0; size--) {
			if (hasSizeOfBorder(size, right, down)) {
				return size;
			}
		}
		return 0;
	}

	public static boolean hasSizeOfBorder(int size, int[][] right, int[][] down) {
		for (int i = 0; i != right.length - size + 1; i++) {
			for (int j = 0; j != right[0].length - size + 1; j++) {
				if (right[i][j] >= size && down[i][j] >= size
						&& right[i + size - 1][j] >= size
						&& down[i][j + size - 1] >= size) {
					return true;
				}
			}
		}
		return false;
	}

	public static int[][] generateRandom01Matrix(int rowSize, int colSize) {
		int[][] res = new int[rowSize][colSize];
		for (int i = 0; i != rowSize; i++) {
			for (int j = 0; j != colSize; j++) {
				res[i][j] = (int) (Math.random() * 2);
			}
		}
		return res;
	}

	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i != matrix.length; i++) {
			for (int j = 0; j != matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int[][] matrix = generateRandom01Matrix(7, 8);
		printMatrix(matrix);
		System.out.println(getMaxSize(matrix));
	}
}
