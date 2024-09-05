
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

// 키 순서
public class Solution {

	static int N, M;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuffer answer = new StringBuffer();

		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt(); // 학생 수
			M = sc.nextInt(); // 두 학생의 키를 비교한 횟수

			List<Integer>[] in = new ArrayList[N + 1];
			List<Integer>[] out = new ArrayList[N + 1];
			for (int i = 1; i <= N; i++) {
				in[i] = new ArrayList<>();
				out[i] = new ArrayList<>();
			}

			for (int i = 0; i < M; i++) {
				int from = sc.nextInt();
				int to = sc.nextInt();
				in[to].add(from); // 직전 정점
				out[from].add(to); // 직후 정점
			}

			int cnt = 0;
			for (int i = 1; i <= N; i++) {
				int front = count(i, in); // 앞에 서있는 학생 수
				int rear = count(i, out); // 뒤에 서있는 학생 수
				if (front + rear == N - 1) {
					cnt++;
				}
			}

			answer.append(String.format("#%d %d\n", test_case, cnt));
		}

		System.out.println(answer);
		sc.close();
	}

	public static int count(int start, List<Integer>[] graph) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);

		boolean[] visited = new boolean[N + 1];
		visited[start] = true;

		int cnt = -1;
		while (!queue.isEmpty()) {
			int v = queue.poll();
			cnt++;

			for (int u : graph[v]) {
				if (visited[u]) {
					continue;
				}
				queue.add(u);
				visited[u] = true;
			}
		}

		return cnt;
	}

}
