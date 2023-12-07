import java.util.Collections;
import java.util.PriorityQueue;

// 혼자 놀기의 달인
public class No131130 {

	public static void main(String[] args) {
		System.out.println(solution(new int[] { 8, 6, 3, 7, 2, 5, 1, 4 }));
	}

	public static int solution(int[] cards) {
		int N = cards.length;
		int[][] route = new int[N][N];
		for (int i = 0; i < N; i++) {
			route[i][cards[i] - 1] = 1;
		}

		PriorityQueue<Integer> cnts = new PriorityQueue<>(Collections.reverseOrder());
		for (int i = 0; i < N; i++) {
			if (route[i][cards[i] - 1] == 1) {
				// 상자의 경로를 저장한다.
				int r1 = i;
				int r2 = cards[i] - 1;

				// 연 상자의 개수
				int cnt = 0;

				while (route[r1][r2] != 0) {
					// 현재 상자를 연다.
					route[r1][r2] = 0;

					// 연 상자의 개수 증가한다.
					cnt++;

					// 다음 상자의 경로를 저장한다.
					r1 = r2;
					r2 = cards[r1] - 1;
				}

				// 연 상자의 개수 저장
				cnts.add(cnt);
			}
		}

		// 1번 상자 그룹을 제외하고 남는 상자가 없으면 0점이다.
		if (cnts.size() == 1)
			return 0;

		// 1번 상자 그룹에 속한 상자의 수와 2번 상자 그룹에 속한 상자의 수를 곱한다.
		// 상자의 수가 가장 많은 두 그룹을 선정한다.
		int group1 = cnts.poll();
		int group2 = cnts.poll();
		return group1 * group2;
	}

}
