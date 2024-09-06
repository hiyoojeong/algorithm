import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 메모이제이션을 이용한 성능 최적화
public class Solution {

	static int N, adjMatrix[][], radjMatrix[][], cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer answer = new StringBuffer();
		int TC = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(in.readLine());
			int M = Integer.parseInt(in.readLine());

			adjMatrix = new int[N + 1][N + 1]; // 학생번호 1번부터 시작한다.
			StringTokenizer st = null;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(in.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				adjMatrix[a][b] = 1;
			}

			for (int i = 1; i <= N; i++) {
				adjMatrix[i][0] = -1; // 탐색되지 않은 학생을 나타난다.(후에 탐색이 완료되면 자신보다 큰 학생 수가 저장된다.)
			}

			// 나보다 키가 큰 학생들의 수를 센다.
			// adjMatrix[i][0] : j보다 작은 학생들의 수
			// adjMatrix[i][j] == i이면, j는 i보다 키가 크다는 의미
			for (int i = 1; i <= N; i++) {
				if (adjMatrix[i][0] != -1) { // 자신이 탐색되어 있는 상태면 넘긴다.
					continue;
				}
				gtDFS(i); // 자신보다 키가 큰 학생들을 탐색한다.
			}

			// 나보다 키가 작은 학생들의 수를 센다.
			// adjMatrix[0][i] : i보다 작은 학생들의 수
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					adjMatrix[0][j] += adjMatrix[i][j];
				}
			}

			int ans = 0; // 자신의 키를 알 수 있는 학생 수
			for (int i = 1; i <= N; i++) {
				if (adjMatrix[i][0] + adjMatrix[0][i] == N - 1) {
					ans++;
				}
			}

			answer.append("#" + tc + " " + ans + "\n");
		}
		System.out.println(answer);
	}

	private static void gtDFS(int cur) {
		for (int i = 1; i <= N; i++) {
			// 나와 관계없는 학생은 넘긴다.
			if (adjMatrix[cur][i] == 0) {
				continue;
			}

			// 나보다 키가 큰 i학생을 발견했다.
			// i학생이 탐색되지 않은 상태라면 i학생보다 키가 큰 학생을 탐색한다.
			if (adjMatrix[i][0] == -1) {
				gtDFS(i);
			}

			// 나보다 키가 큰 i학생에 대한 탐색이 완료된 상태이다.
			// i학생보다 키가 큰 학생들은 나보다 키가 큰 것이므로, 나에게도 적용한다.
			if (adjMatrix[i][0] > 0) { // i보다 키가 큰 학생이 존재!! (없어도 되지만, 불필요한 탐색 제거 가능)
				for (int j = 1; j <= N; j++) {
					if (adjMatrix[i][j] == 1) { // i학생보다 키가 큰 j학생을 발견했다. 즉, j학생은 나보다 키가 크다.
						adjMatrix[cur][j] = 1;
					}
				}
			}
		}

		// 나보다 키가 큰 학생들의 수를 센다.
		adjMatrix[cur][0] = 0; // 초기값이 -1이므로 누적하기 위해서 0으로 초기화한다.
		for (int i = 1; i <= N; i++) {
			adjMatrix[cur][0] += adjMatrix[cur][i];
		}
	}

}
