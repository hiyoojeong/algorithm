import java.util.Scanner;

// 의석이의 우뚝 선 산
public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		StringBuffer answer = new StringBuffer();
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			int[] heights = new int[N + 1];
			for (int i = 0; i < N; i++) {
				heights[i] = sc.nextInt();
			}
			heights[N] = Integer.MAX_VALUE;

			int cnt = 0;

			int left = 0, right = 0;
			boolean up = true, down = false;
			for (int i = 0; i < N; i++) {
				// 올라가는 구간
				if (up && heights[i] < heights[i + 1]) {
					left++;
					continue;
				}
				// 내려가는 구간
				if (down && heights[i] > heights[i + 1]) {
					right++;
					continue;
				}

				// 올라가야 하는 구간인데, 내려간 경우 => peak를 찾음
				if (up) {
					up = false;
					down = true;
					continue;
				}
				// 내려가야 하는 구간인데, 올라간 경우 => 구간 종료
				if (down) {
					right++; // 현재 위치도 카운팅

					// 구간 수 업데이트
					if (left >= 1 && right >= 1) {
						cnt += (left * right);
					}

					// 초기화
					left = 1; // 현재 위치도 카운팅
					right = 0;
					up = true;
					down = false;
					continue;
				}
			}

			answer.append(String.format("#%d %d\n", test_case, cnt));
		}

		System.out.println(answer);
		sc.close();

	}

}
