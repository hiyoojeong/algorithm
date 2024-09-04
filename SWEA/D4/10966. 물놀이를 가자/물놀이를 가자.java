
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

// 물놀이를 가자
public class Solution {

	static class Pos {
		int x, y, cnt;

		public Pos(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}

	static int N, M, dist[][];
	static char map[][];
	static List<Pos> water;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuffer answer = new StringBuffer();

		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt();
			M = sc.nextInt();
			map = new char[N][M];

			Queue<Pos> queue = new ArrayDeque<>();
			boolean[][] visited = new boolean[N][M];

			for (int i = 0; i < N; i++) {
				String input = sc.next();
				for (int j = 0; j < M; j++) {
					map[i][j] = input.charAt(j);
					if (map[i][j] == 'W') {
						queue.add(new Pos(i, j, 0));
						visited[i][j] = true;
					}
				}
			}

			// 각각의 물에서 땅인 칸으로 이동하기 위한 최소 횟수 구하기
			int res = 0;
			while (!queue.isEmpty()) {
				Pos now = queue.poll();
				res += now.cnt;

				for (int i = 0; i < 4; i++) {
					int nx = now.x + dx[i];
					int ny = now.y + dy[i];

					// 범위를 벗어났거나, 이미 방문했다면 이동하지 않는다.
					if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) {
						continue;
					}

					// 물이면 이동하지 않는다.
					if (map[nx][ny] == 'W') {
						continue;
					}

					queue.add(new Pos(nx, ny, now.cnt + 1));
					visited[nx][ny] = true;
				}
			}

			answer.append(String.format("#%d %d\n", test_case, res));
		}

		System.out.println(answer);
		sc.close();
	}
}
