import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int c = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());

		int cost = 0;
		int customer = 0;
		int[] dp = new int[c + 101];
		Arrays.fill(dp, Integer.MAX_VALUE - 1000);
		dp[0] = 0;

		for (int i = 0; i < n; i++) { // 도시 i 번째까지
			st = new StringTokenizer(br.readLine());
			cost = Integer.parseInt(st.nextToken());
			customer = Integer.parseInt(st.nextToken());
			for (int j = customer; j < c + 101; j++) { // 고객 j 명까지
				dp[j] = Math.min(dp[j], cost + dp[j - customer]);
			}
		}

		int result = Integer.MAX_VALUE;
		for (int i = c; i < c + 101; i++) {
			result = Math.min(result, dp[i]);
		}

		System.out.println(result);
	}

}
