import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 파티
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

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 학생 수
		int M = Integer.parseInt(st.nextToken()); // 도로 (단방향)
		int X = Integer.parseInt(st.nextToken()); // 모이는 마을

		graph = new ArrayList[N + 1];
		dist = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			graph[i] = new ArrayList<>();
			dist[i] = Integer.MAX_VALUE;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			graph[start].add(new Node(end, t));
		}

		int[] totalDist = new int[N + 1];

		// N번 마을 -> X번 마을
		for (int i = 1; i < N + 1; i++) {
			dijkstra(i);
			totalDist[i] += dist[X];

			for (int j = 1; j < N + 1; j++) {
				dist[j] = Integer.MAX_VALUE;
			}
		}

		// X번 마을 -> N번 마을
		dijkstra(X);
		for (int i = 1; i < N + 1; i++) {
			totalDist[i] += dist[i];
		}

		int answer = 0;
		for (int i = 1; i < N + 1; i++) {
			answer = Math.max(answer, totalDist[i]);
		}

		System.out.println(answer);

	}

	public static void dijkstra(int startNode) {
		dist[startNode] = 0;

		Queue<Node> queue = new PriorityQueue<Node>((o1, o2) -> o1.cost - o2.cost);
		queue.add(new Node(startNode, 0));

		while (!queue.isEmpty()) {
			Node now = queue.poll();
			int nowNode = now.idx;

			if (now.cost > dist[nowNode]) {
				continue;
			}

			for (Node next : graph[nowNode]) {
				int nextNode = next.idx;

				if (dist[nowNode] + next.cost < dist[nextNode]) {
					dist[nextNode] = dist[nowNode] + next.cost;
					queue.add(new Node(nextNode, dist[nextNode]));
				}
			}
		}
	}

}
