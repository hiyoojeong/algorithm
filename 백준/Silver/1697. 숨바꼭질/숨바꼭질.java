import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

// 숨바꼭질
public class Main {

	static class Pos {
		int pos, cnt;

		public Pos(int pos, int cnt) {
			this.pos = pos;
			this.cnt = cnt;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		System.out.println(dfs(N, K));
	}

	public static int dfs(int start, int end) {
		Queue<Pos> queue = new LinkedList<>();
		queue.add(new Pos(start, 0));

		Set<Integer> visited = new HashSet<>();
		visited.add(start);

		while (!queue.isEmpty()) {
			Pos now = queue.poll();

			if (now.pos == end) {
				return now.cnt;
			}

			for (int i = 0; i < 3; i++) {
				int next = 0;
				if (i == 0)
					next = now.pos - 1;
				if (i == 1)
					next = now.pos + 1;
				if (i == 2)
					next = now.pos * 2;

				if (next >= 0 && next <= 100000 && !visited.contains(next)) {
					queue.add(new Pos(next, now.cnt + 1));
					visited.add(next);
				}
			}
		}

		return 0;
	}

}
