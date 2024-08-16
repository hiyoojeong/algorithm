import java.util.Scanner;

// 햄버거 다이어트
public class Solution {

	static int N, L;
	static int[] calories;
	static int[] scores;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		StringBuffer answer = new StringBuffer();
		for (int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt(); // 재료의 수
			L = sc.nextInt(); // 제한 칼로리

			calories = new int[N];
			scores = new int[N];
			for (int i = 0; i < N; i++) {
				scores[i] = sc.nextInt();
				calories[i] = sc.nextInt();
			}
			
			int maxScore = solution();
			answer.append(String.format("#%d %d\n", test_case, maxScore));
		}

		System.out.println(answer);

		sc.close();

	}

	public static int solution() {
		// dp[i] : i 칼로리까지의 최대 햄버거 점수
		int[] dp = new int[L + 1];

		// i번째 재료까지 선택했을 때
		for (int i = 0; i < N; i++) {
			// j 칼로리까지의 최대 햄버거 점수를 계산한다.
			for (int j = L; j - calories[i] >= 0; j--) {
				// i번째 재료를 선택하는 경우, i번쨰 재료를 선택하지 않는 경우 중에 더 큰 점수를 선택한다.
				dp[j] = Math.max(dp[j - calories[i]] + scores[i], dp[j]);
			}
		}

		return dp[L];
	}

}
