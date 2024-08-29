
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

// 탈출
class Main {
	static class Pos {
		int x, y, cnt;

		public Pos(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static final int BLANK = 0, WATER = 1, ROCK = 2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		Queue<Pos> queue;
		int[][] waterTime;
		boolean[][] visited;

		// 입력
		st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		int[][] map = new int[R][C];
		Pos start = null;
		Pos end = null;

		queue = new ArrayDeque<>();
		visited = new boolean[R][C];
		waterTime = new int[R][C];

		for (int i = 0; i < R; i++) {
			String input = br.readLine();
			for (int j = 0; j < C; j++) {
				char type = input.charAt(j);
				if (type == '*') { // 물
					map[i][j] = WATER;
					queue.add(new Pos(i, j, 1));
					visited[i][j] = true;
					waterTime[i][j] = 1; // 물이 도착하는 시간이 처음에 1로 설정한다.
				} else if (type == 'X') { // 돌
					map[i][j] = ROCK;
				} else { // 빈칸
					map[i][j] = BLANK;
					waterTime[i][j] = Integer.MAX_VALUE; // 우선빈칸은 물이 도착하는 시간을 무한대로 둔다.
				}

				if (type == 'S') {
					start = new Pos(i, j, 1);
				} else if (type == 'D') {
					end = new Pos(i, j, 1);
				}
			}
		}

		// dfs - 물이 차는 시간을 표시
		while (!queue.isEmpty()) {
			Pos now = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				int ncnt = now.cnt + 1;

				// 범위를 벗어나거나, 이미 방문한 곳(물이 벌써 찼던 곳)
				if (nx < 0 || ny < 0 || nx >= R || ny >= C || visited[nx][ny]) {
					continue;
				}

				// 물은 돌을 통과할 수 없고, 비버의 소굴로 들어갈 수 없다.
				if (map[nx][ny] == ROCK || (nx == end.x && ny == end.y)) {
					continue;
				}

				queue.add(new Pos(nx, ny, ncnt));
				visited[nx][ny] = true;

				waterTime[nx][ny] = ncnt;
			}
		}

		// dfs - 고슴도치 이동
		queue = new ArrayDeque<>();
		visited = new boolean[R][C];

		queue.add(start);
		visited[start.x][start.y] = true;

		int answer = -1;
		while (!queue.isEmpty()) {
			Pos now = queue.poll();

			// 비버의 굴에 도착!
			if (now.x == end.x && now.y == end.y) {
				answer = now.cnt - 1; // 처음 시간을 1로 설정했기 때문에, -1 해줘야 한다.
				break;
			}

			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				int ncnt = now.cnt + 1;

				// 범위를 벗어났거나, 이미 방문한 곳(고슴도치가 방문했던 곳)
				if (nx < 0 || ny < 0 || nx >= R || ny >= C || visited[nx][ny]) {
					continue;
				}

				// 고슴도치는 돌을 통과할 수 없고, 물이 찰 예전인 칸으로 이동할 수 없다.
				if (map[nx][ny] == ROCK || ncnt >= waterTime[nx][ny]) {
					continue;
				}

				queue.add(new Pos(nx, ny, ncnt));
				waterTime[nx][ny] = ncnt;
			}
		}

		System.out.println(answer == -1 ? "KAKTUS" : answer);
	}
}