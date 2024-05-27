import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

// 촌수계산
public class Main {

	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 연결 정보를 저장한다.
		List<Integer>[] graph = new ArrayList[N + 1];
		// 각 노드마다 진입차수를 저장한다.
		Map<Integer, Integer> indegrees = new HashMap<>();

		// 초기화
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
			indegrees.put(i, 0);
		}

		// 입력 값 저장
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int front = Integer.parseInt(st.nextToken());
			int rear = Integer.parseInt(st.nextToken());
			graph[front].add(rear);
			indegrees.put(rear, indegrees.get(rear) + 1);
		}

		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N + 1];

		// 진입차수가 0이면, 큐에 저장한다.
		for (int key : indegrees.keySet()) {
			if (indegrees.get(key) == 0) {
				queue.add(key);
				visited[key] = true;
			}
		}

		StringBuffer answer = new StringBuffer();
		while (!queue.isEmpty()) {
			int key = queue.poll();
			answer.append(key).append(" ");

			// 처리된 노드와 연결된 간선을 삭제한다.
			for (int connect : graph[key]) {
				indegrees.put(connect, indegrees.get(connect) - 1);

				// 삭제 후, 진입차수가 0이면, 큐에 저장한다.
				if (indegrees.get(connect) == 0 && !visited[connect]) {
					queue.add(connect);
					visited[connect] = true;
				}
			}
		}

		System.out.println(answer);

	}

}
