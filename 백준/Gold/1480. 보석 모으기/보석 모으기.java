import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 보석 모으기
public class Main {

	static int N, M, C;
	static int[] jewels;
	static int[][][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		jewels = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			jewels[i] = Integer.parseInt(st.nextToken());
		}

		dp = new int[C + 1][M][1 << N]; // 최대 C 무게까지 담을 수 있으므로, 배열 크기를 C+1로 설정한다.
		recursion(C, M - 1, 0); // 가방을 하나 사용하고 있으므로, 남은 가방 수는 M-1이다.
		System.out.println(dp[C][M - 1][0]);

	}

	public static int recursion(int space, int bagCnt, int jewelStatus) {
		// 담을 수 있는 가방이 없거나, 이미 담았던 보석인 경우
		if (bagCnt == -1 || jewelStatus == (1 << N) - 1) {
			return 0;
		}

		// 이미 해결했던 상황인 경우
		if (dp[space][bagCnt][jewelStatus] != 0) {
			return dp[space][bagCnt][jewelStatus];
		}

		for (int i = 0; i < N; i++) {
			// 현재 가방에 담는 경우: 담은 적 없는 보석이고, 담을 수 있는 무게여야 한다
			if ((jewelStatus & (1 << i)) == 0 && space - jewels[i] >= 0) {
				dp[space][bagCnt][jewelStatus] = Math.max(dp[space][bagCnt][jewelStatus],
						1 + recursion(space - jewels[i], bagCnt, jewelStatus | (1 << i)));
			}

			// 다른 가방에 담는 경우: 담을 적 없는 보석이고, 담을 수 있는 무게이며, 담을 가방이 있어야 한다.
			if ((jewelStatus & (1 << i)) == 0 && C - jewels[i] >= 0 && bagCnt > 0) {
				dp[space][bagCnt][jewelStatus] = Math.max(dp[space][bagCnt][jewelStatus],
						1 + recursion(C - jewels[i], bagCnt - 1, jewelStatus | (1 << i)));
			}
		}

		return dp[space][bagCnt][jewelStatus];
	}

}
