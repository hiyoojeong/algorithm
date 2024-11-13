import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 할로윈의 양아치
public class Main {

	static int[] parent, cnt, candy;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 아이들의 수
		int M = Integer.parseInt(st.nextToken()); // 아이들의 친구 관계 수
		int K = Integer.parseInt(st.nextToken()); // 울음소리가 공명하기 위한 최소 아이의 수

		parent = new int[N + 1];
		cnt = new int[N + 1];
		candy = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
			cnt[i] = 1;
			candy[i] = Integer.parseInt(st.nextToken());
		}

		// union-find
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a, b);
		}

		// knapsack
		List<Integer> weight = new ArrayList<>();
		List<Integer> value = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			if (parent[i] == i) {
				weight.add(cnt[i]);
				value.add(candy[i]);
			}
		}

		int[] dp = new int[K]; // K-1명까지 울릴 수 있다.
		for (int i = 0; i < weight.size(); i++) {
			for (int j = K - 1; j - weight.get(i) >= 0; j--) {
				dp[j] = Math.max(dp[j], dp[j - weight.get(i)] + value.get(i));
			}
		}

		System.out.println(dp[K - 1]);
	}

	private static int find(int v) {
		if (v == parent[v]) {
			return v;
		}
		return parent[v] = find(parent[v]);
	}

	private static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if (pa == pb) {
			return;
		}
		parent[pb] = pa;
		cnt[pa] += cnt[pb];
		candy[pa] += candy[pb];
	}
}