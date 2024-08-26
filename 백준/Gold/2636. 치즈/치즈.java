import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 치즈
public class Main {

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static final int OUT = 0, CHEESE = -1;

	static int N, M;
	static int[][] map;

	static Queue<Pos> queue;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];
		int cheese = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					map[i][j] = CHEESE;
					cheese++;
				}
			}
		}

		// 치즈 녹이기
		int time = 0, remainingCheese = cheese, removeCheese = 0;
		while (true) {
			time++;

			// 바깥 영역에 노출된 치즈 체크
			queue = new ArrayDeque<>();
			queue.add(new Pos(0, 0));

			visited = new boolean[N][M];
			visited[0][0] = true;

			while (!queue.isEmpty()) {
				Pos now = queue.poll();

				for (int i = 0; i < 4; i++) {
					int nx = now.x + dx[i];
					int ny = now.y + dy[i];

					if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) {
						continue;
					}

					visited[nx][ny] = true; // 방문 체크

					if (map[nx][ny] == CHEESE) { // 바깥 영역에 노출된 치즈 체크
						map[nx][ny] = time;
					}
					if (map[nx][ny] == OUT) { // 인접한 바깥 영역으로 이동
						queue.add(new Pos(nx, ny));
					}
				}
			}

			// 바깥 영역에 노출된 치즈 녹임
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == time) {
						map[i][j] = OUT;
						removeCheese++;
					}
				}
			}

			if (cheese == removeCheese) {
				break;
			}

			remainingCheese = cheese - removeCheese;
		}

		System.out.println(time);
		System.out.println(remainingCheese);

	}

}
