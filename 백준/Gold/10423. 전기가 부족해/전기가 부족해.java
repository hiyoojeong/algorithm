
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 전기가 부족해
class Main {
	static class Edge {
		int v, weight;

		public Edge(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 도시의 개수
		int M = Integer.parseInt(st.nextToken()); // 설치 가능한 케이블의 수
		int K = Integer.parseInt(st.nextToken()); // 발전소의 개수

		// 발전소가 설치된 도시의 번호
		int[] station = new int[K];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			station[i] = Integer.parseInt(st.nextToken());
		}

		// 두 도시를 연결하는 케이블의 정보
		List<Edge>[] cables = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			cables[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken()); // 비용
			cables[u].add(new Edge(v, w));
			cables[v].add(new Edge(u, w));
		}

		// Prim
		Queue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
		boolean[] visited = new boolean[N + 1];

		for (int i = 0; i < K; i++) { // 발전소가 설치된 도시와 연결된 케이블을 저장
			int city = station[i];

			visited[city] = true;
			for (Edge e : cables[city]) {
				pq.add(e);
			}
		}

		int minWeight = 0;
		while (!pq.isEmpty()) {
			Edge now = pq.poll();

			if (visited[now.v]) {
				continue;
			}

			visited[now.v] = true; // 정점 방문
			minWeight += now.weight; // 최소비용 업데이트

			for (Edge e : cables[now.v]) { // 인접정점 큐에 삽입
				if (visited[e.v]) {
					continue;
				}
				pq.add(e);
			}
		}

		System.out.println(minWeight);
	}
}