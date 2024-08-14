import java.util.Scanner;

// 파리퇴치
public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		StringBuffer answer = new StringBuffer();
		for (int test_case = 1; test_case <= T; test_case++) {
			// 입력
			int N = sc.nextInt();
			int M = sc.nextInt();

			int[][] sum = new int[N + 1][N + 1]; // 누적합
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					int cnt = sc.nextInt();

					// sum[i][j] : (0,0)에서 (i,j)까지의 누적합
					sum[i][j] = cnt + sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
				}
			}

			// 결과
			int res = solution(N, M, sum);

			// 출력
			answer.append(String.format("#%d %d\n", test_case, res));
		}
		System.out.println(answer);

		sc.close();
	}

	public static int solution(int N, int M, int[][] sum) {
		int maxValue = 0;
		
		for (int i = 1; i <= N - (M-1); i++) {
			for (int j = 1; j <= N - (M-1); j++) {
				// (i,j)는 왼쪽 상단 좌표이다.
				// (i,j) ~ (i+m-1, j+m-1)까지의 누적합을 구한다.
				int sx = i, sy = j;
				int ex = i + (M - 1), ey = j + (M - 1);
				int value = sum[ex][ey] - sum[sx - 1][ey] - sum[ex][sy - 1] + sum[sx - 1][sy - 1];
				maxValue = Math.max(value, maxValue);
			}
		}

		return maxValue;
	}

}
