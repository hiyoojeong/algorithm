import java.util.Scanner;

// 장훈이의 높은 선반
public class Solution {

	static int N, B;
	static int[] heights;

	static int minDist;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		StringBuffer answer = new StringBuffer();
		for (int test_case = 1; test_case <= T; test_case++) {

			N = sc.nextInt();
			B = sc.nextInt();

			heights = new int[N];
			for (int i = 0; i < N; i++) {
				heights[i] = sc.nextInt();
			}

			minDist = Integer.MAX_VALUE;
			subset(0, 0);

			answer.append(String.format("#%d %d\n", test_case, minDist));
		}

		System.out.println(answer);
		sc.close();
	}

	public static void subset(int cnt, int sum) {
		if (cnt == N) {
			if (sum >= B) {
				minDist = Math.min(minDist, sum - B);
			}
			return;
		}

		subset(cnt + 1, sum + heights[cnt]); // 현재 점원 선택
		subset(cnt + 1, sum); // 현재 점원 미선택
	}

}
