
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 2×n 타일링 2
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		if (N == 1) {
			System.out.println(1);
			return;
		}

		int[] dp = new int[N + 1];
		dp[1] = 1;
		dp[2] = 3;
		for (int i = 3; i <= N; i++) {
			// i-1번째에 한칸짜리 붙이는 1가지 방법 + i-2번째에 두칸짜리 붙이는 2가지 방법
			dp[i] = (dp[i - 1] + dp[i - 2] * 2) % 10007;
		}

		System.out.println(dp[N]);
	}

}
