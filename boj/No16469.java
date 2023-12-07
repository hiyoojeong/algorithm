

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No16469 {

	static int N, M;
	static int[][] move = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	static class Villain {
		int x, y, time;
		boolean[][] visit;

		public Villain(int x, int y, int time, boolean[][] visit) {
			this.x = x;
			this.y = y;
			this.time = time;
			this.visit = visit;
		}
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 맵 정보
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				int value = Character.getNumericValue(str.charAt(j));
				map[i][j] = (value == 1) ? -1 : value;
			}
		}

		// 악당 정보
		Queue<Villain> queue = new LinkedList<>();
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			map[x][y] = 1;

			boolean[][] visit = new boolean[N][M];
			visit[x][y] = true;
			queue.add(new Villain(x, y, 0, visit));
		}

		// 악당 세명이 모이는 최소 시간 구하기
		int minTime = Integer.MAX_VALUE;
		int cnt = 0;
		while (!queue.isEmpty()) {
			// 악당 위치 가져오기
			Villain villain = queue.poll();

			// 악당 이동시키기
			for (int i = 0; i < 4; i++) {
				int nextx = villain.x + move[i][0];
				int nexty = villain.y + move[i][1];
				int nextTime = villain.time + 1;

				if (range(nextx, nexty) && map[nextx][nexty] != -1 && !villain.visit[nextx][nexty]) {
					map[nextx][nexty]++;
					villain.visit[nextx][nexty] = true;

					queue.add(new Villain(nextx, nexty, nextTime, villain.visit));

					// 이동하게 될 위치에, 악당 세명이 모두 도착한 경우
					if (map[nextx][nexty] == 3) {
						if (minTime == Integer.MAX_VALUE) {
							minTime = nextTime;
						}
						if (minTime == nextTime) {
							cnt++;
						}
					}

					if (nextTime > minTime) {
						System.out.println(minTime);
						System.out.println(cnt);
						return;
					}

				}
			}

		}

		System.out.println("-1");
	}

	public static boolean range(int nextx, int nexty) {
		return nextx >= 0 && nexty >= 0 && nextx < N && nexty < M;
	}
}
