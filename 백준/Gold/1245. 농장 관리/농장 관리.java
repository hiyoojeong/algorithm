import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int answer = 0;
		int[][] move = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }, { 1, -1 }, { 1, 1 }, { -1, -1 }, { -1, 1 } };

		boolean[][] totVisited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				boolean[][] visited = new boolean[N][M];
				visited[i][j] = true;

				Queue<Pos> queue = new LinkedList<>();
				queue.add(new Pos(i, j));

				boolean check = true;
				while (!queue.isEmpty()) {
					Pos pos = queue.poll();

					check = true;
					for (int m = 0; m < 8; m++) {
						int nextx = pos.x + move[m][0];
						int nexty = pos.y + move[m][1];

						if (range(N, M, nextx, nexty)) {
							if (map[pos.x][pos.y] < map[nextx][nexty]) {
								check = false;
								break;
							} else if (!visited[nextx][nexty] && map[pos.x][pos.y] == map[nextx][nexty]) {
								queue.add(new Pos(nextx, nexty));
								visited[nextx][nexty] = true;
								totVisited[nextx][nexty] = true;
							}
						}
					}
					if (!check) {
						break;
					}

				}

				if (check && queue.isEmpty() && !totVisited[i][j]) {
//					System.out.println(i + ", " + j + " - " + map[i][j]);
//					System.out.println("봉우리");
					answer++;
				}
			}
		}
		System.out.println(answer);
	}

	public static boolean range(int N, int M, int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < M;
	}

}
