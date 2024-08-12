import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine()); // 민규가 구매하려고 하는 카드의 개수
		int[] cards = new int[N + 1]; // 카드팩 가격 정보

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}

		int[] dp = new int[N + 1]; // dp[i] : i개의 카드를 구매하기 위해 민규가 지불해야 하는 금액의 최댓값
		dp[1] = cards[1]; // 1개의 카드를 구매하기 위해 민규가 지불해야 하는 금액의 최대값

		for (int i = 2; i <= N; i++) {
			dp[i] = cards[i];
			for (int j = i - 1; j >= 1; j--) {
				// dp[j] = d[i-1] + dp[1]
				// dp[j] = d[i-2] + dp[2]
				// dp[j] = d[i-3] + dp[3]
				// ...
				dp[i] = Math.max(dp[i], dp[i - j] + dp[j]);
			}
		}

		System.out.println(dp[N]);
	}

}
