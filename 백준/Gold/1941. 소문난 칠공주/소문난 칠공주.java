
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 소문난 칠공주
public class Main {

	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static boolean map[][];
	static boolean selected[][];
	static int res;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new boolean[5][5];
		selected = new boolean[5][5];
		for (int i = 0; i < 5; i++) {
			String input = br.readLine();
			for (int j = 0; j < 5; j++) {
				map[i][j] = (input.charAt(j) == 'S' ? true : false); // true: 이다솜파
			}
		}

		combination(0, 0, 0);
		System.out.println(res);
	}

	// 1차원값을 2차원으로 조작
	// idx / 맵사이즈 => 행 2 / 3 => 0
	// idx % 맵사이즈 => 열 2 % 3 => 2
	// 5 / 3 => 1, 5 % 3 => 2

	// 2차원 맵의 값으로 1차원 위치찾기
	// 행첨자 % 열사이즈 + 열첨자 => 1차원 배열의 위치값
	// 2 * 3 + 0 => 6
	// (1,2) 1 * 3 + 2 => 5

	public static void combination(int cnt, int scnt, int start) {
		if (cnt == 7) {
			if (scnt >= 4 && isAdjust()) {
				res++;
			}
			return;
		}

		for (int i = start; i < 25; i++) {
			selected[i / 5][i % 5] = true; // 선택한 학생 체크
			if (map[i / 5][i % 5]) { // 이다솜파 학생인 경우
				combination(cnt + 1, scnt + 1, i + 1);
			} else { // 임도연파 학생인 경우
				combination(cnt + 1, scnt, i + 1);
			}
			selected[i / 5][i % 5] = false; // 선택한 학생 해제
		}
	}

	public static boolean isAdjust() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (selected[i][j]) {
					int cnt = 0; // 한 학생과 인접해있는 이다솜파 학생 수

					Queue<Pos> queue = new LinkedList<>();
					boolean[][] visited = new boolean[5][5];

					queue.add(new Pos(i, j));
					visited[i][j] = true;

					while (!queue.isEmpty()) {
						Pos now = queue.poll();
						cnt++;

						for (int k = 0; k < 4; k++) {
							int nr = now.r + dr[k];
							int nc = now.c + dc[k];

							// 범위를 벗어났거나 이미 방문했다면, 이동하지 않는다.
							if (nr < 0 || nc < 0 || nr >= 5 || nc >= 5 || visited[nr][nc]) {
								continue;
							}

							// 이다솜파 학생이 아닌 경우, 이동하지 않는다.
							if (!selected[nr][nc]) {
								continue;
							}

							queue.add(new Pos(nr, nc));
							visited[nr][nc] = true;
						}

					}

					if (cnt == 7) { // 인접한 이다솜파 학생 수가 7명
						return true;
					}
					return false;
				}
			}
		}

		return false;
	}
}
