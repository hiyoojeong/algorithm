
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// ACM Craft
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder answer = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 건물의 개수
			int K = Integer.parseInt(st.nextToken()); // 건물간의 건설순서 규칙의 총 개수

			int[] time = new int[N + 1]; // 건물당 건설에 걸리는 시간
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				time[i] = Integer.parseInt(st.nextToken());
			}

			List<Integer>[] adjList = new ArrayList[N + 1]; // 인접한 건설순서
			int[] in = new int[N + 1]; // 진입차수
			for (int i = 1; i <= N; i++) {
				adjList[i] = new ArrayList();
			}

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int X = Integer.parseInt(st.nextToken()); // 건물 X를 지은 다음에 건물 Y를 짓는 것이 가능
				int Y = Integer.parseInt(st.nextToken());
				adjList[X].add(Y);
				in[Y]++;
			}

			int W = Integer.parseInt(br.readLine()); // 건설해야 할 건물의 번호

			// 위상정렬
			int[] endTime = new int[N + 1]; // 건설완료 하는데 드는 최소 시간

			Queue<Integer> q = new ArrayDeque<>();
			for (int i = 1; i <= N; i++) {
				if (in[i] == 0) {
					q.add(i);
					endTime[i] = time[i];
				}
			}

			while (!q.isEmpty()) {
				int v = q.poll();
				for (int u : adjList[v]) {
					endTime[u] = Math.max(time[u] + endTime[v], endTime[u]); // 건설시작하는 최소 시간 업데이트
					in[u]--;
					if (in[u] == 0) {
						q.add(u);
					}
				}
				if (in[W] == 0) { // 건물 W를 건설완료
					answer.append(endTime[W] + "\n");
					break;
				}
			}
		}
		
		System.out.println(answer);
	}

}
