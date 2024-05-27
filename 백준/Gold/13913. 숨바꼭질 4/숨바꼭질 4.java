import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

// 꿀 따기
public class Main {

	static int N, K;
	static int[] time;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		time = new int[100001];
		parent = new int[100001];

		// 이동하는 최단 경로를 구한다.
		dfs();

		// parent 배열을 따라가며, 이동하는 최단 경로를 출력한다.
		Stack<Integer> stack = new Stack<>();
		stack.push(K);

		int idx = K;
		while (idx != N) {
			stack.push(parent[idx]);
			idx = parent[idx];
		}

		// 수빈이가 동생을 찾는 가장 빠른 시간을 출력
		System.out.println(time[K]);

		// 어떻게 이동해야 하는지 공백으로 구분해 출력
		StringBuffer answer = new StringBuffer();
		while (!stack.isEmpty()) {
			answer.append(stack.pop()).append(" ");
		}

		System.out.println(answer);
	}

	public static void dfs() {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(N);

		while (!queue.isEmpty()) {
			int now = queue.poll();

			if (now == K) {
				break;
			}

			for (int i = 0; i < 3; i++) {
				int next = 0;

				if (i == 0)
					next = now + 1;
				else if (i == 1)
					next = now - 1;
				else if (i == 2)
					next = now * 2;

				if (next < 0 || next > 100000) {
					continue;
				}

				if (time[next] == 0) {
					queue.add(next);
					time[next] = time[now] + 1;
					parent[next] = now;
				}
			}
		}
	}

}