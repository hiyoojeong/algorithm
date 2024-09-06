import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 중복된 탐색을 하지 않도록 최적화
public class Solution {

	static int N, adjMatrix[][], radjMatrix[][], cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer answer = new StringBuffer();
		int TC = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(in.readLine());
			int M = Integer.parseInt(in.readLine());

			adjMatrix = new int[N + 1][N + 1]; // 학생번호 1번부터 시작
			StringTokenizer st = null;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(in.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				adjMatrix[a][b] = 1;
			}

			for (int i = 1; i <= N; i++) {
				adjMatrix[i][0] = -1; // 탐색되지 않은 학생을 나타냄(후에 탐색이 완료되면 자신보다 큰 학생 수 저장)
			}

			// 각 학생마다 자신보다 큰, 자신보다 작은 학생 각각 탐색
			for (int i = 1; i <= N; i++) {
				if (adjMatrix[i][0] != -1) { // 자신이 탐색되어 있는 상태면 넘김
					continue;
				}
				gtDFS(i);
			}

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					adjMatrix[0][j] += adjMatrix[i][j];
				}
			}

			int ans = 0; // 자신의 키를 알 수 있는 학생 수
			for (int k = 1; k <= N; k++) {
				if (adjMatrix[k][0] + adjMatrix[0][k] == N - 1) {
					ans++;
				}
			}

			answer.append("#" + tc + " " + ans + "\n");
		}
		System.out.println(answer);
	}

	private static void gtDFS(int cur) { // 자신보다 큰 학생따라 탐색
		for (int i = 1; i <= N; i++) {
			if (adjMatrix[cur][i] == 0) { // 자신과 관계없는 학생은 넘김
				continue;
			}

			// i는 나보다 크다.
			// i가 탐색되지 않았다면 탐색한다.
			if (adjMatrix[i][0] == -1) {
				gtDFS(i);
			}

			// 나보다 키가 큰 학생이 탐색을 완료한 상태
			// i보다 키가 큰 학생이 있다면 그 학생들의 정보를 나에게 반영(간접 관계를 직접관계로 경로 압축)
			if (adjMatrix[i][0] > 0) { // 나보다 큰 학생이 있을 경우만 업데이트
				for (int j = 1; j <= N; j++) {
					if (adjMatrix[i][j] != 0) {
						adjMatrix[cur][j] = 1;
					}
				}
			}
		}

		// 나보다 키가 큰 학생들의 수를 센다.
		adjMatrix[cur][0] = 0; // 초기값이 -1이므로 누적하기 위해서 0으로 초기화
		for (int k = 1; k <= N; k++) {
			adjMatrix[cur][0] += adjMatrix[cur][k];
		}
	}

}
