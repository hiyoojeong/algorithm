
// 뮤탈리스크

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int[][][] dp;

	static int[] attacks = { 9, 3, 1 };
	static List<List<Integer>> orders = new ArrayList<>();

	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 입력
		int N = Integer.parseInt(br.readLine());
		int[] scvs = new int[3];
		dp = new int[61][61][61];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			scvs[i] = Integer.parseInt(st.nextToken());
		}

		// SCV를 파괴하기 위한 공격 순서 조합을 만든다.
		makeAttacks(new ArrayList<>());

		// 모든 SCV를 파괴하기 위한 공격 횟수의 최솟값을 찾는다.
		dfs(scvs, 0);

		System.out.println(min);
	}

	public static void dfs(int[] scv, int depth) {
		int s1 = scv[0];
		int s2 = scv[1];
		int s3 = scv[2];

		// 모든 SCV를 파괴한 경우, 결과를 업데이트한다
		if (s1 <= 0 && s2 <= 0 && s3 <= 0) {
			min = Math.min(min, depth);
			return;
		}

		// 이미 최솟값을 넘겼다면, 더이상 확인하지 않는다.
		if (dp[s1][s2][s3] != 0 && dp[s1][s2][s3] <= depth) {
			return;
		}

		dp[s1][s2][s3] = depth;

		// 공격 순서 조합대로 공격해본다.
		for (int i = 0; i < orders.size(); i++) {
			List<Integer> order = orders.get(i);
			int n1 = Math.max(scv[0] - attacks[order.get(0)], 0);
			int n2 = Math.max(scv[1] - attacks[order.get(1)], 0);
			int n3 = Math.max(scv[2] - attacks[order.get(2)], 0);

			dfs(new int[] { n1, n2, n3 }, depth + 1);
		}
	}

	// SCV를 파괴하기 위한 공격 순서 조합을 만든다.
	public static void makeAttacks(List<Integer> result) {
		if (result.size() == attacks.length) {
			List<Integer> order = new ArrayList<>();
			for (int i = 0; i < attacks.length; i++) {
				order.add(result.get(i));
			}
			orders.add(order);
			return;
		}

		for (int i = 0; i < attacks.length; i++) {
			if (!result.contains(i)) {
				result.add(i);
				makeAttacks(result);
				result.remove(result.size() - 1);
			}
		}
	}

}
