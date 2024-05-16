import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int n;
	static char[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 데이터를 저장한다.
		n = Integer.parseInt(br.readLine());
		map = new char[n][n];
		for (int i = 0; i < n; i++) {
			String input = br.readLine();
			for (int j = 0; j < n; j++) {
				map[i][j] = input.charAt(j);
			}
		}

		// 모두 같은 색으로 이루어져 있는 가장 긴 연속 부분의 길이를 찾는다.
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// 오른쪽과 바꾼다.
				changeRight(i, j);
				cnt = Math.max(cnt, getSerializedCnt());
				changeRight(i, j);

				// 아래쪽과 바꾼다.
				changeDown(i, j);
				cnt = Math.max(cnt, getSerializedCnt());
				changeDown(i, j);
			}
		}

		System.out.println(cnt);

	}

	public static void changeRight(int i, int j) {
		if (j == n - 1) {
			return;
		}

		char tmp = map[i][j];
		map[i][j] = map[i][j + 1];
		map[i][j + 1] = tmp;
	}

	public static void changeDown(int i, int j) {
		if (i == n - 1) {
			return;
		}

		char tmp = map[i][j];
		map[i][j] = map[i + 1][j];
		map[i + 1][j] = tmp;
	}

	public static int getSerializedCnt() {
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				char ch = map[i][j];
				int rightCnt = 0, downCnt = 0;

				// 오른쪽 체크
				for (int k = j; k < n; k++) {
					if (map[i][k] == ch) {
						rightCnt++;
					} else {
						break;
					}
				}

				// 아래쪽 체크
				for (int k = i; k < n; k++) {
					if (map[k][j] == ch) {
						downCnt++;
					} else {
						break;
					}
				}

				cnt = Math.max(Math.max(rightCnt, downCnt), cnt);
			}
		}

		return cnt;
	}

}
