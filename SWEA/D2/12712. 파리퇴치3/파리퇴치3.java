import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 파리퇴치3
public class Solution {

	static int N, M;
	static int[][] flies;

	static int[][] d_plus = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
	static int[][] d_x = { {-1, -1}, {-1, 1}, {1, -1}, {1, 1} };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer answer = new StringBuffer();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			flies = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					flies[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			answer.append("#").append(t).append(" ").append(solution()).append("\n");

		}
		
		System.out.println(answer);

	}

	public static int solution() {
		int maxSum = Integer.MIN_VALUE;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int sum_plus = getPlusSum(i, j);
				int sum_x = getXSum(i, j);
				
				maxSum = Math.max(Math.max(sum_plus, sum_x), maxSum);
			}
		}
		
		return maxSum;
	}

	public static int getPlusSum(int x, int y) {
		int sum = flies[x][y];

		for (int d = 0; d < 4; d++) {
			int nx = x;
			int ny = y;
			
			for (int m = 0; m < M-1; m++) {
				nx += d_plus[d][0];
				ny += d_plus[d][1];

				if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
					sum += flies[nx][ny];
				}
			}
		}
		
		return sum;
	}
	
	public static int getXSum(int x, int y) {
		int sum = flies[x][y];

		for (int d = 0; d < 4; d++) {
			int nx = x;
			int ny = y;
			
			for (int m = 0; m < M-1; m++) {
				nx += d_x[d][0];
				ny += d_x[d][1];

				if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
					sum += flies[nx][ny];
				}
			}
		}
		
		return sum;
	}
}
