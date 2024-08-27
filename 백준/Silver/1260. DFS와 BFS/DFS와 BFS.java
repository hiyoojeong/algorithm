
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 1260. DFS와 BFS
class Main {

	static StringBuffer answer;
	static List<Integer>[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		answer = new StringBuffer();

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 정점 수
		int M = Integer.parseInt(st.nextToken()); // 간선 수
		int s = Integer.parseInt(st.nextToken()); // 시작 정점

		list = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			list[to].add(from);
			list[from].add(to);
		}
		
		// 정렬
		for (int i = 1; i <= N; i++) {
			Collections.sort(list[i]);
		}

		// dfs
		dfs(s, new boolean[N + 1]);
		answer.append("\n");

		// bfs
		bfs(s, new boolean[N + 1]);
		
		System.out.println(answer);
	}

	public static void dfs(int v, boolean[] visited) {
		visited[v] = true;
		answer.append(v + " ");

		for (int next : list[v]) {
			if (visited[next]) {
				continue;
			}
			dfs(next, visited);
		}
	}

	public static void bfs(int s, boolean[] visited) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(s);

		visited[s] = true;

		while (!queue.isEmpty()) {
			int now = queue.poll();
			answer.append(now + " ");

			for (int next : list[now]) {
				if (visited[next]) {
					continue;
				}
				queue.add(next);
				visited[next] = true;
			}
		}
	}
}