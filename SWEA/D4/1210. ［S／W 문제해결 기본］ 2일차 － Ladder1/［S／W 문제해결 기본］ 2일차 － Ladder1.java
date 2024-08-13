import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// [SWEA] 1210. Ladder1
public class Solution {

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static int N = 100;
	static int[][] map;
	static boolean[][] isVisited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer answer = new StringBuffer();

		for (int t = 1; t <= 10; t++) {
			// 입력
			map = new int[N][N];
			isVisited = new boolean[N][N];

			int T = Integer.parseInt(br.readLine());
			for (int i = 0; i < 100; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 도착 지점의 좌표에서 거슬러 올라가, 시작 지점의 좌표를 찾는다.
			int start = 0;
			for (int i = 0; i < N; i++) {
				if (map[N - 1][i] == 2) {
					start = dfs(N - 1, i);
					break;
				}
			}

			// 출력
			answer.append("#").append(T).append(" ").append(start).append("\n");
		}

		System.out.println(answer);
	}

	public static int dfs(int x, int y) {
		if (x == 0) {
			return y;
		}

		isVisited[x][y] = true; // 방문 체크

		// 왼쪽으로 가는 경우
		if (y - 1 >= 0 && map[x][y - 1] == 1 && !isVisited[x][y - 1]) {
			return dfs(x, y - 1);
		}

		// 오른쪽으로 가는 경우
		if (y + 1 < N && map[x][y + 1] == 1 && !isVisited[x][y + 1]) {
			return dfs(x, y + 1);
		}

		// 위로 가는 경우
		return dfs(x - 1, y);
	}

}
