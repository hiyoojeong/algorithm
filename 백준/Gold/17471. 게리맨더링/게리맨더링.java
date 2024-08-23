import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 17471. 게리맨더링
public class Main {

	static int N, people[];
	static List<Integer>[] adjList;

	static final int A = 0, B = 1;
	static int[] team;
	static boolean isVisited[];

	static int totalCnt, minDist = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer answer = new StringBuffer();

		N = Integer.parseInt(br.readLine());
		people = new int[N];
		team = new int[N];
		isVisited = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
			totalCnt += people[i];
		}

		adjList = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			for (int j = 0; j < M; j++) {
				int adj = Integer.parseInt(st.nextToken()) - 1;
				adjList[i].add(adj);
				adjList[adj].add(i);
			}
		}

		dfs(0, 0, 0);

		System.out.println(minDist == Integer.MAX_VALUE ? -1 : minDist);
	}

	public static void dfs(int cnt, int ACnt, int BCnt) {
		if (cnt == N) {
			// 각 선거구는 적어도 하나의 구역을 포함해야 한다.
			if (ACnt == totalCnt || BCnt == totalCnt) {
				return;
			}

			// 구역끼리 연결되어 있는지 확인한다.
			for (int i = 0; i < N; i++) { // A 구역 인구 체크
				if (team[i] == A) {
					if (checkAdj(i, A) != ACnt) {
						return;
					}
					break;
				}
			}
			for (int i = 0; i < N; i++) { // B 구역 인구 체크
				if (team[i] == B) {
					if (checkAdj(i, B) != BCnt) {
						return;
					}
					break;
				}
			}

			// 두 선거구의 인구 차이의 최솟값 업데이트
			minDist = Math.min(Math.abs(ACnt - BCnt), minDist);
			return;
		}

		// 구역 A로 배정
		team[cnt] = A;
		dfs(cnt + 1, ACnt + people[cnt], BCnt);

		// 구역 B로 배정
		team[cnt] = B;
		dfs(cnt + 1, ACnt, BCnt + people[cnt]);

	}

	public static int checkAdj(int idx, int teamType) {
		Queue<Integer> q = new LinkedList<>();
		q.add(idx);

		boolean[] isVisited = new boolean[N];
		isVisited[idx] = true;

		int cnt = 0;
		while (!q.isEmpty()) {
			int now = q.poll();
			cnt += people[now];

			for (int adj : adjList[now]) {
				if (isVisited[adj] || team[adj] != teamType) {
					continue;
				}
				isVisited[adj] = true;
				q.add(adj);
			}
		}

		return cnt;
	}

}
