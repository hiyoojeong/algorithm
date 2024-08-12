import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 제곱수의 합
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); // 자연수
		int[] dp = new int[N + 1]; // dp[i] : 자연수 i를 제곱수의 합으로 표현할 때 그 항의 최소 개수

		dp[1] = 1;

		for (int i = 2; i <= N; i++) {
			// 제곱수라면, 최수 항 개수는 1이다.
			if (Math.sqrt(i) == (int) Math.sqrt(i)) {
				dp[i] = 1;
				continue;
			}

			// 제곱수가 아니라면, ...
			dp[i] = Integer.MAX_VALUE;
			for (int j = i - 1; j >= 1; j--) {
				// dp[i] = dp[i-1] + dp[1];
				// dp[i] = dp[i-2] + dp[2];
				// dp[i] = dp[i-3] + dp[3];
				// ...
				dp[i] = Math.min(dp[i], dp[i-j] + dp[j]);
			}
		}

		System.out.println(dp[N]);

	}

}
