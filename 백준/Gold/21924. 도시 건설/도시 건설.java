import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

// 도시 건설
class Main {

	static class Conn implements Comparable<Conn> {
		int to, from, cost;

		public Conn(int to, int from, int cost) {
			this.to = to;
			this.from = from;
			this.cost = cost;
		}

		@Override
		public int compareTo(Conn o) {
			return this.cost - o.cost;
		}

	}

	static Queue<Conn> conns;
	static int[] parent;

	public static void main(String[] args) throws IOException, InterruptedException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		conns = new PriorityQueue<>();
		long totalCost = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			conns.add(new Conn(to, from, cost));
			totalCost += cost;
		}

		// 루트 저장 (초기에는 자기 자신)
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		// kruskal
		long cost = 0;
		int edgeCount = 0;
		while (!conns.isEmpty()) {
			Conn conn = conns.poll();
			if (union(conn.to, conn.from)) {
				cost += conn.cost;
				edgeCount++;
			}
		}

		long answer = -1;
		if (edgeCount == N - 1) {
			answer = totalCost - cost;
		}
		System.out.println(answer);
	}

	public static boolean union(int v1, int v2) {
		int parent1 = find(v1);
		int parent2 = find(v2);

		if (parent1 == parent2) { // 사이클
			return false;
		}

		if (parent1 > parent2) {
			parent[parent1] = parent2;
		} else {
			parent[parent2] = parent1;
		}
		return true;
	}

	public static int find(int v) {
		if (parent[v] == v) { // 루트 발견
			return v;
		}
		return parent[v] = find(parent[v]); // 현재 정점의 루트 갱신하며 반환
	}
}