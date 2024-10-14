
// 줄기세포배양
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

class Solution {
	static class Pos {
		int r, c, time, originTime;

		public Pos(int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
			this.originTime = time;
		}

		private void resetTime() {
			time = originTime;
		}

		@Override
		public boolean equals(Object obj) {
			Pos pos = (Pos) obj;
			return this.r == pos.r && this.c == pos.c;
		}

		@Override
		public int hashCode() {
			return Objects.hash(r, c);
		}
	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static Set<Pos> cell;
	static Queue<Pos> nonActive, active;

	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		StringBuffer answer = new StringBuffer();
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			cell = new HashSet<>();
			nonActive = new ArrayDeque<>();
			active = new ArrayDeque<>();

			int N = sc.nextInt(); // 초기 상태에서 줄기세포가 분포된 세로크기
			int M = sc.nextInt(); // 가로크기
			int K = sc.nextInt(); // 배양시간

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					int time = sc.nextInt();
					if (time == 0) {
						continue;
					}
					cell.add(new Pos(i, j, time));
					nonActive.add(new Pos(i, j, time));
				}
			}

			for (int time = 1; time <= K; time++) {
				// 활성 상태
				int size = active.size();
				Queue<Pos> nexts = new ArrayDeque<>();
				for (int i = 0; i < size; i++) {
					// 생명력 수치가 1 감소한다.
					Pos now = active.poll();
					now.time--;

					// 주변 셀에 번식할 수 있으면 번식한다.
					for (int j = 0; j < 4; j++) {
						int nr = now.r + dr[j];
						int nc = now.c + dc[j];

						Pos next = new Pos(nr, nc, now.originTime);
						if (!cell.contains(next)) {
							cell.add(next);
							nexts.add(next); // 비활성 상태 세포로 바로저장하지 않고 임시저장한다.
						}
					}

					// 아직 생명령 수치가 남아있으면 다시 큐에 넣는다.
					if (now.time > 0) {
						active.add(now);
					}
				}

				// 비활성 상태
				size = nonActive.size();
				for (int i = 0; i < size; i++) {
					// 생명력 수지가 1 감소한다.
					Pos now = nonActive.poll();
					now.time--;

					// 생명력 수치가 0이라면 활성 상태가 된다.
					if (now.time == 0) {
						now.resetTime();
						active.add(now);
					}
					// 아직 생명력 수치가 남아있으면 다시 큐에 넣는다.
					else if (now.time > 0) {
						nonActive.add(now);
					}
				}

				// 활성 상태 세포에서 번식된 비활성 상태 세포
				while (!nexts.isEmpty()) {
					Pos next = nexts.poll();
					nonActive.add(next);
				}
			}

			int cnt = nonActive.size() + active.size();
			answer.append(String.format("#%d %d\n", test_case, cnt));
		}
		System.out.println(answer);
		sc.close();
	}
}