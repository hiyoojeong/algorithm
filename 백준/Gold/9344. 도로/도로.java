
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 도로
public class Main {

	static class Edge {
		int to, from, cost;

		public Edge(int to, int from, int cost) {
			this.to = to;
			this.from = from;
			this.cost = cost;
		}
	}

	static int[] parent;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer answer = new StringBuffer();

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 도시의 수
			int M = Integer.parseInt(st.nextToken()); // 길의 수
			int p = Integer.parseInt(st.nextToken()); // 도로를 지어도 되는지 판단하는 두 도시
			int q = Integer.parseInt(st.nextToken());

			parent = new int[N + 1];
			List<Edge>[] adj = new ArrayList[N + 1];
			for (int i = 1; i <= N; i++) {
				parent[i] = i;
				adj[i] = new ArrayList<>();
			}

			Queue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int to = Integer.parseInt(st.nextToken());
				int from = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				pq.add(new Edge(to, from, cost));
			}

			// kruskal
			int edgeCount = 0;
			boolean isContain = false;
			while (!pq.isEmpty()) {
				Edge now = pq.poll();

				if (union(now.to, now.from)) {
					// 가장 짧은 도로망을 만들었을 때, p-q를 잇는 도로를 지었다
					if ((now.to == p && now.from == q) || (now.to == q && now.from == p)) {
						isContain = true;
					}
					edgeCount++;
				}

				if (edgeCount == N - 1) {
					break;
				}
			}

			if (isContain) {
				answer.append("YES\n");
			} else {
				answer.append("NO\n");
			}
		}
		System.out.println(answer);
	}

	static int find(int v) {
		if (parent[v] == v) {
			return v;
		}
		return parent[v] = find(parent[v]);
	}

	static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);

		if (pa == pb) {
			return false;
		}

		parent[pa] = pb;
		return true;
	}

}
