import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer sb = new StringBuffer();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 재료의 수
			int L = Integer.parseInt(st.nextToken()); // 제한 칼로리

			int[] scores = new int[N + 1]; // 맛에 대한 점수
			int[] calories = new int[N + 1]; // 칼로리

			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());

				int score = Integer.parseInt(st.nextToken());
				int calorie = Integer.parseInt(st.nextToken());

				scores[i] = score;
				calories[i] = calorie;
			}

			int answer = solution(N, L, scores, calories);

			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}

		System.out.println(sb);
	}

	public static int solution(int N, int L, int[] scores, int[] calories) {

		int[] dp = new int[L + 1];
		
		// 가방 문제
		// dp[j]: j 칼로리까지 햄버거의 최대 점수
		for (int i = 1; i <= N; i++) {
			for (int j = L; j - calories[i] >= 0; j--) {
				dp[j] = Math.max(dp[j], scores[i] + dp[j - calories[i]]);
			}
		}

		return dp[L];
	}

}
