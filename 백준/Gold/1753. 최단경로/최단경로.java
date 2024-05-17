import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 최단경로
public class Main {

	static class Node {
		int idx, cost;

		public Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}

	static List<Node>[] graph; // 그래프를 '인접 리스트'로 구현
	static int[] dist; // 최단거리

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int startNode = Integer.parseInt(br.readLine());

		// 인접 리스트와 최단거리 정보를 초기화한다.
		graph = new ArrayList[V + 1];
		dist = new int[V + 1];
		for (int i = 1; i < V + 1; i++) {
			graph[i] = new ArrayList<>();
			dist[i] = Integer.MAX_VALUE;
		}

		// 간선 정보를 인접 리스트에 저장한다.
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()); // 출발 노드
			int v = Integer.parseInt(st.nextToken()); // 도착 노드
			int w = Integer.parseInt(st.nextToken()); // 거리
			graph[u].add(new Node(v, w));
		}

		dist[startNode] = 0;
		dijkstra(startNode);

		for (int i = 1; i < V + 1; i++) {
			System.out.println(dist[i] != Integer.MAX_VALUE ? dist[i] : "INF");
		}
	}

	public static void dijkstra(int startNode) {
		Queue<Node> queue = new PriorityQueue<Node>((o1, o2) -> o1.cost - o2.cost);
		queue.add(new Node(startNode, dist[startNode]));

		while (!queue.isEmpty()) {
			Node now = queue.poll();

			for (Node next : graph[now.idx]) {
				int nextNode = next.idx;
				if (now.cost + next.cost < dist[nextNode]) {
					dist[nextNode] = now.cost + next.cost;
					queue.add(new Node(nextNode, dist[nextNode]));
				}
			}
		}
	}

}
