
// 가장 큰 정사각형
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[][] arr = new int[n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			String input = br.readLine();
			for (int j = 1; j <= m; j++) {
				arr[i][j] = input.charAt(j - 1) - '0';
			}
		}

		int max = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (arr[i][j] == 1) {
					arr[i][j] = Math.min(Math.min(arr[i - 1][j - 1], arr[i - 1][j]), arr[i][j - 1]) + 1;
				}
				max = Math.max(max, arr[i][j]);
			}
		}

		System.out.println(max * max);
		br.close();
	}
}
