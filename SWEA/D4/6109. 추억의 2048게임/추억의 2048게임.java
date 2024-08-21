import java.util.Scanner;

// 추억의 2048게임
public class Solution {

	static int N;
	static int[][] map;
	static int[][] result;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		StringBuffer answer = new StringBuffer();
		for (int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt(); // 격자 크기
			map = new int[N][N]; // 격자
			result = new int[N][N]; // 결과 격자

			String dir = sc.next();

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			if (dir.equals("up")) {
				up();
			} else if (dir.equals("down")) {
				down();
			} else if (dir.equals("left")) {
				left();
			} else if (dir.equals("right")) {
				right();
			}

			answer.append(String.format("#%d\n", test_case));
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					answer.append(result[i][j]).append(" ");
				}
				answer.append("\n");
			}
		}

		System.out.println(answer);

		sc.close();
	}

	public static void up() {
		for (int j = 0; j < N; j++) {
			// 합치기
			for (int i = 0; i < N - 1; i++) {
				if (map[i][j] == 0)
					continue;

				// 숫자 찾기
				int k = i + 1;
				while (k < N - 1 && map[k][j] == 0) {
					k++;
				}

				// 같은 숫자면 합치기
				if (map[i][j] == map[k][j]) {
					map[i][j] += map[k][j];
					map[k][j] = 0;
				}
			}

			// 옮기기
			int idx = 0;
			for (int i = 0; i < N; i++) {
				if (map[i][j] != 0) {
					result[idx++][j] = map[i][j];
				}
			}
		}

	}

	public static void down() {
		// 합치기
		for (int j = 0; j < N; j++) {
			for (int i = N - 1; i >= 1; i--) {
				if (map[i][j] == 0) {
					continue;
				}

				// 숫자 찾기
				int k = i - 1;
				while (k > 0 && map[k][j] == 0) {
					k--;
				}

				// 같은 숫자면 합치기
				if (map[i][j] == map[k][j]) {
					map[i][j] += map[k][j];
					map[k][j] = 0;
				}
			}

			// 옮기기
			int idx = N - 1;
			for (int i = N - 1; i >= 0; i--) {
				if (map[i][j] != 0) {
					result[idx--][j] = map[i][j];
				}
			}
		}
	}

	public static void left() {
		for (int i = 0; i < N; i++) {
			// 합치기
			for (int j = 0; j < N - 1; j++) {
				if (map[i][j] == 0) {
					continue;
				}

				// 숫자 찾기
				int k = j + 1;
				while (k < N - 1 && map[i][k] == 0) {
					k++;
				}

				// 같은 숫자면 합치기
				if (map[i][j] == map[i][k]) {
					map[i][j] += map[i][k];
					map[i][k] = 0;
				}
			}

			// 옮기기
			int idx = 0;
			for (int j = 0; j < N; j++) {
				if (map[i][j] != 0) {
					result[i][idx++] = map[i][j];
				}
			}
		}

	}

	public static void right() {
		// 합치기
		for (int i = 0; i < N; i++) {
			for (int j = N - 1; j >= 1; j--) {
				if (map[i][j] == 0) {
					continue;
				}

				// 숫자 찾기
				int k = j - 1;
				while (k > 0 && map[i][k] == 0) {
					k--;
				}

				// 같은 숫자면 합치기
				if (map[i][j] == map[i][k]) {
					map[i][j] += map[i][k];
					map[i][k] = 0;
				}
			}

			// 옮기기
			int idx = N - 1;
			for (int j = N - 1; j >= 0; j--) {
				if (map[i][j] != 0)
					result[i][idx--] = map[i][j];
			}
		}

	}

	public static void print() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j] + " ");

			}
			System.out.println();

		}
	}

}
