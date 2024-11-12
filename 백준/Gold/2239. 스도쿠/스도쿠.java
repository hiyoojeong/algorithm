import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 스도쿠
public class Main {

	static class Pos {

		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int[][] map = new int[9][9];
	static List<Pos> pos = new ArrayList<>();
	static StringBuilder answer = null;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 9; i++) {
			String input = br.readLine();
			for (int j = 0; j < 9; j++) {
				map[i][j] = input.charAt(j) - '0';
				if (map[i][j] == 0) {
					pos.add(new Pos(i, j));
				}
			}
		}

		dfs(0);
		System.out.println(answer);
	}

	private static void dfs(int idx) {
		// 스도쿠 이미 만들어짐
		if (answer != null) {
			return;
		}

		// 스도쿠 만들어짐
		if (idx == pos.size()) {
			answer = new StringBuilder();
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					answer.append(map[i][j]);
				}
				answer.append("\n");
			}
			return;
		}

		int r = pos.get(idx).r;
		int c = pos.get(idx).c;

		for (int i = 1; i <= 9; i++) {
			if (!isValid(r, c, i) || answer != null) {
				continue;
			}
			map[r][c] = i;
			dfs(idx + 1);
			map[r][c] = 0;
		}
	}

	private static boolean isValid(int r, int c, int val) {
		for (int i = 0; i < 9; i++) {
			if (map[i][c] == val) { // 행 확인
				return false;
			}
			if (map[r][i] == val) { // 열 확인
				return false;
			}
		}

		for (int i = r / 3 * 3, endR = r / 3 * 3 + 3; i < endR; i++) {
			for (int j = c / 3 * 3, endC = c / 3 * 3 + 3; j < endC; j++) {
				if (map[i][j] == val) { // 3×3 크기 확인
					return false;
				}
			}
		}
		return true;
	}
}