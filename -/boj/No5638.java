

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.util.Arrays;
//import java.util.Comparator;
import java.util.StringTokenizer;

/*
4
720000 120000
50000 60000
130000 50000
1200000 150000
3
5000000 7
5000000 30
63000000 24

Case 1: 120000
Case 2: 110000
Case 3: IMPOSSIBLE
*/

// 수문
public class No5638 {

	static int N;
	static int[][] gates;

	static int minCost;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer sb = new StringBuffer();

		N = Integer.parseInt(br.readLine()); // 수문 개수
		gates = new int[N][2]; // 수문 정보

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int flow = Integer.parseInt(st.nextToken()); // 유량
			int cost = Integer.parseInt(st.nextToken()); // 비용

			gates[i][0] = flow;
			gates[i][1] = cost;
		}

//		Arrays.sort(gates, new Comparator<int[]>() {
//
//			@Override
//			public int compare(int[] o1, int[] o2) {
//				return o1[1] - o2[1];
//			}
//		});

		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수
		for (int i = 1; i <= T; i++) {
			st = new StringTokenizer(br.readLine());
			int volume = Integer.parseInt(st.nextToken()); // 비워야 하는 물의 양
			int time = Integer.parseInt(st.nextToken()); // 비워내야 하는 시간 제한

			minCost = Integer.MAX_VALUE;
			for (int r = 1; r <= N; r++) {
				combination(volume, time, new boolean[N], 0, r);
			}

			if (minCost == Integer.MAX_VALUE) {
				sb.append("Case " + i + ": IMPOSSIBLE\n");
			} else {
				sb.append("Case " + i + ": " + minCost + "\n");
			}
		}

		System.out.println(sb);

	}

	public static void combination(int volume, int time, boolean[] visited, int start, int r) {
		if (r == 0) {
			int flow = 0;
			int cost = 0;

			for (int i = 0; i < N; i++) {
				if (visited[i]) {
					flow += gates[i][0];
					cost += gates[i][1];
				}
			}

			if (Math.ceil(volume / flow) < time) {
				if (minCost > cost) {
					minCost = cost;
				}
			}

			return;
		}

		for (int i = start; i < N; i++) {
			visited[i] = true;
			combination(volume, time, visited, i + 1, r - 1);
			visited[i] = false;
		}

	}

}
