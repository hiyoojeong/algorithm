import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 종이 정보
		int k = sc.nextInt();
		int N = (int) Math.pow(2, k);
		int[][] map = new int[N][N];

		// 모두 접은 후 위치 구하기
		Stack<Character> stack = new Stack<>();
		int r = 0, c = 0, width = N, height = N;
		for (int i = 0; i < 2 * k; i++) {
			String input = sc.next();
			char ch = input.charAt(0);
			stack.add(ch);

			if (ch == 'U') {
				height /= 2;
			} else if (ch == 'D') {
				height /= 2;
				r += height;
			} else if (ch == 'L') {
				width /= 2;
			} else if (ch == 'R') {
				width /= 2;
				c += width;
			}
		}

		// 구멍 뚫는 위치에 구멍 뚫기
		int h = sc.nextInt();
		map[r][c] = h;

		// 종이 피면서 다른 뚫린 구멍 위치 구하기
		while (!stack.isEmpty()) {
			char ch = stack.pop();

			if (ch == 'U') {
				// 반대편 시작점
				int revR = r + 2 * height - 1, revC = c;

				// 반대편 구멍 위치 구하기 - 행 감소, 열 증가
				for (int i = 0; i < height; i++) {
					for (int j = 0; j < width; j++) {
						if (map[r + i][c + j] == 0) {
							map[revR - i][revC + j] = 2;
						} else if (map[r + i][c + j] == 2) {
							map[revR - i][revC + j] = 0;
						} else if (map[r + i][c + j] == 1) {
							map[revR - i][revC + j] = 3;
						} else if (map[r + i][c + j] == 3) {
							map[revR - i][revC + j] = 1;
						}
					}
				}

				// 시작점 이동
				height *= 2;
			} else if (ch == 'D') {
				// 반대편 시작점
				int revR = r - 1, revC = c;

				// 반대편 구멍 위치 구하기 - 행 감소, 열 증가
				for (int i = 0; i < height; i++) {
					for (int j = 0; j < width; j++) {
						if (map[r + i][c + j] == 0) {
							map[revR - i][revC + j] = 2;
						} else if (map[r + i][c + j] == 2) {
							map[revR - i][revC + j] = 0;
						} else if (map[r + i][c + j] == 1) {
							map[revR - i][revC + j] = 3;
						} else if (map[r + i][c + j] == 3) {
							map[revR - i][revC + j] = 1;
						}
					}
				}

				// 시작점 이동
				r -= height;
				height *= 2;
			} else if (ch == 'L') {
				// 반대편 시작점
				int revR = r, revC = c + 2 * width - 1;

				// 반대편 구멍 위치 구하기 - 행 증가, 열 감소
				for (int i = 0; i < height; i++) {
					for (int j = 0; j < width; j++) {
						if (map[r + i][c + j] == 0) {
							map[revR + i][revC - j] = 1;
						} else if (map[r + i][c + j] == 1) {
							map[revR + i][revC - j] = 0;
						} else if (map[r + i][c + j] == 2) {
							map[revR + i][revC - j] = 3;
						} else if (map[r + i][c + j] == 3) {
							map[revR + i][revC - j] = 2;
						}
					}
				}

				// 시작점 이동
				width *= 2;
			} else if (ch == 'R') {
				// 반대편 시작점
				int revR = r, revC = c - 1;

				// 반대편 구멍 위치 구하기 - 행 증가, 열 감소
				for (int i = 0; i < height; i++) {
					for (int j = 0; j < width; j++) {
						if (map[r + i][c + j] == 0) {
							map[revR + i][revC - j] = 1;
						} else if (map[r + i][c + j] == 1) {
							map[revR + i][revC - j] = 0;
						} else if (map[r + i][c + j] == 2) {
							map[revR + i][revC - j] = 3;
						} else if (map[r + i][c + j] == 3) {
							map[revR + i][revC - j] = 2;
						}
					}
				}

				// 시작점 이동
				c -= width;
				width *= 2;
			}
		}

		print(map, N);

		sc.close();
	}

	public static void print(int[][] map, int N) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
