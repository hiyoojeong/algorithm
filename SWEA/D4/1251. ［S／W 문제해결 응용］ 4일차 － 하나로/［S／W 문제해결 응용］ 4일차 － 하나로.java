
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

// 하나로
public class Solution {

	static class Edge {
		int to;
		double weight;

		public Edge(int to, double weight) {
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
			int N = sc.nextInt(); // 섬의 개수

			int[] x = new int[N]; // 섬의 x좌표
			int[] y = new int[N]; // 섬의 y좌표
			for (int i = 0; i < N; i++) {
				x[i] = sc.nextInt();
			}
			for (int i = 0; i < N; i++) {
				y[i] = sc.nextInt();
			}

			double E = sc.nextDouble(); // 환경 부담 세율

			// 간선 추가
			List<Edge>[] edges = new ArrayList[N];
			for (int i = 0; i < N; i++) {
				edges[i] = new ArrayList<>();
			}
			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
//					double L = Math.sqrt(Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2));
//					L = E * L * L;
					double L = Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2);
					edges[i].add(new Edge(j, L));
					edges[j].add(new Edge(i, L));
				}
			}

			// prim
			Queue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {

				@Override
				public int compare(Edge o1, Edge o2) {
					if (o1.weight > o2.weight) {
						return 1;
					} else if (o1.weight < o2.weight) {
						return -1;
					}
					return 0;
				}
			});
			boolean[] visited = new boolean[N];

			visited[0] = true;
			for (Edge e : edges[0]) {
				pq.add(e);
			}

			double minWeight = 0;
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

			long result = Math.round(minWeight * E);
//			answer.append("#" + test_case + " " + (long) Math.floor(minWeight * 10) / 10 + "\n");
			
			System.out.printf("#%d %d\n", test_case, result);
		}

		sc.close();
	}
}