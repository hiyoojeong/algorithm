import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 팰린드롬?
public class Main {

	static final int TRUE = 1, FALSE = -1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int[][] dp = new int[N][N];

		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;

			int s = start, e = end;
			while (s <= e) {
				// 팰린드롬인지 아닌지 판정이 난 경우
				if (dp[s][e] == TRUE) {
					dp[start][end] = TRUE;
					break;
				}
				if (dp[s][e] == FALSE) {
					dp[start][end] = FALSE;
					break;
				}

				// 팰린드롬이 아닌 경우
				if (arr[s] != arr[e]) {
					dp[s][e] = FALSE;
					dp[start][end] = FALSE;
					break;
				}
				
				s++;
				e--;
			}

			// 팰린드롬인 경우
			if (s > e) {
				dp[start][end] = TRUE;
			}

			bw.write(dp[start][end] == TRUE ? "1" : "0");
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}