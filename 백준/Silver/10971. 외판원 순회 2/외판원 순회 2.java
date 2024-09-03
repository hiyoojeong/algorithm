
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 외판원 순회 2
public class Main {

	static class Node {
		int v, cost;

		public Node(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}
	}

	static int N, costs[][];
	static boolean visited[];

	static int minCost;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		costs = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				costs[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		minCost = Integer.MAX_VALUE;
		visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			visited[i] = true;
			dfs(i, i, 0, 0); // i에서 출발해서 i로 도착
		}

		System.out.println(minCost);
	}

	public static void dfs(int s, int e, int cnt, int cost) {
		if (cnt == N - 1) {
			if (costs[s][e] != 0) {
				cost += costs[s][e];
				if (minCost > cost) {
					minCost = cost;
				}
			}
			return;
		}

		for (int i = 0; i < N; i++) {
			if (visited[i] || costs[s][i] == 0) { // 한 번 갔던 도시나 길이 없는 경우 갈 수 없다.
				continue;
			}
			visited[i] = true;
			dfs(i, e, cnt + 1, cost + costs[s][i]);
			visited[i] = false;
		}
	}

}
