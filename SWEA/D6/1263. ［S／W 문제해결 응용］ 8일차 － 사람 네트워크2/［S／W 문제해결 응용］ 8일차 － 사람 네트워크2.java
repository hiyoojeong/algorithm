
import java.util.Scanner;

// 사람 네트워크2
class Solution {

	static final int INF = Integer.MAX_VALUE / 2;

	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		StringBuffer answer = new StringBuffer();
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			int[][] map = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
					if(i!=j && map[i][j] == 0 ) {
						map[i][j] = INF;
					}
				}
			}

			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
					}
				}
			}

			int minDist = INF;
			for (int i = 0; i < N; i++) {
				int dist = 0;
				for (int j = 0; j < N; j++) {
					dist += map[i][j];
				}
				minDist = Math.min(minDist, dist);
			}

			answer.append(String.format("#%d %d\n", test_case, minDist));
		}
		System.out.println(answer);
		sc.close();
	}
}