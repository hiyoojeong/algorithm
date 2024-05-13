

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class No11985 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		long K = Long.parseLong(st.nextToken());

		// 오렌지 크기 정보
		long[] oranges = new long[N + 1];
		for (int i = 1; i <= N; i++) {
			oranges[i] = Long.parseLong(br.readLine());
		}

		// dp[i] : i번째 오렌지까지 포장하는 최소 비용
		long[] dp = new long[N + 1];
		Arrays.fill(dp, Long.MAX_VALUE);
		dp[0] = 0;

		// i번째 오렌지를 기준으로, 앞에 있는 오렌지를 함께 포장했을 때 최소로 포장할 수 있으면
		// dp[i]를 업데이트 한다.
		for (int i = 1; i <= N; i++) {
			long max = Long.MIN_VALUE;
			long min = Long.MAX_VALUE;
			for (int size = 1; size <= M; size++) {
				if (i - size < 0) {
					break;
				}

				max = Math.max(max, oranges[i - size + 1]);
				min = Math.min(min, oranges[i - size + 1]);
				dp[i] = Math.min(dp[i], dp[i - size] + (K + size * (max - min)));
			}
		}

		System.out.println(dp[N]);
	}

}
