import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 틱택토
public class Main {

	static char[][] board = new char[3][3];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer answer = new StringBuffer();

		String input = null;
		while (!(input = br.readLine()).equals("end")) {
			int pos = 0;

			int xCnt = 0;
			int oCnt = 0;
			int blankCnt = 0;

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					char ch = input.charAt(pos++);

					if (ch == 'X') {
						xCnt++;
					} else if (ch == 'O') {
						oCnt++;
					} else if (ch == '.') {
						blankCnt++;
					}

					board[i][j] = ch;
				}
			}

			// 'X의 수 = O의 수' 혹은 'X의 수 = O의 수 + 1'이여야 한다.
			if (!(xCnt == oCnt || xCnt == oCnt + 1)) {
				answer.append("invalid").append("\n");
				continue;
			}

			boolean x_bingo = bingo('X');
			boolean o_bingo = bingo('O');

			// X 빙고이면 'X의 수 = O의 수 + 1'이여야 한다.
			if (x_bingo && !(xCnt == oCnt + 1)) {
				answer.append("invalid").append("\n");
				continue;
			}
			// O 빙고이면 'X의 수 = O의 수'이여야 한다.
			if (o_bingo && !(xCnt == oCnt)) {
				answer.append("invalid").append("\n");
				continue;
			}
			// 하나라도 빙고가 되면, 즉시 게임이 종료되어야 한다.
			if(x_bingo && o_bingo) {
				answer.append("invalid").append("\n");
				continue;
			}
			// 빙고가 없을 때 최종 상태가되려면, 빈칸이 없어야 한다.
			if (!x_bingo && !o_bingo && blankCnt > 0) {
				answer.append("invalid").append("\n");
				continue;
			}

			answer.append("valid").append("\n");

		}

		System.out.println(answer);

	}

	public static boolean bingo(char ch) {
		boolean bingo = false;

		for (int i = 0; i < 3; i++) {

			// 세로
			bingo = true;
			for (int j = 0; j < 3; j++) {
				if (board[i][j] != ch) {
					bingo = false;
					break;
				}
			}
			if (bingo) {
				return true;
			}

			// 가로
			bingo = true;
			for (int j = 0; j < 3; j++) {
				if (board[j][i] != ch) {
					bingo = false;
					break;
				}
			}
			if (bingo) {
				return true;
			}

		}

		// 대각선(왼쪽 위 -> 오른쪽 아래)
		bingo = true;
		for (int i = 0; i < 3; i++) {
			if (board[i][i] != ch) {
				bingo = false;
				break;
			}
		}
		if (bingo) {
			return true;
		}

		// 대각선(오른쪽 위 -> 왼쪽 아래)
		bingo = true;
		for (int i = 0; i < 3; i++) {
			if (board[i][2-i] != ch) {
				bingo = false;
				break;
			}
		}
		if (bingo) {
			return true;
		}

		return false;
	}

}
