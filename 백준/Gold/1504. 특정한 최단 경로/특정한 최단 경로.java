import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 특정한 최단경로
public class Main {

	static class Node {
		int idx, cost;

		public Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}

	}

	static List<Node>[] gragh;
	static int[] dist;

	static int BIG_VALUE = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		// 인접 그래프와 최단거리 배열을 초기화한다.
		gragh = new ArrayList[V + 1];
		dist = new int[V + 1];
		for (int i = 1; i < V + 1; i++) {
			gragh[i] = new ArrayList<>();
		}

		// 인접 그래프의 간선 정보를 저장한다.
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			gragh[a].add(new Node(b, c));
			gragh[b].add(new Node(a, c));
		}

		// 반드시 거쳐야 하는 두 개의 정점을 저장한다.
		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());

		// 1) v1을 시작점으로 하는 최단경로 구하기
		for (int i = 1; i < V + 1; i++) {
			dist[i] = BIG_VALUE;
		}
		dijkstra(v1);
		int startToV1 = dist[1]; // 1 -> v1
		int v1ToV2 = dist[v2]; // v1 -> v2
		int v1ToN = dist[V]; // v1 -> n

		// 2) v2를 시작점으로 하는 최단경로 구하기
		for (int i = 1; i < V + 1; i++) {
			dist[i] = BIG_VALUE;
		}
		dijkstra(v2);
		int startToV2 = dist[1]; // 1 -> v2
		int v2ToV1 = dist[v1]; // v2 -> v1
		int v2ToN = dist[V]; // v2 -> n

		int v1Cost = BIG_VALUE, v2Cost = BIG_VALUE;
		if (startToV1 != BIG_VALUE && v1ToV2 != BIG_VALUE && v2ToN != BIG_VALUE) {
			// 1 -> v1 -> v2 -> n
			v1Cost = startToV1 + v1ToV2 + v2ToN;
		}
		if (startToV2 != BIG_VALUE && v2ToV1 != BIG_VALUE && v1ToN != BIG_VALUE) {
			// 1 -> v2 -> v1 -> n
			v2Cost = startToV2 + v2ToV1 + v1ToN;
		}

		int answer = Math.min(v1Cost, v2Cost);
		System.out.println(answer == BIG_VALUE ? "-1" : answer);

	}

	public static void dijkstra(int startNode) {
		dist[startNode] = 0;

		Queue<Node> queue = new PriorityQueue<Node>((o1, o2) -> o1.cost - o2.cost);
		queue.add(new Node(startNode, dist[startNode]));

		while (!queue.isEmpty()) {
			Node now = queue.poll();
			int nowNode = now.idx;

			for (Node next : gragh[nowNode]) {
				int nextNode = next.idx;

				if (dist[nowNode] + next.cost < dist[nextNode]) {
					dist[nextNode] = dist[nowNode] + next.cost;
					queue.add(new Node(nextNode, dist[nextNode]));
				}
			}
		}
	}

}
