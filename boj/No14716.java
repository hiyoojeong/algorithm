

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 현수막
public class No14716 {

	static int N, M;
	static int[][] banner;
	static int[] mr = { -1, 1, 0, 0, -1, -1, 1, 1};
	static int[] mc = { 0, 0, -1, 1, -1, 1, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 현수막 크기
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 현수막 정보 저장
		banner = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				banner[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 글자 개수 세기
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (banner[i][j] == 1) {
					cnt++;
					bfs(i, j);
				}
			}
		}

		System.out.println(cnt);

	}

	// 상, 하, 좌, 우, 대각선으로 인접하여 연결된 부분 확인  
	public static void bfs(int r, int c) {
		if ((r < 0 || c < 0 || r >= N || c >= M) || (banner[r][c] == 0)) {
			return;
		}

		banner[r][c] = 0;

		for (int i = 0; i < 8; i++) {
			int nextr = r + mr[i];
			int nextc = c + mc[i];
			bfs(nextr, nextc);
		}
	}

}
