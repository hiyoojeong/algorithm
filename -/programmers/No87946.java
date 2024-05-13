// 피로도
public class No87946 {

	public static int max = 0;

	public static void main(String[] args) {
		System.out.println(solution(80, new int[][] { { 80, 20 }, { 50, 40 }, { 30, 10 } }));
	}

	public static int solution(int k, int[][] dungeons) {
		int N = dungeons.length;
		permutation(k, dungeons, new int[N], new boolean[N], 0, N); // 순열

		return max;
	}

	public static void permutation(int k, int[][] dungeons, int[] output, boolean[] visited, int n, int N) {
		// 던전을 방문하는 순서가 다 정해졌다.
		if (n == N) {
			int curK = k; // 현재 피로도
			int cnt = 0; // 탐험한 던전의 수

			for (int i = 0; i < N; i++) {
				// 던전에 탐험할 수 있는 경우
				if (curK >= dungeons[output[i]][0]) {
					cnt++; // 탐험한 던전의 수 증가
					curK -= dungeons[output[i]][1]; // 소모 피로도만큼 현재 피로도 소모
				}
				// 던전에 탐험할 수 없는 경우
				else {
					break;
				}
			}

			// 탐험한 던전의 수가 최대 던전 수라면 업데이트
			if (cnt > max)
				max = cnt;
			return;
		}

		// 던전을 방문하는 순서를 정한다.
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				output[n] = i;
				permutation(k, dungeons, output, visited, n + 1, N);
				visited[i] = false;
			}
		}
	}

}
