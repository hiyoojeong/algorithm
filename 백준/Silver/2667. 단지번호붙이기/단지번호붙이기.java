
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 2667. 단지번호붙이기
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
	static int[][] map;

	static Queue<Pos> q;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer answer = new StringBuffer();

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}

		q = new LinkedList<>();
		visited = new boolean[N][N];

		int cnt = 0;
		Queue<Integer> res = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					cnt++;
					res.add(bfs(i, j));
				}
			}
		}

		answer.append(cnt).append("\n");
		while (!res.isEmpty()) {
			answer.append(res.poll()).append("\n");
		}
		System.out.println(answer);
	}

	public static int bfs(int sx, int sy) {
		q.add(new Pos(sx, sy));
		visited[sx][sy] = true;

		int cnt = 0;

		while (!q.isEmpty()) {
			Pos now = q.poll();
			cnt++;

			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny] || map[nx][ny] == 0) {
					continue;
				}

				q.add(new Pos(nx, ny));
				visited[nx][ny] = true;
			}
		}

		return cnt;
	}
}