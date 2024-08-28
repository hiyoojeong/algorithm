
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 촌수 계산
class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine()); // 사람 수

		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken()); // 촌수 계산해야 하는 사람 1
		int b = Integer.parseInt(st.nextToken()); // 촌수 계산해야 하는 사람 2

		boolean[][] map = new boolean[N + 1][N + 1]; // 부모자식 관계
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[p][c] = true;
			map[c][p] = true;
		}

		// bfs
		boolean[] visited = new boolean[N + 1];
		visited[a] = true;

		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(a);

		int res = -1, depth = 0;
		while (!queue.isEmpty()) {

			int size = queue.size(); // 현재 큐에 들어있는 정점은 모두 동일한 depth
			for (int i = 0; i < size; i++) { // 다음 depth에 있는 정점 탐색
				int now = queue.poll();

				if (now == b) {
					res = depth;
					break;
				}

				for (int j = 1; j <= N; j++) {
					if (visited[j] || !map[now][j]) { // 방문했거나, 부모자식 관계가 아닌 경우
						continue;
					}
					visited[j] = true;
					queue.add(j);
				}
			}

			depth++;
		}

		System.out.println(res);

	}

}