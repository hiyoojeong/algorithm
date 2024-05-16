import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer answer = new StringBuffer();

		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int min = Integer.MAX_VALUE;

			int[][] presents = new int[3][2];
			for (int j = 0; j < 3; j++) {
				st = new StringTokenizer(br.readLine());
				presents[j][0] = Integer.parseInt(st.nextToken());
				presents[j][1] = Integer.parseInt(st.nextToken());

			}

			for (int a = 0; a < 2; a++) {
				for (int b = 0; b < 2; b++) {
					for (int c = 0; c < 2; c++) {
						int row = 0; // 가로
						int column = 0; // 세로
						int value = 0;

						// 나란히 있을 때
						row = Math.max(Math.max(presents[0][a], presents[1][b]), presents[2][c]);
						column = presents[0][(a + 1) % 2] + presents[1][(b + 1) % 2] + presents[2][(c + 1) % 2];
						value = row * column;
						if (min > value) {
							min = value;
						}

						// 두개 - 한개 있을 때
						for (int d = 0; d < 3; d++) {
							row = Math.max(presents[d][a], presents[(d + 1) % 3][b] + presents[(d + 2) % 3][c]);
							column = presents[d][(a + 1) % 2]
									+ Math.max(presents[(d + 1) % 3][(b + 1) % 2], presents[(d + 2) % 3][(c + 1) % 2]);
							value = row * column;
							if (min > value) {
								min = value;
							}
						}
					}
				}
			}
			
			answer.append(min + "\n");
		}
		System.out.println(answer);
	}

}
