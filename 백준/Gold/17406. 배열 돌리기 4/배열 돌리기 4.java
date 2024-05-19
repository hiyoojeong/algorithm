import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 배열 돌리기 4
public class Main {

	static class Method {
		int r, c, s;

		public Method(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}

	static int N, M, K;
	static int[][] originalMap;
	static List<Method> methods;

	static int answer = Integer.MAX_VALUE;

	// 우, 하, 좌, 상 순서
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		originalMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				originalMap[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		methods = new ArrayList<>();
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			methods.add(new Method(r, c, s));
		}

		combination(K, new ArrayList<>());

		System.out.println(answer);
	}

	public static void combination(int n, List<Integer> list) {
		if (list.size() == n) {
			int[][] map = copyMap();

			// 조합된 순서대로 map을 회전한다.
			for (Integer order : list) {
				Method method = methods.get(order);
				rotate(map, method.r, method.c, method.s);
			}

			// 각 행에 있는 모든 수의 합 중 최솟값을 계산하여 업데이트한다.
			int minSum = getMinSumOfRow(map);
			answer = Math.min(answer, minSum);
		}

		for (int i = 0; i < n; i++) {
			if (!list.contains(i)) {
				list.add(i);
				combination(n, list);
				list.remove(list.size() - 1);
			}
		}

	}

	public static void rotate(int[][] map, int r, int c, int s) {
		int[][] tmp = new int[N][M];

		// 회전 후 정보를 tmp 배열에 저장한다.
		for (int i = 1; i <= s; i++) {
			int nowr = r - i;
			int nowc = c - i;

			for (int d = 0; d < 4; d++) {
				for (int j = 0; j < i * 2; j++) {
					int nextr = nowr + dr[d];
					int nextc = nowc + dc[d];

					tmp[nextr][nextc] = map[nowr][nowc];

					nowr = nextr;
					nowc = nextc;
				}
			}
		}

		// tmp 데이터를 map 데이터에 저장한다.
		for (int i = r - s; i <= r + s; i++) {
			for (int j = c - s; j <= c + s; j++) {
				if (i == r && j == c) {
					continue;
				}
				map[i][j] = tmp[i][j];
			}
		}

	}

	public static int getMinSumOfRow(int[][] map) {
		int minSum = Integer.MAX_VALUE, sum;

		for (int i = 0; i < N; i++) {
			sum = 0;

			for (int j = 0; j < M; j++) {
				sum += map[i][j];
			}

			minSum = Math.min(sum, minSum);
		}

		return minSum;
	}

	public static int[][] copyMap() {
		int[][] tmp = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tmp[i][j] = originalMap[i][j];
			}
		}

		return tmp;
	}

}
