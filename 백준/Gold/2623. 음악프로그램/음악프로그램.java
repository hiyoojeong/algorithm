
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 음악프로그램
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer answer = new StringBuffer();

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 가수의 수
		int M = Integer.parseInt(st.nextToken()); // 보조 PD의 수

		// 위상정렬
		List<Integer>[] adjList = new ArrayList[N + 1]; // 순서
		int[] in = new int[N + 1]; // 진입차수 개수

		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			for (int j = 1; j < size; j++) {
				int to = Integer.parseInt(st.nextToken());
				adjList[from].add(to);
				in[to]++;
				from = to;
			}
		}

		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[N + 1];

		for (int i = 1; i <= N; i++) { // 진입차수가 0인 정점
			if (in[i] == 0) {
				queue.add(i);
			}
		}

		int cnt = 0;
		while (!queue.isEmpty()) {
			int now = queue.poll();
			answer.append(now + "\n");
			cnt++;

			for (int next : adjList[now]) {
				if (visited[next]) {
					continue;
				}
				in[next]--; // 진입차수 1 감소
				if (in[next] == 0) { // 진입차수가 0이 된 정점
					queue.add(next);
					visited[next] = true;
				}
			}
		}

		System.out.println(cnt == N ? answer : 0);
	}

}
