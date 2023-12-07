

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 단어 찾기
public class No15705 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String srcStr = br.readLine(); // 찾으려는 문자
		int srcStrLen = srcStr.length();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 행
		int M = Integer.parseInt(st.nextToken()); // 열

		// 표 저장
		char[][] table = new char[N][M]; // 주어진 표
		for (int i = 0; i < N; i++) {
			String chars = br.readLine();
			for (int j = 0; j < M; j++) {
				table[i][j] = chars.charAt(j);
			}
		}

		// 이동 방향
		// 위, 아래, 왼쪽, 오른쪽, 왼쪽 위, 오른쪽 위, 왼쪽 아래, 오른쪽 아래
		int[] mr = new int[] { -1, 1, 0, 0, -1, -1, 1, 1 };
		int[] mc = new int[] { 0, 0, -1, 1, -1, 1, -1, 1 };

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {

				// 찾으려는 문자의 첫글자와 일치한다면
				if (table[r][c] == srcStr.charAt(0)) {

					// 8방향으로 이동하며 나머지 알파벳 확인하기
					for (int dir = 0; dir < 8; dir++) {

						boolean check = true;
						int nextr = r;
						int nextc = c;
						
						for (int i = 1; i < srcStrLen; i++) {
							nextr = nextr + mr[dir];
							nextc = nextc + mc[dir];

							// 범위를 벗어남
							if (nextr < 0 || nextc < 0 || nextr >= N || nextc >= M) {
								check = false;
								continue;
							}

							// 일치하지 않음
							if (table[nextr][nextc] != srcStr.charAt(i)) {
								check = false;
								continue;
							}
						}
						
						if (check) {
							System.out.println(1);
							return;
						}

					}

				}

			}
		}

		System.out.println(0);

	}

}
