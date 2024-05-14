import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 행성 탐사
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int testCase = Integer.parseInt(br.readLine());

		int[][] jungle = new int[n + 1][m + 1];
		int[][] ocean = new int[n + 1][m + 1];
		int[][] ice = new int[n + 1][m + 1];

		for (int i = 1; i <= n; i++) {
			String input = br.readLine();
			for (int j = 1; j <= m; j++) {
				jungle[i][j] = jungle[i][j - 1] + jungle[i - 1][j] - jungle[i - 1][j - 1];
				ocean[i][j] = ocean[i][j - 1] + ocean[i - 1][j] - ocean[i - 1][j - 1];
				ice[i][j] = ice[i][j - 1] + ice[i - 1][j] - ice[i - 1][j - 1];
				if (input.charAt(j - 1) == 'J') {
					jungle[i][j]++;
				} else if (input.charAt(j - 1) == 'O') {
					ocean[i][j]++;
				} else if (input.charAt(j - 1) == 'I') {
					ice[i][j]++;
				}
			}
		}

		StringBuffer answer = new StringBuffer();
		for (int t = 0; t < testCase; t++) {
			st = new StringTokenizer(br.readLine());
			int sx = Integer.parseInt(st.nextToken());
			int sy = Integer.parseInt(st.nextToken());
			int ex = Integer.parseInt(st.nextToken());
			int ey = Integer.parseInt(st.nextToken());

			int j = jungle[ex][ey] - jungle[sx - 1][ey] - jungle[ex][sy - 1] + jungle[sx - 1][sy - 1];
			int o = ocean[ex][ey] - ocean[sx - 1][ey] - ocean[ex][sy - 1] + ocean[sx - 1][sy - 1];
			int i = ice[ex][ey] - ice[sx - 1][ey] - ice[ex][sy - 1] + ice[sx - 1][sy - 1];
			answer.append(j + " " + o + " " + i + "\n");
		}
		System.out.println(answer);

	}

}
