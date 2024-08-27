
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

// 숨바꼭질
class Main {

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
		int N = Integer.parseInt(st.nextToken()); // 시작 위치
		int K = Integer.parseInt(st.nextToken()); // 도착 위치

		Queue<Pos> q = new LinkedList<>();
		boolean[] visited = new boolean[100_001];

		q.add(new Pos(N, 0));
		visited[N] = true;

		int answer = 0;
		while (!q.isEmpty()) {
			Pos now = q.poll();

			if (now.pos == K) {
				answer = now.cnt;
				break;
			}

			int npos = now.pos + 1;
			if (npos <= 100_000 && !visited[npos]) {
				q.add(new Pos(npos, now.cnt + 1));
				visited[npos] = true;
			}

			npos = now.pos - 1;
			if (npos >= 0 && !visited[npos]) {
				q.add(new Pos(npos, now.cnt + 1));
				visited[npos] = true;
			}

			npos = now.pos << 1;
			if (npos <= 100_000 && !visited[npos]) {
				q.add(new Pos(npos, now.cnt + 1));
				visited[npos] = true;
			}
		}

		System.out.println(answer);
	}
}