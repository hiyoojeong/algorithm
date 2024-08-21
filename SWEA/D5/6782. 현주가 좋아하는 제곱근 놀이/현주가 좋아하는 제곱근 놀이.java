import java.util.Scanner;

// 현주가 좋아하는 제곱근 놀이
public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		StringBuffer answer = new StringBuffer();
		for (int test_case = 1; test_case <= T; test_case++) {
			long N = sc.nextLong();

			int cnt = 0;
			while (N != 2) {
				// 제곱근이 정수면 제곱근으로 바꾼다.
				double sqrt_N = Math.sqrt(N);
				if (sqrt_N == (long) sqrt_N) {
					N = (long) sqrt_N;
					cnt++;
					continue;
				}

				// 제곱근이 정수가 아니면, 제곤근이 정수가 될 때까지 +1한다.
				long tmp = (long) sqrt_N + 1; // 가장 가까운 정수 제곱근
				cnt += (tmp * tmp) - N + 1; // 가장 가까운 정수 제곱근을 만들기 위해, +1 연산해야하는 횟수를 한 번에 더한다.
				N = tmp;
			}

			answer.append(String.format("#%d %d\n", test_case, cnt));
		}

		System.out.println(answer);

		sc.close();

	}

}
