
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 안전 영역
class Main {

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static int N, map[][];
	static Queue<Pos> queue;
	static boolean[][] visited;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		int maxHeight = 0, minHeight = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				minHeight = Math.min(minHeight, map[i][j]);
				maxHeight = Math.max(maxHeight, map[i][j]);
			}
		}

		int maxCount = 0;
		for (int height = minHeight - 1; height <= maxHeight; height++) {
			visited = new boolean[N][N];
			queue = new LinkedList<>();

			int count = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] > height && !visited[i][j]) { // 물에 잠기지 않는 안전한 영역
						count++;
						bfs(i, j, height);
					}
				}
			}

			maxCount = Math.max(count, maxCount);
		}

		System.out.println(maxCount);
	}

	public static void bfs(int x, int y, int height) {
		queue.add(new Pos(x, y));
		visited[x][y] = true;

		while (!queue.isEmpty()) {
			Pos now = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny] || map[nx][ny] <= height) {
					continue;
				}

				queue.add(new Pos(nx, ny));
				visited[nx][ny] = true;
			}
		}
	}
}