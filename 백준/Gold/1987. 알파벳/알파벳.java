
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 알파벳
class Main {

	static int R, C;
	static int[][] map;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static int maxCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); // 세로
		C = Integer.parseInt(st.nextToken()); // 가로

		map = new int[R][C];
		for (int i = 0; i < R; i++) {
			String input = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = input.charAt(j) - 'A';
			}
		}

		boolean[] visited = new boolean[26];
		visited[map[0][0]] = true;
		dfs(0, 0, 1, visited);

		System.out.println(maxCnt);
	}

	public static void dfs(int x, int y, int cnt, boolean[] visited) {
		if (cnt > maxCnt) {
			maxCnt = cnt;
		}

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || ny < 0 || nx >= R || ny >= C) {
				continue;
			}

			if (visited[map[nx][ny]]) {
				continue;
			}

			visited[map[nx][ny]] = true;
			dfs(nx, ny, cnt + 1, visited);
			visited[map[nx][ny]] = false;
		}
	}

}