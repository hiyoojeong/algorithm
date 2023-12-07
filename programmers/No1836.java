// 리틀 프렌즈 사천성
public class No1836 {

	static boolean[][] isVisited;

	public static void main(String[] args) {
		int m = 3, n = 3;
		String[] board = { "DBA", "C*A", "CDB" };

//		int m = 2, n = 4;
//		String[] board = {"NRYN", "ARYA"};

//		int m = 4, n = 4;
//		String[] board = {".ZI.", "M.**", "MZU.", ".IU."};

//		int m = 2, n = 2;
//		String[] board = {"AB", "BA"};

		isVisited = new boolean[m][n];
		while (check(m, n, board)) {
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if(!isVisited[i][j]) {
						
					}
				}
			}
		}

	}


	static boolean check(int m, int n, String[] board) {
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (board[m].charAt(n) != '.' && board[m].charAt(n) != '*') {
					return false;
				}
			}
		}
		return true;
	}

}
