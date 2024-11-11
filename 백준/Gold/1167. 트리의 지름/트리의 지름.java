import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 트리의 지름
public class Main {

	static class Node {

		int v, dist;

		public Node(int v, int dist) {
			this.v = v;
			this.dist = dist;
		}
	}

	static List<Node>[] adj;
	static boolean[] visited;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		adj = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		int[] in = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			while (true) {
				int to = Integer.parseInt(st.nextToken());
				if (to == -1) {
					break;
				}
				int dist = Integer.parseInt(st.nextToken());
				adj[from].add(new Node(to, dist));
				in[from]++;
			}
		}

		dfs(1);
		System.out.println(result);
	}

	public static int dfs(int v) {
		visited[v] = true;

		int maxChildDist = 0;
		Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		for (Node next : adj[v]) {
			if (visited[next.v]) {
				continue;
			}
			int dist = dfs(next.v) + next.dist;
			maxChildDist = Math.max(maxChildDist, dist);
			pq.add(dist);
		}

		// 현재 노드를 루트로 했을 때, 최대 트리의 지름
		int maxDist = 0;
		for (int i = 0; i < 2; i++) {
			if (pq.isEmpty()) {
				break;
			}
			maxDist += pq.poll();
		}
		result = Math.max(result, maxDist);

		return maxChildDist;
	}
}