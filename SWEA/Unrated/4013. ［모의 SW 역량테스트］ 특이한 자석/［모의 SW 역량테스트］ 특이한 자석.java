import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 특이한 자석
public class Solution {

	static class Magnet {
		int num, direction;

		public Magnet(int num, int direction) {
			this.num = num;
			this.direction = direction;
		}
	}

	static final int N = 4; // 톱니바퀴의 수
	static final int M = 8; // 톱니바퀴에서 톱니의 수
	static int K; // 회전 횟수
	static int[][] magnets; // 톱니바퀴 상태

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		StringBuffer answer = new StringBuffer();
		for (int test_case = 1; test_case <= T; test_case++) {
			// 입력
			K = sc.nextInt();
			magnets = new int[N][M];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					magnets[i][j] = sc.nextInt();
				}
			}

			for (int i = 0; i < K; i++) {
				int num = sc.nextInt() - 1; // 회전시키려는 자석의 번호
				int direction = sc.nextInt(); // 회전 방향

				// 회전한다.
				rotate(num, direction);
			}

			// 결과
			int res = getScore();

			// 출력
			answer.append(String.format("#%d %d\n", test_case, res));
		}

		System.out.println(answer);

		sc.close();
	}

	public static void rotate(int num, int direction) {
		// direction = 1 : 시계방향
		// direction = -1 : 반시계방향

		Queue<Magnet> queue = new LinkedList<>(); // 회전 대기 큐
		queue.add(new Magnet(num, direction));
		int left = 0, right = 0, currentDir = 0;

		/*
		 * 오른쪽에 있는 자석들 중 회전되어야 할 자석 정보를 찾는다.
		 */
		left = num;
		right = num + 1;
		currentDir = direction * -1;
		while (right < N) {
			// 붙어 있는 자석은 서로 붙어 있는 날의 자성과 같을 경우에는 회전하지 않으므로, 연쇄적인 회전이 끊긴다.
			if (magnets[left][2] == magnets[right][6]) {
				break;
			}

			// 붙어 있는 자석은 서로 붙어 있는 날의 자성과 다를 경우에만, 반대 방향으로 1 칸 회전된다.
			queue.add(new Magnet(right, currentDir));

			// 다음 자석의 번호를 설정한다.
			left++;
			right++;

			// 다음 자석의 회전방향을 반대로 설정한다.
			currentDir *= -1;
		}

		/*
		 * 왼쪽에 있는 자석들 중 회전되어야 할 자석 정보를 찾는다.
		 */
		left = num - 1;
		right = num;
		currentDir = direction * -1;
		while (left >= 0) {
			// 붙어 있는 자석은 서로 붙어 있는 날의 자성과 같을 경우에는 회전하지 않으므로, 연쇄적인 회전이 끊긴다.
			if (magnets[left][2] == magnets[right][6]) {
				break;
			}

			// 붙어 있는 자석은 서로 붙어 있는 날의 자성과 다를 경우에만, 반대 방향으로 1 칸 회전된다.
			queue.add(new Magnet(left, currentDir));

			// 다음 자석의 번호를 설정한다.
			left--;
			right--;

			// 다음 자석의 회전방향을 반대로 설정한다.
			currentDir *= -1;
		}

		/*
		 * 회전한다.
		 */
		while (!queue.isEmpty()) {
			Magnet now = queue.poll();

			int[] tmp = new int[M]; // 임시저장 배열

			// 시계 방향
			if (now.direction == 1) {
				for (int i = 0; i < M - 1; i++) {
					tmp[i + 1] = magnets[now.num][i];
				}
				tmp[0] = magnets[now.num][M - 1];
			}

			// 반시계 방향
			else {
				for (int i = 1; i < M; i++) {
					tmp[i - 1] = magnets[now.num][i];
				}
				tmp[M - 1] = magnets[now.num][0];
			}

			for (int i = 0; i < M; i++) { // 임시저장 배열에서 꺼내와서 다시 저장
				magnets[now.num][i] = tmp[i];
			}
		}
	}

	public static int getScore() {
		// 1번째 자석 점수 * 2^0
		// 2번째 자석 점수 * 2^1
		// 3번째 자석 점수 * 2^2
		// 4번째 자석 점수 * 2^3
		int score = 0;
		for (int i = 0; i < N; i++) {
			score += (magnets[i][0] * Math.pow(2, i));
		}

		return score;
	}

}
