import java.util.Scanner;

// 농작물 수확하기
public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		StringBuffer answer = new StringBuffer();
		for (int test_case = 1; test_case <= T; test_case++) {

			int N = sc.nextInt();
			int[][] map = new int[N][N];

			for (int i = 0; i < N; i++) {
				String input = sc.next();
				for (int j = 0; j < N; j++) {
					map[i][j] = input.charAt(j) - '0';
				}
			}

			int sum = 0;
			for (int i = 0, k = N / 2; i < N; i++, k--) { // i=행, j=열, k=건너뛰는 거리
				for (int j = 0 + Math.abs(k); j < N - Math.abs(k); j++) {
					sum += map[i][j];
				}
			}

			answer.append(String.format("#%d %d\n", test_case, sum));
		}

		System.out.println(answer);
		sc.close();
	}

}
