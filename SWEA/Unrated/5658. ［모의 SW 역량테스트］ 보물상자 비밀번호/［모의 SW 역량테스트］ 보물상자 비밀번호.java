
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

// 보물상자 비밀번호
public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuffer answer = new StringBuffer();

		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			int K = sc.nextInt();

			Set<Integer> set = new HashSet<>();
			String password = sc.next();
			int len = password.length() / 4;
			for (int i = 0; i < len; i++) {
				// 각 변에 있는 숫자 확인
				for (int j = 0; j < N; j += len) {
					String hex = password.substring(j, j + len);
					int decimal = Integer.parseInt(hex, 16);
					set.add(decimal);
				}

				// 돌리기
				password = password.charAt(N - 1) + password.substring(0, N - 1);
			}

			Queue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
			for (int num : set) {
				queue.add(num);
			}

			int res = 0;
			for (int i = 0; i < K; i++) {
				res = queue.poll();
			}

			answer.append(String.format("#%d %d\n", test_case, res));
		}

		System.out.println(answer);
		sc.close();
	}

}
