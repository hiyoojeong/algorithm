
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

// 탈주범 검거
public class Solution {

	static class Pos {
		int r, c, l;

		public Pos(int r, int c, int l) {
			this.r = r;
			this.c = c;
			this.l = l;
		}
	}

	static int N, M, R, C, L, map[][];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuffer answer = new StringBuffer();

		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt();
			M = sc.nextInt();
			R = sc.nextInt(); // 맨홀 위치
			C = sc.nextInt(); // 맨홀 위치
			L = sc.nextInt(); // 탈출 후 소요된 시간

			map = new int[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			answer.append(String.format("#%d %d\n", test_case, bfs()));
		}

		System.out.println(answer);
		sc.close();
	}

	public static int bfs() {
		Queue<Pos> q = new ArrayDeque<>();
		q.add(new Pos(R, C, 0));

		boolean[][] visited = new boolean[N][M];
		visited[R][C] = true;

		int cnt = 0; // 탈주범이 위치할 수 있는 장소의 개수
		while (!q.isEmpty()) {
			Pos now = q.poll();

			if (now.l == L) { // 탈출 후 L시간이 지난 경우
				continue;
			}
			
			cnt++;

			if (up(now.r, now.c, visited)) {
				q.add(new Pos(now.r - 1, now.c, now.l + 1));
				visited[now.r - 1][now.c] = true;
			}
			if (down(now.r, now.c, visited)) {
				q.add(new Pos(now.r + 1, now.c, now.l + 1));
				visited[now.r + 1][now.c] = true;
			}
			if (left(now.r, now.c, visited)) {
				q.add(new Pos(now.r, now.c - 1, now.l + 1));
				visited[now.r][now.c - 1] = true;
			}
			if (right(now.r, now.c, visited)) {
				q.add(new Pos(now.r, now.c + 1, now.l + 1));
				visited[now.r][now.c + 1] = true;
			}
		}

		return cnt;
	}

	public static boolean up(int r, int c, boolean[][] visited) {
		if (map[r][c] == 1 || map[r][c] == 2 || map[r][c] == 4 || map[r][c] == 7) {
			if (r - 1 >= 0 && !visited[r - 1][c]) {
				if (map[r - 1][c] == 1 || map[r - 1][c] == 2 || map[r - 1][c] == 5 || map[r - 1][c] == 6) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean down(int r, int c, boolean[][] visited) {
		if (map[r][c] == 1 || map[r][c] == 2 || map[r][c] == 5 || map[r][c] == 6) {
			if (r + 1 < N && !visited[r + 1][c]) {
				if (map[r + 1][c] == 1 || map[r + 1][c] == 2 || map[r + 1][c] == 4 || map[r + 1][c] == 7) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean left(int r, int c, boolean[][] visited) {
		if (map[r][c] == 1 || map[r][c] == 3 || map[r][c] == 6 || map[r][c] == 7) {
			if (c - 1 >= 0 && !visited[r][c - 1]) {
				if (map[r][c - 1] == 1 || map[r][c - 1] == 3 || map[r][c - 1] == 4 || map[r][c - 1] == 5) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean right(int r, int c, boolean[][] visited) {
		if (map[r][c] == 1 || map[r][c] == 3 || map[r][c] == 4 || map[r][c] == 5) {
			if (c + 1 < M && !visited[r][c + 1]) {
				if (map[r][c + 1] == 1 || map[r][c + 1] == 3 || map[r][c + 1] == 6 || map[r][c + 1] == 7) {
					return true;
				}
			}
		}
		return false;
	}
}
