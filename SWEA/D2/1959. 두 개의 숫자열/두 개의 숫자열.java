import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 두 개의 숫자열
public class Solution {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer answer = new StringBuffer();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			int[] A = new int[N];
			int[] B = new int[M];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				B[i] = Integer.parseInt(st.nextToken());
			}

			int max = Integer.MIN_VALUE;
			if (N > M) {
				for (int i = 0; i < N - M + 1; i++) {
					int res = 0;
					for (int j = 0; j < M; j++) {
						res += (A[i + j] * B[j]);
					}
					max = Math.max(res, max);
				}
			} else {
				for (int i = 0; i < M - N + 1; i++) {
					int res = 0;
					for (int j = 0; j < N; j++) {
						res += (B[i + j] * A[j]);
					}
					max = Math.max(res, max);
				}
			}

			answer.append("#").append(t).append(" ").append(max).append("\n");
		}

		System.out.println(answer);
	}

}
