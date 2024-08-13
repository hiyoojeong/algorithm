import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 달팽이 숫자
public class Solution {

	// 우, 하, 좌, 상
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer answer = new StringBuffer();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[][] arr = solution(N);

			answer.append("#").append(t).append("\n");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					answer.append(arr[i][j]).append(" ");
				}
				answer.append("\n");
			}
		}

		System.out.println(answer);
	}

	public static int[][] solution(int N) {
		int[][] arr = new int[N][N];

		int num = 1;
		int d = 0;

		int x = 0, y = 0;
		arr[x][y] = num;
		while (num < Math.pow(N, 2)) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			if ((nx < 0 || ny < 0 || nx >= N || ny >= N) || arr[nx][ny] != 0) {
				d = (d + 1) % 4;
				continue;
			}

			arr[nx][ny] = ++num;

			x = nx;
			y = ny;
		}

		return arr;
	}

}