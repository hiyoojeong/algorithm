import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 데이터를 저장한다.
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// DP를 이용하여 가장 큰 증가하는 부분 수열의 합을 계산한다.
		int[] dp = new int[n];
		int maxSum = 0;
		for (int k = 0; k < n; k++) {
			dp[k] = arr[k];
			for (int i = 0; i < k; i++) {
				if (arr[i] < arr[k]) {
					dp[k] = Math.max(dp[k], dp[i] + arr[k]);
				}
			}
			maxSum = Math.max(maxSum, dp[k]);
		}

		System.out.println(maxSum);
	}
}
