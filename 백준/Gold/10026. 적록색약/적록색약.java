
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 적록색약
class Main {

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static int N;
	static char[][] map;

	static Queue<Pos> q;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new char[N][N];

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = input.charAt(j);
			}
		}

		// 적록색약이 아닌 사람
		q = new ArrayDeque<>();

		int cnt1 = 0;
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					cnt1++;
					bfs1(i, j, map[i][j]);
				}
			}
		}

		int cnt2 = 0;
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					cnt2++;
					bfs2(i, j, map[i][j]);
				}
			}
		}

		System.out.println(cnt1 + " " + cnt2);
	}

	public static void bfs1(int sx, int sy, char color) {
		q.add(new Pos(sx, sy));
		visited[sx][sy] = true;

		while (!q.isEmpty()) {
			Pos now = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) {
					continue;
				}

				if (map[nx][ny] != color) {
					continue;
				}

				q.add(new Pos(nx, ny));
				visited[nx][ny] = true;
			}
		}
	}

	public static void bfs2(int sx, int sy, char color) {
		q.add(new Pos(sx, sy));
		visited[sx][sy] = true;

		color = map[sx][sy] == 'G' ? 'R' : map[sx][sy]; // 초록색은 빨간색으로 보인다.

		while (!q.isEmpty()) {
			Pos now = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) {
					continue;
				}

				int tmpColor = map[nx][ny] == 'G' ? 'R' : map[nx][ny]; // 초록색은 빨간색으로 보인다.

				if (color != tmpColor) {
					continue;
				}

				q.add(new Pos(nx, ny));
				visited[nx][ny] = true;
			}
		}
	}
}