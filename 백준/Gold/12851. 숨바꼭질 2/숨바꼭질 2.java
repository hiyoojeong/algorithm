import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 숨바꼭질
public class Main {

	static class Pos {
		int pos, time;

		public Pos(int pos, int time) {
			this.pos = pos;
			this.time = time;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		// DFS
		Queue<Pos> queue = new LinkedList<>();
		queue.add(new Pos(N, 0));

		// 해당 위치에 도달하기까지 걸린 시간
		int[] time = new int[100001];
		time[N] = 0;

		int minTime = Integer.MAX_VALUE;
		int minCnt = 0;

		while (!queue.isEmpty()) {
			Pos now = queue.poll();

			if (now.time > minTime) {
				break;
			}

			if (now.pos == K) {
				minTime = now.time;
				minCnt++;
			}

			for (int i = 0; i < 3; i++) {
				int nextPos = 0;
				int nextTime = now.time + 1;
				if (i == 0)
					nextPos = now.pos - 1;
				if (i == 1)
					nextPos = now.pos + 1;
				if (i == 2)
					nextPos = now.pos * 2;

				Pos next = new Pos(nextPos, nextTime);
				if (nextPos >= 0 && nextPos <= 100000) {
					// 이전에 해당 위치에 방문한 적 없거나, 이전에 해당 위치에 방문하기까지 걸린 시간과 같거나 더 적은 경우만
					if (time[next.pos] == 0 || time[next.pos] >= next.time) {
						queue.add(next);
						time[next.pos] = next.time;
					}
				}
			}
		}

		System.out.println(minTime);
		System.out.println(minCnt);

	}

}
