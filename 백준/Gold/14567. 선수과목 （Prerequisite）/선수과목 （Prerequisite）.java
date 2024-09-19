// 선수과목 (Prerequisite)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		List<Integer>[] adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}

		int[] in = new int[N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken()); // A는 B의 선수과목
			int B = Integer.parseInt(st.nextToken());
			adjList[A].add(B); // 연결정보 추가
			in[B]++; // 진입차수 증가
		}

		int[] result = new int[N + 1];
		int cnt = 1;

		Queue<Integer> pq = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) { // 진입차수가 0인 정점 추가
			if (in[i] == 0) {
				pq.add(i);
				result[i] = cnt;
			}
		}

		while (!pq.isEmpty()) {
			cnt++;
			int size = pq.size();
			for (int i = 0; i < size; i++) {
				int v = pq.poll();
				for (int u : adjList[v]) {
					in[u]--;
					if (in[u] == 0) { // 진입차수가 0이 된 정점 추가
						pq.add(u);
						if (result[u] == 0) { // 최소 이수학기만 저장
							result[u] = cnt;
						}
					}
				}
			}
		}

		StringBuilder answer = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			answer.append(result[i] + " ");
		}
		System.out.println(answer);

	}

}
