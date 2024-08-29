import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

// 3124. 최소 스패닝 트리
public class Solution {

	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}

	}

	static int[] parents;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuffer answer = new StringBuffer();

		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			// 입력
			int V = sc.nextInt();
			int E = sc.nextInt();

			parents = new int[V + 1];
			for (int i = 1; i <= V; i++) {
				parents[i] = i;
			}

			Queue<Edge> pq = new PriorityQueue<>();
			for (int i = 0; i < E; i++) {
				int from = sc.nextInt();
				int to = sc.nextInt();
				int weight = sc.nextInt();
				pq.add(new Edge(from, to, weight));
			}

			int edge = 0;
			long weight = 0;
			while (!pq.isEmpty()) {
				Edge e = pq.poll();
				if (union(e.from, e.to)) {
					weight += e.weight;
					edge++;
				}
				if (edge == V - 1) {
					break;
				}
			}

			answer.append("#" + test_case + " " + weight + "\n");
		}

		System.out.println(answer);
		sc.close();
	}

	public static int find(int v) {
		if (parents[v] == v) {
			return v;
		}
		return parents[v] = find(parents[v]);
	}

	public static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);

		if (pa == pb) {
			return false;
		}

		parents[pa] = pb;
		return true;
	}
}
