import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 외판원 순회 3
public class Main {

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public double distance(Pos pos) {
			return Math.sqrt(Math.pow(pos.x - this.x, 2) + Math.pow(pos.y - this.y, 2));
		}
	}

	static int N;
	static Pos[] cities;
	static double[][] W;
	static double[][] dp;

	static final double INF = Double.MAX_VALUE / 2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		cities = new Pos[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			cities[i] = new Pos(x, y);
		}

		W = new double[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				double cost = cities[i].distance(cities[j]);
				W[i][j] = cost;
				W[j][i] = cost;
			}
		}

		dp = new double[N][1 << N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], -1.0);
		}

		System.out.println(tsp(0, 1));
	}

	public static double tsp(int here, int visited) {
		if (dp[here][visited] != -1) {
			return dp[here][visited];
		}

		if (visited == (1 << N) - 1) {
			if (W[here][0] == 0) {
				return INF;
			}
			return W[here][0];
		}

		dp[here][visited] = INF;

		for (int there = 0; there < N; there++) {
			if ((visited & (1 << there)) == 0 && W[here][there] != 0) {
				dp[here][visited] = Math.min(dp[here][visited], W[here][there] + tsp(there, visited | (1 << there)));
			}
		}

		return dp[here][visited];
	}

}
