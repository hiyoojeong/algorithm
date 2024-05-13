

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 세 번 이내에 사과를 먹자
public class No26129 {

	static int[][] board;
	static boolean check;

	static int[] mr = { 1, -1, 0, 0 };
	static int[] mc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 보드 정보 입력
		board = new int[5][5];
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 시작 지점 정보 저장
		st = new StringTokenizer(br.readLine());
		int sr = Integer.parseInt(st.nextToken());
		int sc = Integer.parseInt(st.nextToken());

		check = false;
		dfs(sr, sc, 0, 0);

		System.out.println(check ? 1 : 0);

	}

	public static void dfs(int r, int c, int depth, int count) {
		if (depth > 3) {
			return;
		}

		if (count >= 2) {
			check = true;
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nextr = r + mr[i];
			int nextc = c + mc[i];

			// 범위를 벗어난 경우
			if (nextr < 0 || nextc < 0 || nextr >= 5 || nextc >= 5) {
				continue;
			}

			// 장애물이 있는 경우
			if (board[nextr][nextc] == -1) {
				continue;
			}

			int tmp = board[r][c];
			board[r][c] = -1;
			dfs(nextr, nextc, depth + 1, board[nextr][nextc] == 1 ? count + 1 : count);
			board[r][c] = tmp;

		}

	}

}
