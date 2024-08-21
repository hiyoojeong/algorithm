import java.util.Arrays;
import java.util.Scanner;

// 보호 필름
public class Solution {

	static final int A = 0, B = 1;

	static int D; // 두께 = 행
	static int W; // 가로크기 = 열
	static int K; // 합격기준
	static int[][] cases;
	static int[][] tmp;

	static boolean[] isSelected; // 약물을 넣었는지 체크

	static int minCnt;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		StringBuffer answer = new StringBuffer();
		for (int test_case = 1; test_case <= T; test_case++) {
			D = sc.nextInt();
			W = sc.nextInt();
			K = sc.nextInt();
			cases = new int[D][W];
			tmp = new int[D][W];

			for (int i = 0; i < D; i++) {
				for (int j = 0; j < W; j++) {
					cases[i][j] = tmp[i][j] = sc.nextInt();
				}
			}

			// 약품 투입 후보를 선정한다.
			isSelected = new boolean[D];
			minCnt = Integer.MAX_VALUE;
			subset(0);

			answer.append(String.format("#%d %d\n", test_case, minCnt));
		}

		System.out.println(answer);

		sc.close();

	}

	public static void subset(int cnt) {
		if (cnt == D) {
			inject(0, 0);
			reset();
			return;
		}

		// 약물 투입 후보 선정
		isSelected[cnt] = true;
		subset(cnt + 1);
		// 약물 투입 후보 탈락
		isSelected[cnt] = false;
		subset(cnt + 1);

	}

	public static void inject(int cnt, int index) {
		if (cnt >= minCnt) {
			return;
		}

		if (index == D) {
			if (isPass()) {
				minCnt = cnt;
			}
			return;
		}

		if (isSelected[index]) {
			// A 주입
			Arrays.fill(tmp[index], A);
			inject(cnt + 1, index + 1);
			// B 주입
			Arrays.fill(tmp[index], B);
			inject(cnt + 1, index + 1);
		} else {
			inject(cnt, index + 1);
		}

	}

	public static boolean isPass() {
		// 합격 기준 확인
		for (int j = 0; j < W; j++) {
			int s = 0, e = 1;
			int len = 1;

			while (e < D) {
				// 다른 특성이 나왔다면, 길이 카운팅 초기화
				if (tmp[s][j] != tmp[e][j]) {
					s = e;
					e++;
				}
				// 같은 특성이 나왔다면, 길이 증가
				else {
					e++;
				}

				len = Math.max(e - s, len);

				if (len >= K) { // 합격 기준 넘겼다면, 더 이상 확인할 필요없다. 반복문 탈출
					break;
				}
			}

			if (len < K) { // 반복문을 탈출한 시점에 합격 기준을 넘지 못했다면, 함격 기준 통과 실패
				return false;
			}
		}

		return true;
	}

	public static void reset() {
		for (int i = 0; i < D; i++) {
			for (int j = 0; j < W; j++) {
				tmp[i][j] = cases[i][j];
			}
		}
	}

}
