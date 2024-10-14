
import java.util.Scanner;

// 활주로 건설
class Solution {

	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };
	static int UP = 0, LEFT = 1, DOWN = 2, RIGHT = 3;

	static int N, X, map[][];
	static int[][] visited;

	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		StringBuffer answer = new StringBuffer();
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt();
			X = sc.nextInt();
			map = new int[N][N];
			visited = new int[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					for (int d = 0; d < 4; d++) {
						if (install(i, j, d, false)) {
							install(i, j, d, true);
						}
					}
				}
			}

			int cnt = 0;
			for (int i = 0; i < N; i++) {
				cnt += checkVerticle(i);
				cnt += checkHorizontal(i);
			}

			answer.append(String.format("#%d %d\n", test_case, cnt));
		}

		System.out.println(answer);
		sc.close();
	}

	private static int checkVerticle(int c) {
		for (int r = 0; r < N - 1; r++) {
			// 높이가 같은지 확인한다.
			if (map[r][c] == map[r + 1][c]) {
				continue;
			}
			// 높이가 1 작은 쪽에 경사로가 있는지 확인한다.
			if (map[r][c] - 1 == map[r + 1][c] && (visited[r + 1][c] & (1 << DOWN)) != 0) {
				continue;
			}
			if (map[r + 1][c] - 1 == map[r][c] && (visited[r][c] & (1 << UP)) != 0) {
				continue;
			}
			return 0;
		}
		return 1;
	}

	private static int checkHorizontal(int r) {
		for (int c = 0; c < N - 1; c++) {
			// 높이가 같은지 확인한다.
			if (map[r][c] == map[r][c + 1]) {
				continue;
			}
			// 높이가 1 작은 쪽에 경사로가 있는지 확인한다.
			if (map[r][c] - 1 == map[r][c + 1] && (visited[r][c + 1] & (1 << RIGHT)) != 0) {
				continue;
			}
			if (map[r][c + 1] - 1 == map[r][c] && (visited[r][c] & (1 << LEFT)) != 0) {
				continue;
			}
			return 0;
		}
		return 1;
	}

	private static boolean install(int r, int c, int d, boolean isInstall) {
		int nr = r;
		int nc = c;

		for (int i = 0; i < X; i++) {
			nr += dr[d];
			nc += dc[d];

			// 범위를 벗어난 경우
			if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
				return false;
			}
			// 이미 경사로가 설치된 경우 (상하끼리, 좌우끼리는 겹쳐서 경사로를 설치할 수 없다.)
			if ((visited[nr][nc] & (1 << d)) != 0 || (visited[nr][nc] & (1 << ((d + 2) % 4))) != 0) {
				return false;
			}
			// 높이가 1 차이가 아닌 경우
			if (map[r][c] - 1 != map[nr][nc]) {
				return false;
			}

			if (isInstall) {
				visited[nr][nc] |= (1 << d);
			}
		}

		return true;
	}
}