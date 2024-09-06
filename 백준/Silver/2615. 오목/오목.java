
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 오목
public class Main {

	static final int N = 19;
	static int[][] map = new int[20][20];
	static boolean[][][] visited = new boolean[4][20][20];

	static int[] dx = { 0, 1, 1, -1 }; // 우, 하, 우하, 우상
	static int[] dy = { 1, 0, 1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (map[i][j] != 0) {
					for (int d = 0; d < 3; d++) { // 방향
						if (!visited[d][i][j] && isClear(i, j, map[i][j], d)) {
							System.out.println(map[i][j]);
							System.out.println(i + " " + j);
							return;
						}
					}
				}
			}
		}
		for (int i = N; i >= 1; i--) {
			for (int j = N; j >= 1; j--) {
				if (map[i][j] != 0) {
					if (!visited[3][i][j] && isClear(i, j, map[i][j], 3)) {
						System.out.println(map[i][j]);
						System.out.println(i + " " + j);
						return;
					}
				}
			}
		}
		System.out.println(0);
	}

	public static boolean isClear(int x, int y, int val, int d) {
		// 시작지점에서 5개 연속되는지 확인하기
		int cnt = 0;
		while (map[x][y] == val) {
			cnt++;
			visited[d][x][y] = true; // 현재방향으로 현재지점을 지났음을 체크하기

			x += dx[d];
			y += dy[d];

			if (x <= 0 || y <= 0 || x > N || y > N) {
				break;
			}
		}

		if (cnt == 5) {
			return true;
		}
		return false;
	}

}
