import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 맥주 마시면서 걸어가기
public class Main {

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static final int INF = Integer.MAX_VALUE / 2;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer answer = new StringBuffer();

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());

			List<Pos> list = new ArrayList<>();
			for (int i = 0; i <= N + 1; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				list.add(new Pos(x, y));
			}

			int[][] dist = new int[N + 2][N + 2];
			for (int i = 0; i <= N; i++) {
				for (int j = 1; j <= N + 1; j++) {
					int d = Math.abs(list.get(i).x - list.get(j).x) + Math.abs(list.get(i).y - list.get(j).y);
					if (d > 1000) {
						d = INF;
					}
					dist[i][j] = d;
					dist[j][i] = d;
				}
			}

			for (int k = 0; k <= N + 1; k++) {
				for (int i = 0; i <= N + 1; i++) {
					for (int j = 0; j <= N + 1; j++) {
						dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
					}
				}
			}

			if (dist[0][N + 1] >= INF) {
				answer.append("sad\n");
			} else {
				answer.append("happy\n");
			}
		}

		System.out.println(answer);
	}

}
