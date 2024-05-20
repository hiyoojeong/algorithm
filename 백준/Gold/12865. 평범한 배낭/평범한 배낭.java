import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 평범한 배낭
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 물건의 개수
		int K = Integer.parseInt(st.nextToken()); // 최대 무게

		int[] weight = new int[N + 1];
		int[] value = new int[N + 1];
		int[][] dp = new int[N + 1][K + 1]; // 최대 무게 K인 가방에 담을 수 있는 N번째 물건까지의 최대 가치
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			weight[i] = w;
			value[i] = v;
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				// 담을 수 있는 경우
				if (j >= weight[i]) {
					// 담지 않는 경우: dp[i - 1][j]
					// 담는 경우: value[i] + dp[i - 1][j - weight[i]]
					dp[i][j] = Math.max(dp[i - 1][j], value[i] + dp[i - 1][j - weight[i]]);
				}
				// 담을 수 없는 경우
				else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}

		System.out.println(dp[N][K]);

	}

}
