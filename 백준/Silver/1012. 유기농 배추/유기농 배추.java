import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 유기농 배추
public class Main {

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N, M, K;
	static int[][] map;
	static boolean[][] visited;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		StringBuffer answer = new StringBuffer();
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			visited = new boolean[N][M];

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				map[x][y] = 1;
			}

			answer.append(solution()).append("\n");
		}

		System.out.println(answer);
	}

	public static int solution() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					cnt++;
					dfs(i, j);
				}
			}
		}
		return cnt;
	}

	public static void dfs(int sx, int sy) {
		Queue<Pos> queue = new LinkedList<>();
		queue.add(new Pos(sx, sy));
		visited[sx][sy] = true;

		while (!queue.isEmpty()) {
			Pos now = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nextx = now.x + dx[i];
				int nexty = now.y + dy[i];

				if (nextx >= 0 && nexty >= 0 && nextx < N && nexty < M) {
					if (map[nextx][nexty] == 1 && !visited[nextx][nexty]) {
						visited[nextx][nexty] = true;
						queue.add(new Pos(nextx, nexty));
					}
				}
			}
		}
	}

}
