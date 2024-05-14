import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 합이 0
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] list = new int[n + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		list[n] = Integer.MAX_VALUE;

		// 오름차순 정렬
		Arrays.sort(list);

		// 두 개의 숫자 선택
		// 합이 0이 되는, 한 개의 숫자 탐색
		// lowerbound : target보다 같거나 큰 수들 중 가장 앞 원소의 인덱스
		// upperbound : target보다 큰 수들 중 가장 앞 원소의 인덱스
		long answer = 0;
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				long sum = list[i] + list[j];
				int sidx = lowerbound(j + 1, n, list, (-1) * sum);
				int eidx = upperbound(j + 1, n, list, (-1) * sum);

				// 합이 0이 되는, 한 개의 숫자의 전체 개수
				answer += (eidx - sidx);
			}
		}

		System.out.println(answer);

	}

	public static int lowerbound(int left, int right, int[] list, long target) {
		while (left < right) {
			int mid = (left + right) / 2;
			if (list[mid] >= target) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return right;
	}

	public static int upperbound(int left, int right, int[] list, long target) {
		while (left < right) {
			int mid = (left + right) / 2;
			if (list[mid] <= target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return right;
	}

}
