
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 미로 탐색
public class Main {

	static class Pos {
		int x, y, cnt;

		public Pos(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}

		// bfs
		Queue<Pos> queue = new ArrayDeque<>();
		queue.add(new Pos(0, 0, 1)); // 출발칸도 카운팅

		boolean[][] visited = new boolean[N][M];
		visited[0][0] = true;

		while (!queue.isEmpty()) {
			Pos now = queue.poll();

			if (now.x == N - 1 && now.y == M - 1) {
				System.out.println(now.cnt);
				break;
			}

			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];

				// 범위를 벗어났거나, 이미 방문한 곳은 이동하지 않는다.
				if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) {
					continue;
				}

				// 0은 이동할 수 없는 칸이다.
				if (map[nx][ny] == 0) {
					continue;
				}

				queue.add(new Pos(nx, ny, now.cnt + 1));
				visited[nx][ny] = true;
			}
		}
	}

}
