import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 도시 분할 계획
public class Main {

	static class Edge {
		int u, v, cost;

		public Edge(int u, int v, int cost) {
			this.u = u;
			this.v = v;
			this.cost = cost;
		}
	}

	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 집의 개수
		int M = Integer.parseInt(st.nextToken()); // 길의 개수
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		Queue<Edge> edges = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			edges.add(new Edge(u, v, cost));
		}

		int edgeCount = 0, cost = 0;
		while (!edges.isEmpty()) {
			if (edgeCount == N - 2) { // 2개의 도시로 분할되어야 하므로, 유지비가 가장 큰 경로를 없앤다.
				break;
			}
			
			Edge e = edges.poll();
			if (union(e.u, e.v)) {
				edgeCount++;
				cost += e.cost;
			}
		}

		System.out.println(cost);
	}

	private static int find(int v) {
		if (v == parent[v]) {
			return v;
		}
		return parent[v] = find(parent[v]);
	}

	private static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if (pa == pb) {
			return false;
		}
		parent[pa] = pb;
		return true;
	}
}