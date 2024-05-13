

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 자전거 묘기
public class No25706 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] jump = new int[N];
		int[] dp = new int[N];

		// 점프대 정보 저장
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			jump[i] = Integer.parseInt(st.nextToken());
		}

		// 밟는 칸 개수 구하기
		dp[N - 1] = 1;
		for (int i = N - 2; i >= 0; i--) {
			if (jump[i] == 0) {
				dp[i] = dp[i + 1] + 1;
			} else {
				int next = i + 1 + jump[i];
				if (next >= N) {
					dp[i] = 1;
				} else {
					dp[i] = dp[next] + 1;
				}
			}
		}

		for (int i = 0; i < N; i++) {
			System.out.print(dp[i] + " ");
		}
	}

}
