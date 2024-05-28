import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 주난의 난
public class Main {

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N, M;
	static char[][] map;

	static int sx, sy, ex, ey;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];

		st = new StringTokenizer(br.readLine());
		sx = Integer.parseInt(st.nextToken()) - 1;
		sy = Integer.parseInt(st.nextToken()) - 1;
		ex = Integer.parseInt(st.nextToken()) - 1;
		ey = Integer.parseInt(st.nextToken()) - 1;

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
			}
		}

		System.out.println(solution());

	}

	public static int solution() {
		int cnt = 1;

		while (!dfs()) {
			cnt++;
		}

		return cnt;
	}

	public static boolean dfs() {
		Queue<Pos> queue = new LinkedList<>();
		queue.add(new Pos(sx, sy));

		boolean[][] visited = new boolean[N][M];
		visited[sx][sy] = true;

		while (!queue.isEmpty()) {
			Pos now = queue.poll();

			if (now.x == ex && now.y == ey) {
				return true;
			}

			for (int i = 0; i < 4; i++) {
				int nextx = now.x + dx[i];
				int nexty = now.y + dy[i];

				if (nextx >= 0 && nexty >= 0 && nextx < N && nexty < M) {
					if (map[nextx][nexty] == '1') {
						map[nextx][nexty] = '0';
						visited[nextx][nexty] = true;
					} else if (!visited[nextx][nexty]) {
						queue.add(new Pos(nextx, nexty));
						visited[nextx][nexty] = true;
					}
				}
			}
		}

		return false;
	}

}
