import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 지름길
public class Main {

	static class Node {
		int idx, cost;

		public Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}

	static List<Node>[] graph;
	static int[] dist;

	static int N, D;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		// 인접리스트, 최단거리 배열을 초기화한다.
		graph = new ArrayList[10001];
		dist = new int[10001];
		for (int i = 0; i < 10001; i++) {
			graph[i] = new ArrayList<>();
			dist[i] = i; // 처음에는 i번째 위치까지의 거리가 i이다.
		}

		// 지름길 정보를 저장한다.
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph[start].add(new Node(end, cost));
		}

		// 중간 중간에 지름길이 있기 때문에, 어느 위치를 경유해서 가느냐에 따라 최종 위치까지 가는 거리가 달라질 수 있다.
		// 어떤 위치까지 가는 최단 거리와, 그 위치에서부터 다른 위치까지 가는 최단 거리를 계속 업데이트해줘야 한다.
		for (int i = 0; i < D; i++) {
			dijkstra(i);
		}

		int answer = dist[D];

		System.out.println(answer);

	}

	public static void dijkstra(int startNode) {
		// dist[startNode + 1]: 기존 알고 있던 다음 위치까지 가는 거리
		// dist[startNode] + 1: 현재 위치에서 다음 위치까지 가는 거리
		// '기존 알고 있던 다음 위치까지 가는 거리'가 '현재 위치에서 다음 위치까지 가는 거리'보다 짧으면, 업데이트한다.
		if (dist[startNode + 1] > dist[startNode] + 1) {
			dist[startNode + 1] = dist[startNode] + 1;
		}

		// 현재 위치에서 다른 위치로 갈 수 있는 지름길을 확인한다.
		// 지름길이 더 짧으면, 업데이트한다.
		for (Node next : graph[startNode]) {
			int nextNode = next.idx;

			if (dist[startNode] + next.cost < dist[nextNode]) {
				dist[nextNode] = dist[startNode] + next.cost;
			}
		}
	}

}
