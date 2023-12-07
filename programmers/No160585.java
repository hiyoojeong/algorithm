// 혼자서 하는 틱택토
public class No160585 {

	public static void main(String[] args) {
		System.out.println(solution(new String[] { "O.X", ".O.", "..X" }));
		System.out.println(solution(new String[] { "OOO", "...", "XXX" }));
		System.out.println(solution(new String[] { "...", ".X.", "..." }));
		System.out.println(solution(new String[] { "...", "...", "..." }));

	}

	public static int solution(String[] board) {
		int cnt_O = count(board, 'O');
		int cnt_X = count(board, 'X');

		// "O"를 표시할 차례인데 "X"를 표시한 경우
		if (cnt_O < cnt_X)
			return 0;
		// "X"를 표시할 차례인데 "O"를 표시한 경우
		if (cnt_O - cnt_X > 1)
			return 0;

		// 선공이 승리해서 게임이 종료되었음에도 게임을 진행한 경우
		if (bingo(board, 'O') && (cnt_O == cnt_X))
			return 0;
		// 후공이 승리해서 게임이 종료되었음에도 게임을 진행한 경우
		if (bingo(board, 'X') && (cnt_O > cnt_X))
			return 0;

		return 1;
	}

	public static boolean bingo(String[] board, char ch) {
		int[][] r = { { 0, 0, 0 }, { 1, 1, 1 }, { 2, 2, 2 }, { 0, 1, 2 }, { 0, 1, 2 }, { 0, 1, 2 }, { 0, 1, 2 },
				{ 0, 1, 2 }, };
		int[][] c = { { 0, 1, 2 }, { 0, 1, 2 }, { 0, 1, 2 }, { 0, 0, 0 }, { 1, 1, 1 }, { 2, 2, 2 }, { 0, 1, 2 },
				{ 2, 1, 0 }, };

		for (int i = 0; i < 8; i++) {
			boolean isBingo = true;
			if (board[r[i][0]].charAt(c[i][0]) != ch)
				isBingo = false;
			if (board[r[i][1]].charAt(c[i][1]) != ch)
				isBingo = false;
			if (board[r[i][2]].charAt(c[i][2]) != ch)
				isBingo = false;
			if (isBingo)
				return true;
		}
		return false;
	}

	public static int count(String[] board, char ch) {
		int cnt = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i].charAt(j) == ch)
					cnt++;
			}
		}
		return cnt;
	}
}
