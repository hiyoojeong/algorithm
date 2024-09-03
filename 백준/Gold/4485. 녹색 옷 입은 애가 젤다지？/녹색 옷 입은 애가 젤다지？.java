
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 녹색 옷 입은 애가 젤다지?
public class Main {

	static class Node {
		int r, c, cost;

		public Node(int r, int c, int cost) {
			this.r = r;
			this.c = c;
			this.cost = cost;
		}
	}

	static int N;
	static int[][] map;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer answer = new StringBuffer();

		int tc = 1;
		while ((N = Integer.parseInt(br.readLine())) != 0) {
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			answer.append("Problem " + tc + ": " + dijkstra(new Node(0, 0, 0)) + "\n");
			tc++;
		}

		System.out.println(answer);
	}

	private static int dijkstra(Node start) {
		int[][] minDistance = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(minDistance[i], Integer.MAX_VALUE);
		}
		minDistance[start.r][start.c] = map[start.r][start.c];

		Queue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
		pq.add(new Node(start.r, start.c, minDistance[start.r][start.c]));

		boolean[][] visited = new boolean[N][N];

		while (!pq.isEmpty()) {
			Node now = pq.poll();

			if (now.r == N - 1 && now.c == N - 1) {
				return now.cost;
			}

			if (visited[now.r][now.c]) {
				continue;
			}
			visited[now.r][now.c] = true;

			for (int i = 0; i < 4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];

				if (nr < 0 || nc < 0 || nr >= N || nc >= N || visited[nr][nc]) {
					continue;
				}

				if (minDistance[nr][nc] > minDistance[now.r][now.c] + map[nr][nc]) {
					minDistance[nr][nc] = minDistance[now.r][now.c] + map[nr][nc];
					pq.add(new Node(nr, nc, minDistance[nr][nc]));
				}
			}
		}

		return -1;
	}

}
