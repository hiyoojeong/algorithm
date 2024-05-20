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
		int[] dp = new int[K + 1]; // 최대 무게 K인 가방에 담을 수 있는 최대 가치
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			weight[i] = w;
			value[i] = v;
		}

		for (int i = 1; i <= N; i++) {

			// 담을 물건 수를 늘려가면서,
			// 최대 무게(K)부터 담을 수 있는 무게까지만 탐색한다.
			for (int j = K; j - weight[i] >= 0; j--) {
				dp[j] = Math.max(dp[j], value[i] + dp[j - weight[i]]);
			}

		}

		System.out.println(dp[K]);

	}

}
