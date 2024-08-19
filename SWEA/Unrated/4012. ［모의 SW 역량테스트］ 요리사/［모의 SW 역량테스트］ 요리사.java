import java.util.Arrays;
import java.util.Scanner;

// 요리사
public class Solution {

	static int N; // 식재료의 개수
	static int[][] map; // 식재료들의 시너지 값

	static boolean[] isDivided; // true : A 음식, false : B 음식
	static final boolean A = true;
	static final boolean B = false;

	static int[] selected; // 시너지를 게산하기 위해 선택한 두 식재료 정보
	static int synergyA, synergyB; // A 음식의 시너지, B 음식의 시너지

	static int minDist; // 두 음식 간의 최소 맛 차이

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		StringBuffer answer = new StringBuffer();
		for (int test_case = 1; test_case <= T; test_case++) {
			// 입력
			N = sc.nextInt();
			map = new int[N][N];
			minDist = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			isDivided = new boolean[N];
			selected = new int[2];

			// 식재료를 A 음식에 넣을지, B 음식에 넣을지 선택한다.
			divide(0, 0);

			answer.append(String.format("#%d %d\n", test_case, minDist));
		}

		System.out.println(answer);

		sc.close();
	}

	public static void divide(int cnt, int index) {
		if (index == N) {
			// 식재료들이 각각 N / 2개씩 나누어 두 개의 요리를 만들어냈다.
			if (cnt == N / 2) {
				// A 음식을 만들 때 시너지 구하기
				synergyA = 0;
				getSynergyA(0, 0);

				// B 음식을 만들 때 시너지 구하기
				synergyB = 0;
				getSynergyB(0, 0);

				// 두 음식 간의 맛의 차이가 최소가 되는 경우라면, 업데이트한다.
				minDist = Math.min(Math.abs(synergyA - synergyB), minDist);
			}
			return;
		}

		// 현재 식재료를 A 음식으로 만드는 경우
		isDivided[index] = A;
		divide(cnt + 1, index + 1);
		// 현재 식재료를 B 음식으로 만드는 경우
		isDivided[index] = B;
		divide(cnt, index + 1);
	}

	public static void getSynergyA(int cnt, int start) {
		if (cnt == 2) {
			synergyA += map[selected[0]][selected[1]];
			synergyA += map[selected[1]][selected[0]];
			return;
		}

		for (int i = start; i < N; i++) {
			if (isDivided[i] == A) {
				selected[cnt] = i;
				getSynergyA(cnt + 1, i + 1);
			}
		}
	}

	public static void getSynergyB(int cnt, int start) {
		if (cnt == 2) {
			synergyB += map[selected[0]][selected[1]];
			synergyB += map[selected[1]][selected[0]];
			return;
		}

		for (int i = start; i < N; i++) {
			if (isDivided[i] == B) {
				selected[cnt] = i;
				getSynergyB(cnt + 1, i + 1);
			}
		}
	}

}
