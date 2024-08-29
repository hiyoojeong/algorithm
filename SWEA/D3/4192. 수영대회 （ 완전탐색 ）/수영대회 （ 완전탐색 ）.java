
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

// 4192. 수영대회
public class Solution {

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

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuffer answer = new StringBuffer();

		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			// 입력
			int N = sc.nextInt();
			int[][] map = new int[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			Pos start = new Pos(sc.nextInt(), sc.nextInt(), 0);
			Pos end = new Pos(sc.nextInt(), sc.nextInt(), 0);

			// bfs
			Queue<Pos> queue = new ArrayDeque<>();
			queue.add(start);

			boolean[][] visited = new boolean[N][N];
			visited[start.x][start.y] = true;

			int time = -1;
			while (!queue.isEmpty()) {
				Pos now = queue.poll();

				if (now.x == end.x && now.y == end.y) {
					time = now.cnt;
					break;
				}

				for (int i = 0; i < 4; i++) {
					int nx = now.x + dx[i];
					int ny = now.y + dy[i];
					int ncnt = now.cnt + 1;

					// 범위를 벗어났거나, 방문했거나, 장애물이 있는 경우
					if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny] || map[nx][ny] == 1) {
						continue;
					}

					queue.add(new Pos(nx, ny, ncnt));
					visited[nx][ny] = true;
				}
			}

			answer.append("#" + test_case + " " + time + "\n");
		}

		System.out.println(answer);
		sc.close();
	}

}
