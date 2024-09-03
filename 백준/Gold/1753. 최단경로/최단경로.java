
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 최단경로
public class Main {

	static class Node {
		int v, w;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}

	static int V, E;
	static List<Node>[] adjList;
	static int[] minDistance;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer answer = new StringBuffer();

		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken()); // 정점 수
		E = Integer.parseInt(st.nextToken()); // 간선 수

		int K = Integer.parseInt(br.readLine()); // 시작 정점

		// 인접리스트 초기화
		adjList = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adjList[u].add(new Node(v, w));
		}

		// 최소비용 초기화
		minDistance = new int[V + 1];
		Arrays.fill(minDistance, Integer.MAX_VALUE);

		// 시작정점에서 다른정점까지 최소비용 구하기
		minDistance[K] = 0;
		dijkstra(K);

		for (int i = 1; i <= V; i++) {
			answer.append((minDistance[i] == Integer.MAX_VALUE ? "INF" : minDistance[i]) + "\n");
		}
		System.out.println(answer);
	}

	public static void dijkstra(int start) {
		Queue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
		pq.add(new Node(start, 0));

		boolean[] visited = new boolean[V + 1];

		while (!pq.isEmpty()) {
			Node now = pq.poll();

			if (visited[now.v]) {
				continue;
			}
			visited[now.v] = true;

			for (Node next : adjList[now.v]) {
				if (visited[next.v]) {
					continue;
				}
				if (minDistance[next.v] > minDistance[now.v] + next.w) {
					minDistance[next.v] = minDistance[now.v] + next.w;
					pq.add(new Node(next.v, minDistance[next.v]));
				}
			}
		}
	}

}
