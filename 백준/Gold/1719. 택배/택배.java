
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 택배
public class Main {

	static class Node {
		int idx, cost;

		public Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}

	static List<Node>[] graph;
	static int n, path[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer answer = new StringBuffer();

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 집하장의 개수
		int m = Integer.parseInt(st.nextToken()); // 집하장 간 경로의 개수

		graph = new ArrayList[n + 1]; // 간선
		path = new int[n + 1][n + 1]; // 최단경로로 화물을 이동시키기 위해 가장 먼저 거쳐야 하는 집하장
		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}

		// 간선 저장
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int u = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph[v].add(new Node(u, c));
			graph[u].add(new Node(v, c));
			path[v][u] = u;
			path[u][v] = v;
		}

		// i를 시작정점으로 하는 최단 경로를 구하며, 가장 먼저 거쳐야 하는 집하장 업데이트
		for (int i = 1; i <= n; i++) {
			dijkstra(i);
		}

		// 결과 출력
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i == j) {
					answer.append("- ");
				} else {
					answer.append(path[i][j] + " ");
				}
			}
			answer.append("\n");
		}

		System.out.println(answer);
	}

	public static void dijkstra(int start) {
		Queue<Node> q = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);

		int[] dist = new int[n + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);

		// 시작지점과 인접한지점부터 최단거리를 업데이트
		// 시작지점부터 시작하면 안 되는 이유 : 가장 먼저 거쳐야 하는 집하장이 시작지점으로 저장됨
		for (Node node : graph[start]) {
			q.add(node);
			dist[node.idx] = node.cost;
		}

		while (!q.isEmpty()) {
			Node now = q.poll();

			for (Node next : graph[now.idx]) {
				if (dist[next.idx] > dist[now.idx] + next.cost) {
					dist[next.idx] = dist[now.idx] + next.cost;
					q.add(new Node(next.idx, dist[next.idx]));
					// 'start -> next'까지 이동하는데 'start -> now -> next'로 이동하는 게 비용이 적을 경우
					// 'next로 이동하는 경로의 시작점'에 'now로 이동하는 경로의 시작점'을 저장
					path[start][next.idx] = path[start][now.idx];
				}
			}
		}
	}

}
