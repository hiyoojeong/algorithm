
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 내리막길
public class Main {

	static int M, N;
	static int[][] map;
	static int[][] dp;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); // 세로 = 행
		N = Integer.parseInt(st.nextToken()); // 가로 = 열

		map = new int[M + 1][N + 1];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// dp + dfs
		// dp[i][j] : (i,j)에서 (M, N)까지 가는 경로의 수
		dp = new int[M + 1][N + 1];
		for (int i = 1; i <= M; i++) {
			for (int j = 1; j <= N; j++) {
				dp[i][j] = -1; // -1로 초기화하는 이유 : (i,j)에서 (M,N)까지 가는 경로가 없으면, 0으로 저장되는데 이런 경우도 가지치기 위함이다!
			}
		}

		int result = dfs(1, 1);
		System.out.println(result);
	}

	public static int dfs(int x, int y) {
		if (x == M && y == N) { // 도착지점에 도착했으므로, 해당 경로의 수인 1을 반환
			return 1;
		}

		if (dp[x][y] != -1) { // 이미 해당지점에서 도착지점까지 가는 경로 수가 나와있다면, 해당 값을 반환
			return dp[x][y];
		}

		dp[x][y] = 0; // 해당지점에서 도착지점까지 가는 경로 수를 구하기 위해, 0으로 초기화

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			// 범위를 벗어난 곳은 갈 수 없다.
			if (nx <= 0 || ny <= 0 || nx > M || ny > N) {
				continue;
			}

			// 높이가 더 높거나 같은 지점으로 이동할 수 없다.
			if (map[x][y] <= map[nx][ny]) {
				continue;
			}

			dp[x][y] += dfs(nx, ny);
		}

		return dp[x][y]; // 해당지점에서 도착지점까지 가는 경로 수를 구한 뒤, 해당 값을 반환
	}

}
