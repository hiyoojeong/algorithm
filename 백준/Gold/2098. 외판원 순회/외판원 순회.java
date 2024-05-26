import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 외판원 순회
public class Main {

	static int N;
	static int[][] dp;
	static int[][] W;
	static final int INF = Integer.MAX_VALUE / 2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		W = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				W[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dp = new int[N][(1 << N) - 1];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], -1);
		}

		System.out.println(tsp(0, 1));
	}

	public static int tsp(int here, int visited) {
		// 모든 도시를 모두 방문한 경우
		if (visited == (1 << N) - 1) {
			if (W[here][0] == 0) {
				return INF;
			}
			return W[here][0];
		}

		// 이미 방문한 도시인 경우
		if (dp[here][visited] != -1) {
			return dp[here][visited];
		}

		dp[here][visited] = INF;

		for (int there = 0; there < N; there++) {
			// 방문한 적 없으면서, 갈 수 있는 경우
			if ((visited & (1 << there)) == 0 && W[here][there] != 0) {
				dp[here][visited] = Math.min(dp[here][visited], W[here][there] + tsp(there, visited | (1 << there)));
			}
		}

		return dp[here][visited];
	}

}
