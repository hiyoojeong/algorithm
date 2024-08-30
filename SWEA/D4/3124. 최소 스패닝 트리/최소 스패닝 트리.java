
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

// 3124. 최소 스패닝 트리
public class Solution {

	static class Edge {
		int to, weight;

		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuffer answer = new StringBuffer();

		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			// 입력
			int V = sc.nextInt();
			int E = sc.nextInt();

			List<Edge>[] edges = new ArrayList[V + 1];
			for (int i = 1; i <= V; i++) {
				edges[i] = new ArrayList<>();
			}

			for (int i = 0; i < E; i++) {
				int from = sc.nextInt();
				int to = sc.nextInt();
				int weight = sc.nextInt();
				edges[from].add(new Edge(to, weight));
				edges[to].add(new Edge(from, weight));
			}

			// prim
			Queue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
			boolean[] visited = new boolean[V + 1];

			visited[1] = true;
			for (Edge e : edges[1]) {
				pq.add(e);
			}

			long minWeight = 0;
			while (!pq.isEmpty()) {
				Edge now = pq.poll();

				if (visited[now.to]) {
					continue;
				}

				visited[now.to] = true;
				minWeight += now.weight;

				for (Edge e : edges[now.to]) {
					if (visited[e.to]) {
						continue;
					}
					pq.add(e);
				}
			}

			answer.append("#" + test_case + " " + minWeight + "\n");
		}

		System.out.println(answer);
		sc.close();
	}

}
