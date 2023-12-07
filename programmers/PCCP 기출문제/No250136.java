import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

// [PCCP 기출문제] 2번 / 석유 시추
public class No250136 {

	public static void main(String[] args) {
		System.out.println(solution(new int[][] { { 0, 0, 0, 1, 1, 1, 0, 0 }, { 0, 0, 0, 0, 1, 1, 0, 0 },
				{ 1, 1, 0, 0, 0, 1, 1, 0 }, { 1, 1, 1, 0, 0, 0, 0, 0 }, { 1, 1, 1, 0, 0, 0, 1, 1 } }));
	}

	public static int solution(int[][] land) {
		int N = land.length;
		int M = land[0].length;

		int[][] move = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };

		// 석유 덩어리 번호의 분포 정보
		int[][] oil = new int[N][M];

		// 석유 덩어 번호와 크기 정보
		Map<Integer, Integer> oils = new HashMap<>();
		int oilIdx = 1;

		boolean[][] visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (land[i][j] == 1 && !visited[i][j]) {
					Queue<int[]> queue = new LinkedList<>();
					queue.add(new int[] { i, j });
					visited[i][j] = true;

					// 석유 덩어리 크기 저장
					oils.put(oilIdx, oils.getOrDefault(oilIdx, 0) + 1);

					// 석유 위치 저장
					oil[i][j] = oilIdx;

					while (!queue.isEmpty()) {
						int[] l = queue.poll();

						for (int m = 0; m < 4; m++) {
							int ni = l[0] + move[m][0];
							int nj = l[1] + move[m][1];

							if (ni >= 0 && nj >= 0 && ni < land.length && nj < land[0].length) {
								if (!visited[ni][nj] && land[ni][nj] == 1) {
									visited[ni][nj] = true;
									queue.add(new int[] { ni, nj });

									// 석유 덩어리 크기 저장
									oils.put(oilIdx, oils.get(oilIdx) + 1);

									// 석유 위치 저장
									oil[ni][nj] = oilIdx;
								}
							}
						}

					}

					oilIdx++;
				}
			}
		}

		int answer = Integer.MIN_VALUE;
		for (int j = 0; j < M; j++) {
			Set<Integer> set = new HashSet<>();
			int get = 0;
			for (int i = 0; i < N; i++) {
				if (oil[i][j] != 0 && !set.contains(oil[i][j])) {
					get += oils.get(oil[i][j]);
					set.add(oil[i][j]);
				}
			}
			answer = (get > answer) ? get : answer;
		}

		return answer;
	}

}
