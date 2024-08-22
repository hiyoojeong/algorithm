import java.util.Scanner;

// N-Queen
public class Solution {

	static int N, col[], cnt;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		StringBuffer answer = new StringBuffer();
		for (int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt();
			col = new int[N + 1]; // col[i] : i번째 행에서 Queen이 놓여져 있는 열 위치

			cnt = 0;
			dfs(1);

			answer.append(String.format("#%d %d\n", test_case, cnt));
		}

		System.out.println(answer);

		sc.close();

	}

	public static void dfs(int r) {
		// Queen N개를 모두 놓았다.
		if (r > N) {
			cnt++;
			return;
		}

		for (int c = 1; c <= N; c++) {
			if (!isPossible(r, c)) {
				continue;
			}

			col[r] = c;
			dfs(r + 1);

		}
	}

	public static boolean isPossible(int r, int c) {
		for (int i = 1; i < r; i++) {
			// 직전 행에서 Queen이 놓인 열이 현재 행에서 Queen 놓인 열이 같고
			// 직전 행, 열과 현재 행, 열이 대각선 관계
			if (col[i] == c || r - i == Math.abs(c - col[i])) {
				return false;
			}
		}

		return true;
	}

}
