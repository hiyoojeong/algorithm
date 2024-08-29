
// 하나로
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

	static class Edge implements Comparable<Edge> {
		int from, to;
		double length;

		public Edge(int from, int to, double length) {
			this.from = from;
			this.to = to;
			this.length = length;
		}

		@Override
		public int compareTo(Edge o) {
			if (this.length > o.length) {
				return 1;
			} else if (this.length < o.length) {
				return -1;
			}
			return 0;
		}
	}

	static int[] parents;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuffer answer = new StringBuffer();

		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			// 입력
			int N = sc.nextInt(); // 섬의 개수

			int[] x = new int[N + 1]; // 섬의 좌표
			int[] y = new int[N + 1]; // 섬의 좌표
			for (int i = 1; i <= N; i++) {
				x[i] = sc.nextInt();
			}
			for (int i = 1; i <= N; i++) {
				y[i] = sc.nextInt();
			}

			double E = sc.nextDouble(); // 환경 부담 세율

			// 간선 구하기
			Queue<Edge> pq = new PriorityQueue<>();
			for (int i = 1; i <= N - 1; i++) {
				for (int j = i + 1; j <= N; j++) {
					int from = i;
					int to = j;
					double length = Math.sqrt(Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2));
					pq.add(new Edge(from, to, length));
				}
			}

			// 집합 초기화
			parents = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				parents[i] = i;
			}

			// Kruskal
			int edge = 0;
			double cost = 0;
			while (!pq.isEmpty()) {
				Edge e = pq.poll();
				if (union(e.from, e.to)) {
					edge++;
					cost += (e.length * e.length * E);
				}
				if (edge == N - 1) {
					break;
				}
			}

			answer.append("#" + test_case + " " + Math.round((cost * 10) / 10) + "\n");
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
