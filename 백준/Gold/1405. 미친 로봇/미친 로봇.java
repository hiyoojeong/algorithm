
// 미친 로봇

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object obj) {
			Pos pos = (Pos) obj;
			return this.x == pos.x && this.y == pos.y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}

	}

	// 동 서 남 북
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	static int N;
	static double[] probabilities;
	static Set<Pos> visited;

	static double answer = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		probabilities = new double[4];
		visited = new HashSet<>();

		probabilities[0] = Double.parseDouble(st.nextToken()) / 100; // 동쪽으로 이동할 확률
		probabilities[1] = Double.parseDouble(st.nextToken()) / 100; // 서쪽으로 이동할 확률
		probabilities[2] = Double.parseDouble(st.nextToken()) / 100; // 남쪽으로 이동할 확률
		probabilities[3] = Double.parseDouble(st.nextToken()) / 100; // 북쪽으로 이동할 확률

		dfs(0, 0, 0, 1); // 단순하지 않을 확률을 먼저 구한 뒤, 1에서 해당 확률을 빼준다.

		answer = 1.0 - answer; // '단순할 확률 = 1 - 단순하지 않을 확률'이다.

		System.out.println(answer);
	}

	public static void dfs(int x, int y, int cnt, double probability) {
		if (cnt > N) {
			return;
		}

		// 단순하지 않은 경우라면, 해당 확률을 더해준다.
		if (visited.contains(new Pos(x, y))) {
			answer += probability;
			return;
		}

		visited.add(new Pos(x, y)); // 방문 체크

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			int nc = cnt + 1;
			double np = probability * probabilities[i];

			if (np > 0) {
				dfs(nx, ny, nc, np);
			}
		}

		visited.remove(new Pos(x, y)); // 방문 체크 해제
	}

}
