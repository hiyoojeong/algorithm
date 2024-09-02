import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt();
		int N = sc.nextInt();
		int K = sc.nextInt();

		int[][] map = new int[N][M];

		for (int i = 0; i < K; i++) {
			int lr = sc.nextInt();
			int lc = sc.nextInt();
			int rr = sc.nextInt();
			int rc = sc.nextInt();
			for (int j = lr; j < rr; j++) {
				for (int k = lc; k < rc; k++) {
					map[j][k] = 1;
				}
			}
		}
		sc.close();

		boolean[][] visited = new boolean[N][M];
		Queue<Pos> q = new ArrayDeque<>();

		Queue<Integer> areas = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0 && !visited[i][j]) {
					int area = 0;

					q.add(new Pos(i, j));
					visited[i][j] = true;

					while (!q.isEmpty()) {
						Pos now = q.poll();
						area++;

						for (int k = 0; k < 4; k++) {
							int nr = now.r + dr[k];
							int nc = now.c + dc[k];

							if (nr < 0 || nc < 0 || nr >= N || nc >= M || visited[nr][nc]) {
								continue;
							}

							if (map[nr][nc] == 1) {
								continue;
							}

							q.add(new Pos(nr, nc));
							visited[nr][nc] = true;
						}
					}

					areas.add(area);
				}
			}
		}

		System.out.println(areas.size());
		while (!areas.isEmpty()) {
			System.out.print(areas.poll() + " ");
		}
	}

}
