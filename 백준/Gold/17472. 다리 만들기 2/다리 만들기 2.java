import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 다리 만들기
class Main {
	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static class Edge implements Comparable<Edge> {
		int v, w;

		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}

	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 입력
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 섬 정보 저장
		List<List<Pos>> islands = new ArrayList<>();
		Queue<Pos> queue = new ArrayDeque<>();
		int islandNo = 1;
		int[][] islandNos = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1 && islandNos[i][j] == 0) {
					List<Pos> island = new ArrayList<>();

					island.add(new Pos(i, j));
					queue.add(new Pos(i, j));
					islandNos[i][j] = islandNo;

					while (!queue.isEmpty()) {
						Pos pos = queue.poll();

						for (int k = 0; k < 4; k++) {
							int nx = pos.x + dx[k];
							int ny = pos.y + dy[k];

							if (nx < 0 || ny < 0 || nx >= N || ny >= M || islandNos[nx][ny] != 0 || map[nx][ny] == 0) {
								continue;
							}

							// 테두리 부분만

							island.add(new Pos(nx, ny));
							queue.add(new Pos(nx, ny));
							islandNos[nx][ny] = islandNo;
						}
					}

					islands.add(island);
					islandNo++;
				}
			}
		}

		// 간선 저장
		int size = islands.size();
		List<Edge>[] edges = new ArrayList[size + 1]; // 섬 번호는 1부터 시작
		for (int i = 1; i <= size; i++) {
			edges[i] = new ArrayList<>();
		}

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < islands.get(i).size(); j++) {

				Pos pos = islands.get(i).get(j); // i번째 섬의 j번째 포인트

				for (int k = 0; k < 4; k++) { // 해당 포인트에서 상하좌우 인접 섬 찾기
					int cnt = 0, adjIslandNo = 0;
					int x = pos.x;
					int y = pos.y;

					while (true) {
						int nx = x + dx[k];
						int ny = y + dy[k];

						if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
							break;
						}

						if (islandNos[nx][ny] != 0) {
							adjIslandNo = islandNos[nx][ny];
							break;
						}

						cnt++;
						x = nx;
						y = ny;
					}

					if (adjIslandNo != 0 && cnt >= 2) { // 인접 섬이 있고, 거리가 2이상이면 다리 건설 가능
						edges[i + 1].add(new Edge(adjIslandNo, cnt));
					}
				}
			}
		}

		// Prim
		// 섬들 간 중복 간선이 많아서, 방문체크하는 prim을 사용해야 한다.
		Queue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
		boolean[] visited = new boolean[size + 1];

		visited[1] = true;
		for (Edge e : edges[1]) {
			pq.add(e);
		}

		int minWeight = 0, edgeCnt = 0;
		while (!pq.isEmpty()) {
			Edge now = pq.poll();

			if (visited[now.v]) {
				continue;
			}

			visited[now.v] = true;
			minWeight += now.w;
			edgeCnt++;

			for (Edge e : edges[now.v]) {
				if (visited[e.v]) {
					continue;
				}
				pq.add(e);
			}
		}

		// 출력
		System.out.println(edgeCnt == size - 1 ? minWeight : -1);
	}

}
