
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 벽 부수고 이동하기
public class Main {

	static class Pos {
		int r, c, cnt;
		boolean isBreak;

		public Pos(int r, int c, int cnt, boolean isBreak) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.isBreak = isBreak;
		}
	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int N, M, map[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}

		System.out.println(bfs(0, 0, N - 1, M - 1));
	}

	public static int bfs(int sr, int sc, int er, int ec) {
		Queue<Pos> q = new ArrayDeque<>();
		q.add(new Pos(sr, sc, 1, false));

		// visited[N][M][0] : 벽을 부수지 않고 이동한 것
		// visited[N][M][1] : 벽을 부수고 이동한 것
		boolean[][][] visited = new boolean[N][M][2];
		visited[sr][sc][0] = true;

		while (!q.isEmpty()) {
			Pos now = q.poll();

			if (now.r == er && now.c == ec) {
				return now.cnt;
			}

			for (int i = 0; i < 4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];

				if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
					continue;
				}

				// 다음 이동칸에 벽이 없는 경우
				if (map[nr][nc] == 0) {
					// 벽을 부순적 없는 경우에서 방문 체크
					if (!now.isBreak && !visited[nr][nc][0]) {
						q.add(new Pos(nr, nc, now.cnt + 1, now.isBreak));
						visited[nr][nc][0] = true;
					}
					// 벽을 부순적 있는 경우에서 방문 체크
					else if (now.isBreak && !visited[nr][nc][1]) {
						q.add(new Pos(nr, nc, now.cnt + 1, now.isBreak));
						visited[nr][nc][1] = true;
					}
				}
				// 다음 이동칸에 벽이 있는데 벽을 부술 수 있는 경우
				else if (!now.isBreak) {
					q.add(new Pos(nr, nc, now.cnt + 1, true));
					visited[nr][nc][1] = true;
				}
			}
		}

		return -1;
	}
}
