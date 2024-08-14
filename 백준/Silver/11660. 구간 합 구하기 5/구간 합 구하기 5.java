import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 구간 합 구하기 5
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer answer = new StringBuffer();

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 표의 크기
		int M = Integer.parseInt(st.nextToken()); // 합을 구해야 하는 횟수

		int[][] sum = new int[N + 1][N + 1]; // 누적합
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				// sum[i][j] : (0,0)부터 (i,j)까지의 누적합
				int num = Integer.parseInt(st.nextToken());
				sum[i][j] = num + sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int sx = Integer.parseInt(st.nextToken());
			int sy = Integer.parseInt(st.nextToken());
			int ex = Integer.parseInt(st.nextToken());
			int ey = Integer.parseInt(st.nextToken());

			// 시작 좌표의 앞에 있는 값들을 삭제하고, 중복으로 삭제된 값은 다시 더해준다.
			int prefixSum = sum[ex][ey] - sum[sx - 1][ey] - sum[ex][sy - 1] + sum[sx - 1][sy - 1];
			answer.append(prefixSum).append("\n");
		}
		
		System.out.println(answer);

	}

}
