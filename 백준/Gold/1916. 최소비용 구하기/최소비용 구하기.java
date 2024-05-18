import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 최소비용 구하기
public class Main {

	static class Node {
		int idx, cost;

		Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}

	static int N, M;
	static List<Node>[] graph;
	static int[] dist;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine()); // 도시 수
		M = Integer.parseInt(br.readLine()); // 버스 수

		// 인접 리스트, 최단거리 배열을 초기화한다.
		graph = new ArrayList[N + 1];
		dist = new int[N + 1];
		for (int i = 0; i < N + 1; i++) {
			graph[i] = new ArrayList<>();
			dist[i] = Integer.MAX_VALUE;
		}

		// 버스 정보를 저장한다.
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[u].add(new Node(v, w));
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		dijkstra(start);
		int answer = dist[end];

		System.out.println(answer);

	}

	public static void dijkstra(int startNode) {
		dist[startNode] = 0;

		Queue<Node> queue = new PriorityQueue<Node>((o1, o2) -> o1.cost - o2.cost);
		queue.add(new Node(startNode, 0));

		while (!queue.isEmpty()) {
			Node now = queue.poll();

			// now.cost: 지금 확인하려는 현재 위치까지 오는 경로의 거리
			// dist[now.idx]: 기존에 알고 있는 현재 위치까지 오는 경로의 거리
			// '지금 확인하려는 현재 위치까지 오는 경로의 거리'가 '기존에 알고 있는 현재 위치까지 오는 경로의 거리'보다 크다면, 확인할 필요없다.
			if (now.cost > dist[now.idx]) {
				continue;
			}

			for (Node next : graph[now.idx]) {
				if (dist[now.idx] + next.cost < dist[next.idx]) {
					dist[next.idx] = dist[now.idx] + next.cost;
					queue.add(new Node(next.idx, dist[next.idx]));
				}
			}
		}
	}

}
