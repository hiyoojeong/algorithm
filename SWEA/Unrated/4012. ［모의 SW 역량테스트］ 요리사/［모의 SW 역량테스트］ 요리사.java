import java.util.Scanner;

// 4012 [모의 SW 역량테스트] 요리사
public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		StringBuffer answer = new StringBuffer();
		for (int test_case = 1; test_case <= T; test_case++) {
			// 입력
			int N = sc.nextInt();
			int[][] map = new int[N][N];
			int minDist = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			// N = 4라면, 2^4가지 선택할 수 있는 경우의 수가 존재한다. 이는 '1 << N'와 같다.
			// 00000
			// 00001
			// 00010
			// 00011
			// 00100
			// 00101
			// ...
			// 10000

			// 그러나, N / 2만큼의 식재료로 음식을 만든다.
			// 그렇기 때문에 2^4 / 2가지 경우의 수만 확인해보면 된다. 이는 '(1 << N) / 2'와 같다.
			// 000
			// 001
			// 010
			// ...
			// 100
			for (int i = 0; i < (1 << N) / 2; i++) {
				int cnt = 0;
				boolean[] food = new boolean[N]; // true = A 음식 식재료, false = B 음식 식재료

				for (int j = 0; j < N; j++) {
					if ((i & (1 << j)) != 0) { // bit가 1인 경우, A 음식의 식재료로 선택된다.
						food[j] = true;
						cnt++;
					}
				}

				if (cnt == N / 2) { // 식재료가 반반씩 나눠졌다!
					int sumA = 0;
					int sumB = 0;

					for (int k = 0; k < N; k++) {
						for (int l = 0; l < N; l++) {
							if (k == l) { // 동일한 식재료를 사용할 수 없다.
								continue;
							}
							if (food[k] != food[l]) { // 두 식재료가 같은 음식에 사용되어야 한다.
								continue;
							}
							if (food[k]) { // 두 식재료가 모두 A 음식에 사용되는 경우
								sumA += map[k][l];

							} else { // 두 식재료가 모두 B 음식에 사용되는 경우
								sumB += map[k][l];
							}
						}
					}

					minDist = Math.min(minDist, Math.abs(sumA - sumB));
				}
			}

			answer.append(String.format("#%d %d\n", test_case, minDist));
		}

		System.out.println(answer);

		sc.close();
	}

}
