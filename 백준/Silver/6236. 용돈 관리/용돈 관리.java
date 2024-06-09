import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 용돈 관리
public class Main {

	static int N, M, max, totalSum;
	static int[] uses;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		uses = new int[N];
		max = Integer.MIN_VALUE;
		totalSum = 0;
		for (int i = 0; i < N; i++) {
			uses[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, uses[i]);
			totalSum += uses[i];
		}

		System.out.println(binary());

	}

	public static int binary() {
		int left = max;
		int right = totalSum;
		int answer = 0;

		// 하한값을 찾아야 한다.
		while (left <= right) {
			int mid = (left + right) / 2;

			if (check(mid)) {
				answer = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		return answer;
	}

	public static boolean check(int money) {
		int cnt = 1;
		int sum = 0;

		for (int i = 0; i < N; i++) {
			sum += uses[i];

			// 통장에서 뺀 돈이 모자라기 때문에, 다시 인출한다.
			if (sum > money) {
				sum = uses[i];
				cnt++;
			}

			// M번 이상 인출했다면, money로는 불가능하다는 의미이다.
			if (cnt > M) {
				return false;
			}
		}

		return cnt <= M;

	}

}
