import java.util.Scanner;

public class Solution {

	static int N = 6;
	static int[] numbers;
	static int[] selected;
	static boolean[] isSelected;
	static boolean res;

	public static void main(String[] args) {
		numbers = new int[N];
		selected = new int[N];
		isSelected = new boolean[N];

		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			// 입력
			String input = sc.next();
			for (int i = 0; i < N; i++) {
				numbers[i] = input.charAt(i) - '0';
			}

			// 순열
			res = false;
			permutation(0);

			// 출력
			System.out.printf("#%d %s\n", t, res);
		}

		sc.close();

	}

	public static void permutation(int cnt) {
		if (cnt == N) {
//			System.out.println(Arrays.toString(selected));
			if(isBabyGin()) {
				res = true;
			}
			return;
		}

		for (int i = 0; i < N; i++) {
			if (isSelected[i]) {
				continue;
			}

			selected[cnt] = numbers[i];
			isSelected[i] = true;
			permutation(cnt + 1);
			isSelected[i] = false;
		}
	}

	public static boolean isBabyGin() {
		// 앞 3자리 확인
		boolean isRun = true;
		boolean isTriplet = true;

		int pre = selected[0];
		for (int i = 1; i < 3; i++) {
			if (pre + 1 != selected[i]) {
				isRun = false;
			}
			if (pre != selected[i]) {
				isTriplet = false;
			}

			pre = selected[i];
		}

		if (!isRun && !isTriplet) {
			return false;
		}

		// 뒤 3자리 확인
		isRun = true;
		isTriplet = true;

		pre = selected[0 + 3];
		for (int i = 1 + 3; i < 3 + 3; i++) {
			if (pre + 1 != selected[i]) {
				isRun = false;
			}
			if (pre != selected[i]) {
				isTriplet = false;
			}

			pre = selected[i];
		}

		if (!isRun && !isTriplet) {
			return false;
		}

		return true;
	}

}
