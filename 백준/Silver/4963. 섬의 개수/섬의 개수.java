
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 섬의 개수
class Main {

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static int[] dx = { -1, 1, 0, 0, -1, -1, 1, 1 };
	static int[] dy = { 0, 0, -1, 1, -1, 1, -1, 1 };

	static int N, M;
	static int[][] map;

	static Queue<Pos> q;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer answer = new StringBuffer();

		while (true) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); // 너비 = 열
			N = Integer.parseInt(st.nextToken()); // 높이 = 행

			if (N == 0 && M == 0) {
				break;
			}

			map = new int[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			q = new ArrayDeque<>();
			visited = new boolean[N][M];

			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 1 && !visited[i][j]) {
						cnt++;
						dfs(i, j);
					}
				}
			}

			answer.append(cnt).append("\n");
		}

		System.out.println(answer);
	}

	public static void dfs(int sx, int sy) {
		q.add(new Pos(sx, sy));
		visited[sx][sy] = true;

		while (!q.isEmpty()) {
			Pos now = q.poll();

			for (int k = 0; k < 8; k++) {
				int nx = now.x + dx[k];
				int ny = now.y + dy[k];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny] || map[nx][ny] == 0) {
					continue;
				}

				q.add(new Pos(nx, ny));
				visited[nx][ny] = true;
			}
		}
	}
}