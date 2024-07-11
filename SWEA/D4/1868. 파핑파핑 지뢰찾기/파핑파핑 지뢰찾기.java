import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 파핑파핑 지뢰찾기
public class Solution {

	static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static int N;
	static char[][] map;
	static int[][] visited;

	static int[] dx = { -1, 1, 0, 0, -1, -1, 1, 1 };
	static int[] dy = { 0, 0, -1, 1, -1, 1, -1, 1 };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			visited = new int[N][N];

			for (int i = 0; i < N; i++) {
				String input = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = input.charAt(j);
				}
			}

			int[][] bombs = getBombs();

			int answer = dfs_click(bombs);
			answer += click();

			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}

		System.out.println(sb);
	}

	public static int[][] getBombs() {
		// 주변에 있는 지뢰의 개수를 저장한다 
		int[][] bombs = new int[N][N];
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				if (map[x][y] == '*') {
					bombs[x][y] = -1;
				}
				if (map[x][y] == '.') {
					int sum = 0;
					for (int d = 0; d < 8; d++) {
						int nx = x + dx[d];
						int ny = y + dy[d];
						if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
							if (map[nx][ny] == '*') {
								sum++;
							}
						}
					}
					bombs[x][y] = sum;
				}
			}
		}

		return bombs;
	}

	public static int dfs_click(int[][] bombs) {
		int cnt = 0;

		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {

				// 아직 방문하지 않은 주변에 지뢰가 0인 칸을 클릭한다.
				if (bombs[x][y] == 0 && visited[x][y] == 0) {
					cnt++;

					visited[x][y] = 1;

					Queue<Node> queue = new LinkedList<>();
					queue.add(new Node(x, y));

					while (!queue.isEmpty()) {
						Node now = queue.poll();

						if (bombs[now.x][now.y] != 0) {
							continue;
						}
						
						// 8방향을 확인하면서, 아직 방문하지 않은 주변에 지뢰가 0인 칸으로 이동한다.
						for (int d = 0; d < 8; d++) {
							int nx = now.x + dx[d];
							int ny = now.y + dy[d];
							if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
								if (visited[nx][ny] == 0) {
									queue.add(new Node(nx, ny));
									visited[nx][ny] = 1;
								}
							}
						}

					}
				}

			}
		}

		return cnt;
	}

	public static int click() {
		int cnt = 0;
		
		// 아직 방분하지 않은 지뢰가 없는 칸을 하나씩 클릭한다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == '.' && visited[i][j] == 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}

}
