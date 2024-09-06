
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 말이 되고픈 원숭이
public class Main {

	static class Pos {
		int r, c, cnt, kcnt;

		public Pos(int r, int c, int cnt, int kcnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.kcnt = kcnt;
		}
	}

	static int K, W, H;
	static int[][] map;

	static int[] dr = { 0, 0, -1, 1 };
	static int[] dc = { -1, 1, 0, 0 };
	static int[] hdr = { -2, -2, -1, -1, 1, 1, 2, 2 };
	static int[] hdc = { -1, 1, -2, 2, -2, 2, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		K = Integer.parseInt(br.readLine()); // 원숭이가 말처럼 이동할 수 있는 최대 수

		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken()); // 가로길이
		H = Integer.parseInt(st.nextToken()); // 세로길이

		map = new int[H][W];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(bfs(0, 0));
	}

	public static int bfs(int sr, int sc) {
		Queue<Pos> queue = new ArrayDeque<>();
		boolean[][][] visited = new boolean[K + 1][H][W];

		queue.add(new Pos(0, 0, 0, 0));
		visited[0][0][0] = true;

		while (!queue.isEmpty()) {
			Pos cur = queue.poll();

			if (cur.r == H - 1 && cur.c == W - 1) { // 도착점까지 갈 수 있는 경우, 원숭이의 최소 동작수 반환
				return cur.cnt;
			}

			// 말의 움직임
			if (cur.kcnt < K) {
				for (int i = 0; i < 8; i++) {
					int nr = cur.r + hdr[i];
					int nc = cur.c + hdc[i];
					int ncnt = cur.cnt + 1;
					int nkcnt = cur.kcnt + 1;

					// 범위를 벗어났거나, 동일한 조건으로 해당 지점에 방문한 적이 있는 경우
					if (nr < 0 || nc < 0 || nr >= H || nc >= W || visited[nkcnt][nr][nc]) {
						continue;
					}

					// 장애물이 있는 경우
					if (map[nr][nc] == 1) {
						continue;
					}

					queue.add(new Pos(nr, nc, ncnt, nkcnt));
					visited[nkcnt][nr][nc] = true;
				}
			}

			// 원숭이의 움직임
			for (int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				int ncnt = cur.cnt + 1;
				int nkcnt = cur.kcnt;

				// 범위를 벗어났거나, 동일한 조건으로 해당 지점에 방문한 적이 있는 경우
				if (nr < 0 || nc < 0 || nr >= H || nc >= W || visited[nkcnt][nr][nc]) {
					continue;
				}

				if (map[nr][nc] == 1) {
					continue;
				}

				// 장애물이 있는 경우
				queue.add(new Pos(nr, nc, ncnt, nkcnt));
				visited[nkcnt][nr][nc] = true;
			}

		}

		return -1; // 도착점까지 갈 수 없는 경우, -1 반환
	}

}
