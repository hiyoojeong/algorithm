
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 토마토
class Main {

	static class Pos {
		int x, y, time;

		public Pos(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		Queue<Pos> queue = new ArrayDeque<>();

		// 입력
		int[][] map = new int[N][M];
		int cnt = 0; // cnt가 N*M이라면 모든 토마토가 익은 것
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1) {
					cnt++; // 토마토가 없는 칸 카운팅
				}
				if (map[i][j] == 1) {
					cnt++; // 익은 토마토 카운팅
					queue.add(new Pos(i, j, 0));
				}
			}
		}

		// bfs
		int time = 0;
		while (!queue.isEmpty()) {
			Pos now = queue.poll();
			time = Math.max(time, now.time);

			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] != 0) {
					continue;
				}

				cnt++; // 인접해서 익은 토마토 카운팅

				map[nx][ny] = 1;
				queue.add(new Pos(nx, ny, now.time + 1));
			}
		}

		if (cnt == N * M) {
			System.out.println(time);
		} else {
			System.out.println(-1);
		}
	}
}