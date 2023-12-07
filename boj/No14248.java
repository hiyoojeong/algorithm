

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class No14248 {

	static List<Integer> answer = new ArrayList<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 돌의 개수
		int n = Integer.parseInt(br.readLine());

		// 돌의 정보
		int[] stones = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			stones[i] = Integer.parseInt(st.nextToken());
		}

		// 시작 위치
		int pos = Integer.parseInt(br.readLine()) - 1;

		dfs(stones, pos, new boolean[n]);
		System.out.println(answer.size());
	}

	public static void dfs(int[] stones, int pos, boolean[] visit) {
		if (!answer.contains(pos)) {
			answer.add(pos);
		}

		System.out.println("pos: " + pos);

		int left = pos - stones[pos];
		int right = pos + stones[pos];

		if (left >= 0 && !visit[left]) {
			visit[left] = true;
			dfs(stones, left, visit);
			visit[left] = false;
		}

		if (right < stones.length && !visit[right]) {
			visit[right] = true;
			dfs(stones, right, visit);
			visit[right] = false;
		}

	}

}
