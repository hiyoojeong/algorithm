
// 안전 영역

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 입력
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];

		Set<Integer> heights = new HashSet<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				heights.add(map[i][j]);
			}
		}

		// 장마철에 물에 잠기지 않는 안전한 영역의 최대 개수를 저장하는 변수이다.
		int maxCnt = 1;

		// 지역의 높이별로, 그만큼 잠길 수 있다고 가정하여 안전한 영역의 최대 개수를 업데이트해나간다.
		// 그 외에는 의미없는 계산이다.
		for (int height : heights) {
			int cnt = 0; // 안전한 영역의 개수

			int[][] visited = new int[N][N]; // 확인한 영역 체크

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {

					// 안전한 영역을 찾았다면, 그 영역이 어디까지인지 체크한다.
					if (map[i][j] > height && visited[i][j] == 0) {

						cnt++; // 안전한 영역의 개수 증가

						Queue<Pos> queue = new LinkedList<>();
						queue.add(new Pos(i, j));

						visited[i][j] = 1;

						while (!queue.isEmpty()) {
							Pos pos = queue.poll();

							// 인접한 영역을 확인한다.
							for (int k = 0; k < 4; k++) {
								int nx = pos.x + dx[k];
								int ny = pos.y + dy[k];

								// 범위를 넘어간 경우
								if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
									continue;
								}

								// 이미 확인한 영역이거나, 잠긴 영역인 경우
								if (visited[nx][ny] == 1 || map[nx][ny] <= height) {
									continue;
								}

								// 인접한 영역 중에 안전한 영역이므로 Queue에 추가하여 계속 확인해본다.
								queue.add(new Pos(nx, ny));
								visited[nx][ny] = 1;
							}
						}

					}
				}
			}

			maxCnt = Math.max(cnt, maxCnt);
		}

		System.out.println(maxCnt);

	}

}
