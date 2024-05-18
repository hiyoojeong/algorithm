import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 백도어
public class Main {

	static class Node {
		int idx;
		long cost;

		public Node(int idx, long cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}

	static List<Node>[] graph;
	static long[] dist;
	static boolean[] possible;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 분기점의 수
		int M = Integer.parseInt(st.nextToken()); // 분기점들을 잇는 길의 수

		// 인접리스트, 최단거리 배열을 초기화한다.
		graph = new ArrayList[N];
		dist = new long[N];
		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
			dist[i] = Long.MAX_VALUE;
		}

		// 갈 수 있는 분기점과 갈 수 없는 분기점을 체크한다.
		possible = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N - 1; i++) {
			possible[i] = Integer.parseInt(st.nextToken()) == 0 ? true : false;
		}
		possible[N - 1] = true;

		// 갈 수 없는 분기점을 제외하고 인접 리스트에 경로를 저장한다.
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());

			if (possible[a] && possible[b]) {
				graph[a].add(new Node(b, t));
				graph[b].add(new Node(a, t));
			}
		}

		dijkstra(0);
		long answer = dist[N - 1] == Long.MAX_VALUE ? -1 : dist[N - 1];

		System.out.println(answer);

	}

	public static void dijkstra(int startNode) {
		dist[startNode] = 0;

		Queue<Node> queue = new PriorityQueue<Node>((o1, o2) -> (int) (o1.cost - o2.cost));
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
