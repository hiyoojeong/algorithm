import java.util.Scanner;

// 4008. [모의 SW 역량테스트] 숫자 만들기
public class Solution {

	static int N;
	static int[] nums;
	static int[] ops;

	static int[] selected;

	static int max, min;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		StringBuffer answer = new StringBuffer();
		for (int test_case = 1; test_case <= T; test_case++) {
			// 입력
			N = sc.nextInt();
			nums = new int[N];
			ops = new int[4];

			for (int i = 0; i < 4; i++) {
				ops[i] = sc.nextInt();
			}

			for (int i = 0; i < N; i++) {
				nums[i] = sc.nextInt();
			}

			// 순열
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			selected = new int[N - 1];
			permutation(0);

			answer.append(String.format("#%d %d\n", test_case, max - min));
		}

		System.out.println(answer);

		sc.close();

	}

	public static void permutation(int cnt) {
		if (cnt == N - 1) {
			int result = nums[0];

			for (int i = 0; i < N - 1; i++) {
				if (selected[i] == 0) { // +
					result += nums[i + 1];
				} else if (selected[i] == 1) { // -
					result -= nums[i + 1];
				} else if (selected[i] == 2) { // *
					result *= nums[i + 1];
				} else if (selected[i] == 3) { // /
					result /= nums[i + 1];
				}
			}

			min = Math.min(min, result);
			max = Math.max(max, result);
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (ops[i] == 0) {
				continue;
			}

			selected[cnt] = i;
			ops[i]--; // 연산자 사용 처리
			permutation(cnt + 1);
			ops[i]++; // 연산자 사용 원상복구

		}
	}

}
