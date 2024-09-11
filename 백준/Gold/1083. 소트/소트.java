import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 소트
class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int S = Integer.parseInt(br.readLine());

		if ((N - 1) * N / 2 <= S) { // 내림차순 정렬이 가능한 경우
			Arrays.sort(arr);
			for (int i = N - 1; i >= 0; i--) {
				System.out.print(arr[i] + " ");
			}
			return;
		}

		for (int i = 0; i < N - 1; i++) {
			// 현재 위치보다 뒤에 있는 수 중 현재 위치로 가지고 올 수 있는 가장 큰 값을 찾는다.
			int maxIdx = i;
			for (int j = i + 1; j < N; j++) {
				if (arr[maxIdx] < arr[j] && j - i <= S) {
					maxIdx = j;
				}
			}

			// 현재 위치로 가지고 올 수 있는 가장 큰 값이 있다면 바꾼다.
			if (maxIdx != i) {
				for (int k = maxIdx; k > i; k--) { // 바꾸기
					int tmp = arr[k];
					arr[k] = arr[k - 1];
					arr[k - 1] = tmp;
				}
				S -= (maxIdx - i); // 남은 이동수 업데이트하기
			}
		}

		for (int i = 0; i < N; i++) {
			System.out.print(arr[i] + " ");
		}
	}
}