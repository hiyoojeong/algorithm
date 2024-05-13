

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 버섯 농장
public class No27737 {

	static int N, M, K;
	static int[][] farm;
	static int ground;

	static int[] mr = { 1, -1, 0, 0 };
	static int[] mc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 버섯 농장 크기
		M = Integer.parseInt(st.nextToken()); // 버섯 포자 개수
		K = Integer.parseInt(st.nextToken()); // 포자 하나당 얼마나 퍼지는 가

		farm = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				farm[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		boolean check = false;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				// 버섯을 심을 수 있는 땅 발견
				if (farm[i][j] == 0) {
					check = true;

					// 땅의 크기를 확인
					ground = 0;
					dfs(i, j);

					// 땅 크기만큼 최소 개수로 버섯 심기
					M -= Math.ceil((double) ground / K);

					// 버섯 포자가 부족!!
					if (M < 0) {
						System.out.println("IMPOSSIBLE");
						return;
					}
				}

			}
		}

		if (check) { // 버섯을 하나라도 심은 경우
			System.out.println("POSSIBLE");
			System.out.println(M);
		} else { // 버섯을 하나도 심지 않은 경우
			System.out.println("IMPOSSIBLE");
		}
	}

	public static void dfs(int r, int c) {
		if (r < 0 || c < 0 || r >= N || c >= N) {
			return;
		}

		if (farm[r][c] == 1) {
			return;
		}

		// 버섯을 심을 수 있는 땅 개수를 센다
		if (farm[r][c] == 0) {
			ground++;
			farm[r][c] = 1;
		}

		// 이동하기
		for (int i = 0; i < 4; i++) {
			dfs(r + mr[i], c + mc[i]);
		}
	}

}
