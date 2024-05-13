package 동적계획법;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 신나는 함수 실행
public class No9184 {

	static int[][][] dp = new int[21][21][21];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			if (a == -1 && b == -1 && c == -1) {
				break;
			}

			System.out.printf("w(%d, %d, %d) = %d\n", a, b, c, w(a, b, c));
		}
	}

	public static int w(int a, int b, int c) {
		// dp table에 저장되어 있으면, 바로 return
		if (range(a, b, c) && dp[a][b][c] != 0) {
			return dp[a][b][c];
		}

		if (a <= 0 || b <= 0 || c <= 0) {
			return 1;
		}

		if (a > 20 || b > 20 || c > 20) {
			return dp[20][20][20] = w(20, 20, 20); // dp table에 저장
		}

		if (a < b && b < c) {
			return dp[a][b][c] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c); // dp table에 저장
		}

		return dp[a][b][c] = w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1); // dp
																												// table에
																												// 저장

	}

	public static boolean range(int a, int b, int c) {
		return a >= 0 && b >= 0 && c >= 0 && a <= 20 && b <= 20 && c <= 20;
	}

}
