import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 수영대회 결승전 (완전 탐색 + 구현)
public class Solution {

	static class Node implements Comparable<Node> {
		int x, y, time;

		public Node(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}

		@Override
		public int compareTo(Node o1) {
			if (this.time == o1.time) {
				return -1;
			}
			return this.time - o1.time; // 오름차순 정렬
		}
	}

	static int N;
	static int[][] map;

	static Node start;
	static Node end;

	static int duration = 2; // 2초동안 소용돌이가 존재하다가, 1초동안 소용돌이가 사라진다.

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer sb = new StringBuffer();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			st = new StringTokenizer(br.readLine());
			int sx = Integer.parseInt(st.nextToken());
			int sy = Integer.parseInt(st.nextToken());
			start = new Node(sx, sy, 0);

			st = new StringTokenizer(br.readLine());
			int ex = Integer.parseInt(st.nextToken());
			int ey = Integer.parseInt(st.nextToken());
			end = new Node(ex, ey, 0);

			int answer = bfs();
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}

		System.out.println(sb);
	}

	public static int bfs() {

		Queue<Node> queue = new PriorityQueue<>();
		queue.add(new Node(start.x, start.y, 0));

		int[][] visited = new int[N][N];
		visited[start.x][start.y] = 1;

		int count = 0;
		boolean check = false;
		while (!queue.isEmpty()) {
			Node now = queue.poll();

			// 도착 위치에 도달했으므로 탈출한다.
			if (now.x == end.x && now.y == end.y) {
				count = now.time;
				check = true;
				break;
			}

			for (int d = 0; d < 4; d++) {
				int nx = now.x + dx[d];
				int ny = now.y + dy[d];
				if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
					// 방문한 적 없어야 한다.
					if (visited[nx][ny] == 0) {
						// 지나갈 수 있는 곳이므로, 지나간다.
						if (map[nx][ny] == 0) {
							visited[nx][ny] = 1;
							queue.add(new Node(nx, ny, now.time + 1));
						}
						// 소용돌이가 있으므로, 지나갈 수 있는 시간까지 기다렸다가 지나간다.
						if (map[nx][ny] == 2) {
							int ntime = now.time + (duration - (now.time % (duration + 1)));

							visited[nx][ny] = 1;
							queue.add(new Node(nx, ny, ntime + 1));
						}
					}
				}
			}
		}

		if (!check) {
			count = -1;
		}

		return count;
	}

}
