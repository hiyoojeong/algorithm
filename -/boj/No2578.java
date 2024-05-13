

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// 빙고
public class No2578 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 철수가 적은 빙고판
		int[][] board = new int[5][5];
		
		// 숫자에 대한 수가 적은 빙고판 위치 정보
		HashMap<Integer, int[]> map = new HashMap<>();
		
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				int num = Integer.parseInt(st.nextToken());
				board[i][j] = num;
				map.put(num, new int[] { i, j });
			}
		}

		// 철수 빙고판에서 사회자가 부른 숫자 체크
		boolean[][] check = new boolean[5][5];
		int cnt = 0;
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				cnt++;
				
				int num = Integer.parseInt(st.nextToken());
				int[] pos = map.get(num);
				check[pos[0]][pos[1]] = true;
				
				if(bingo(check)) {
					System.out.println(cnt);
					return;
				}
			}
		}

	}

	public static boolean bingo(boolean[][] check) {
		int bingo = 0;
		int cnt;

		// 가로 체크
		for (int i = 0; i < 5; i++) {
			cnt = 0;
			for (int j = 0; j < 5; j++) {
				if (check[i][j]) {
					cnt++;
				}
			}
			if (cnt == 5) {
				bingo++;
			}
		}

		// 세로 체크
		for (int i = 0; i < 5; i++) {
			cnt = 0;
			for (int j = 0; j < 5; j++) {
				if (check[j][i]) {
					cnt++;
				}
			}
			if (cnt == 5) {
				bingo++;
			}
		}

		// 대각선
		cnt = 0;
		for (int i = 0; i < 5; i++) {
			if (check[i][i]) {
				cnt++;
			}
		}
		if (cnt == 5) {
			bingo++;
		}

		// 대각선
		cnt = 0;
		for (int i = 0; i < 5; i++) {
			if (check[i][4 - i]) {
				cnt++;
			}
		}
		if (cnt == 5) {
			bingo++;
		}

		return bingo >= 3 ? true : false;
	}

}
