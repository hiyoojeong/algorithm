
import java.util.Scanner;

// 9229. 한빈이와 Spot Mart
public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		StringBuffer answer = new StringBuffer();
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt(); // 과자 봉지의 개수
			int M = sc.nextInt(); // 무게 합 제한

			int[] weights = new int[N];
			for (int i = 0; i < N; i++) {
				weights[i] = sc.nextInt();
			}

			int max = 0;
			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					int sum = weights[i] + weights[j];
					if (sum > M || max > sum) { // 무게 합 제한을 넘거나, 무게 합 최대보다 작은 경우
						continue;
					}
					max = sum;
				}
			}

			answer.append(String.format("#%d %d\n", test_case, max == 0 ? -1 : max));
		}

		System.out.println(answer);

		sc.close();
	}

}
