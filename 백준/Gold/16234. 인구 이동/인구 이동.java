import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 인구 이동
public class Main {

	static class Country {
		int r, c;

		public Country(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[] mr = { -1, 1, 0, 0 };
		int[] mc = { 0, 0, -1, 1 };

		int answer = 0;
		while (true) {
			boolean[][] visited = new boolean[N][N];

			boolean isMove = false;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {

						// unionNum번 연합 나라 정보를 저장한다.
						List<Country> union = new ArrayList<>();
						union.add(new Country(i, j));

						Queue<Country> queue = new LinkedList<>();
						queue.add(new Country(i, j));

						visited[i][j] = true;

						int sum = map[i][j];

						while (!queue.isEmpty()) {
							Country now = queue.poll();

							for (int m = 0; m < 4; m++) {
								int nextr = now.r + mr[m];
								int nextc = now.c + mc[m];

								if (nextr >= 0 && nextr < N && nextc >= 0 && nextc < N) {
									int distance = Math.abs(map[now.r][now.c] - map[nextr][nextc]);
									if (!visited[nextr][nextc] && distance >= L && distance <= R) {
										union.add(new Country(nextr, nextc));
										queue.add(new Country(nextr, nextc));
										visited[nextr][nextc] = true;

										sum += map[nextr][nextc];

										isMove = true;
									}

								}
							}
						}

						// unionNum번째 연합 나라들 간 인구 이동을 한다.
						for (Country country : union) {
							map[country.r][country.c] = sum / union.size();
						}
					}
				}
			}

			if (isMove) {
				answer++;
			} else {
				break;
			}

		}

		System.out.println(answer);

	}

}
