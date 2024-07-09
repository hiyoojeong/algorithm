import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 장훈이의 높은 선반
public class Solution {

	static int N, B;
	static int[] heights;
	static int minSum;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer sb = new StringBuffer();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 직원의 수
			B = Integer.parseInt(st.nextToken()); // 선반 높이

			heights = new int[N]; // 직원의 키

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				heights[i] = Integer.parseInt(st.nextToken());
			}
			
			minSum = Integer.MAX_VALUE;
			dfs(0, 0);

			int answer = minSum - B;
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}

		System.out.println(sb);
	}

	public static void dfs(int idx, int sum) {
		if(sum >= minSum) {
			return;
		}
		
		if (idx == N) {
			if(sum >= B) {
				minSum = sum;
			}
			return;
		}

		// 현재 점원으로 탑을 쌓는다.
		dfs(idx + 1, sum + heights[idx]);
		
		// 현재 점원으로 탑을 쌓지 않는다.
		dfs(idx + 1, sum);
	}

}
