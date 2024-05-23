import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// RGB거리
public class Main {

	static class House {
		int idx, color, cnt;

		public House(int idx, int color, int cnt) {
			this.idx = idx;
			this.color = color;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[][] cost = new int[N][3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] dp = new int[N][3];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == 0) {
					dp[i][j] = cost[i][j];
				} else {
					// i번째 집이 j로 칠해졌을 때의 최소 비용
					// = j로 칠해지지 않은 i-1번째 집의 최소 비용 + i번째 집을 j로 칠하는 비용
					dp[i][j] = Math.min(dp[i - 1][((j - 1) + 3) % 3], dp[i - 1][((j - 2) + 3) % 3]) + cost[i][j];
				}
			}
		}

		int answer = Math.min(Math.min(dp[N - 1][0], dp[N - 1][1]), dp[N - 1][2]);
		System.out.println(answer);

	}

}
