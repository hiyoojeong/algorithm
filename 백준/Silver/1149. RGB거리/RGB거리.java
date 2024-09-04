
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// RGB 거리
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[][] cost = new int[N + 1][3];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] dp = new int[N + 1][3];
		for (int i = 1; i <= N; i++) { // 현재 색칠하려는 집
			for (int j = 0; j < 3; j++) { // 현재 색칠하려는 색
				// i-1번째 집이 현재 색칠하려는 색과 다르게 칠해졌을 때의 최소 비용 + 현재 색을 색칠하는 비용
				dp[i][j] = Math.min(dp[i - 1][(j + 1) % 3], dp[i - 1][(j + 2) % 3]) + cost[i][j];
			}
		}

		int res = Math.min(Math.min(dp[N][0], dp[N][1]), dp[N][2]);
		System.out.println(res);
	}

}
