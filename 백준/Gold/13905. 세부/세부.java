import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 세부
class Main {

	static class Edge {

		int edge, cost, parent;

		public Edge(int edge, int parent, int cost) {
			this.edge = edge;
			this.parent = parent;
			this.cost = cost;
		}

	}

	public static void main(String[] args) throws IOException, InterruptedException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 집의 수
		int M = Integer.parseInt(st.nextToken()); // 다리의 수

		st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken()); // 시작
		int e = Integer.parseInt(st.nextToken()); // 끝

		// 간선 저장
		List<Edge>[] adj = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			adj[to].add(new Edge(from, 0, cost));
			adj[from].add(new Edge(to, 0, cost));
		}

		// 루트 저장
		int[] parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		// prim
		Queue<Edge> pq = new PriorityQueue<>((o1, o2) -> o2.cost - o1.cost);
		boolean[] visited = new boolean[N + 1];

		visited[s] = true;
		for (Edge edge : adj[s]) {
			pq.add(new Edge(edge.edge, s, edge.cost));
		}

		while (!pq.isEmpty()) {
			Edge now = pq.poll();

			if (visited[now.edge]) {
				continue;
			}

			visited[now.edge] = true; // 현재 정점의 방문 체크
			parent[now.edge] = now.parent; // 현재 정점의 부모 저장 (pq에서 꺼내온 시점에 부모 저장해야 함)

			// 도착
			if (now.edge == e) {
				break;
			}

			// 인접 정점 방문
			for (Edge edge : adj[now.edge]) {
				pq.add(new Edge(edge.edge, now.edge, edge.cost));
			}
		}

		// 도착 정점의 부모를 따라가며 경로 비용 계산
		int idx = e;
		int min = Integer.MAX_VALUE; // 금빼빼로 최대 개수 = 지나가는 다리에서 최소 무게 제한
		while (parent[idx] != idx) {
			for (Edge edge : adj[idx]) {
				if (edge.edge == parent[idx]) {
					min = Math.min(edge.cost, min);
					idx = parent[idx];
					break;
				}
			}
		}

		System.out.println(min == Integer.MAX_VALUE ? 0 : min);
	}
}