
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 두 수의 합
public class Main {

	static int N, K;
	static int[] arr;
	static int minDist; // K와 가장 가까운 두 정수의 합
	static int minDistCnt; // K와 가장 가까운 두 정수의 합의 조합의 수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer answer = new StringBuffer();

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			arr = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(arr);

			minDist = Integer.MAX_VALUE;
			minDistCnt = 0;
			for (int i = 0; i < N - 1; i++) {
				binSrc(i + 1, N - 1, arr[i]);
			}

			answer.append(minDistCnt).append("\n");
		}

		System.out.println(answer);

	}

	public static void binSrc(int left, int right, int num) {
		while (left <= right) {
			int mid = (left + right) / 2;
			int sum = arr[mid] + num; // 두 개의 정수의 합
			int dist = Math.abs(sum - K); // 두 개의 정수의 합과 K 사이의 차이

			// 두 개의 정수의 합이 K에 가장 가까운 수라면, 정보 업데이트
			if (dist <= minDist) {
				if (dist < minDist) {
					minDist = dist;
					minDistCnt = 0;
				}
				minDistCnt++;
			}

			// 더 가깝게 이동한다.
			// 두 개의 정수의 합이 K보다 작다? 값을 늘려야 한다.
			if (sum < K) {
				left = mid + 1;
			}
			// 두 개의 정수의 합이 K보다 크다? 값을 줄여야 한다.
			else if (sum > K) {
				right = mid - 1;
			}
			// 두 개의 정수의 합이 K와 같다? 모든 정수가 다른 값이므로, 가장 가까운 두 정수의 조합의 수는 1개 뿐이다.
			else if (sum == K) {
				return;
			}

		}
	}
}
