import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int N, adjMatrix[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer answer = new StringBuffer();
		int TC = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(in.readLine());
			int M = Integer.parseInt(in.readLine());

			adjMatrix = new int[N + 1][N + 1]; // 학생번호 1번부터 시작
			StringTokenizer st = null;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(in.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				adjMatrix[a][b] = 1;
			}

			// 각 학생마다 자신보다 큰, 자신보다 작은 학생 각각 탐색
			int ans = 0; // 자신의 키를 알 수 있는 학생 수
			for (int i = 1; i <= N; i++) {
				if (gtBFS(i) + ltBFS(i) == N - 1) {
					ans++;
				}
			}

			answer.append("#" + tc + " " + ans + "\n");
		}
		System.out.println(answer);
	}

	private static int gtBFS(int start) { // 자신보다 큰 학생따라 탐색
		int cnt = 0;

		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[N + 1];

		queue.offer(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			int cur = queue.poll();
			for (int i = 1; i <= N; i++) {
				if (!visited[i] && adjMatrix[cur][i] != 0) {
					queue.offer(i);
					visited[i] = true;
					cnt++; // 나보다 큰 대상 카운팅
				}
			}
		}
		return cnt;
	}

	private static int ltBFS(int start) { // 자신보다 작은 학생따라 탐색
		int cnt = 0;

		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[N + 1];

		queue.offer(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			int cur = queue.poll();
			for (int i = 1; i <= N; i++) {
				if (!visited[i] && adjMatrix[i][cur] != 0) {
					queue.offer(i);
					visited[i] = true;
					cnt++; // 나보다 작은 대상 카운팅
				}
			}
		}
		return cnt;
	}

}
