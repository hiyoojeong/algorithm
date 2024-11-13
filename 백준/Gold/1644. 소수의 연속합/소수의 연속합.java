import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 소수의 연속합
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		// 소수가 아닌 수 구하기
		boolean[] notPrime = new boolean[N + 1];
		notPrime[1] = true;
		int notPrimeCnt = 1;
		for (int i = 2; i * i <= N; i++) {
			for (int j = i * i; j <= N; j += i) {
				notPrimeCnt += notPrime[j] ? 0 : 1;
				notPrime[j] = true;
			}
		}

		// 소수 부분합 구하기
		int pSize = N - notPrimeCnt;
		int[] primeSum = new int[pSize + 1];
		int idx = 1;
		for (int i = 1; i <= N; i++) {
			if (!notPrime[i]) {
				primeSum[idx] = primeSum[idx - 1] + i;
				idx++;
			}
		}

		// 투포인터로 소수의 연속합이 N을 만족하는지 찾는다.
		int cnt = 0;
		int s = 0; // <- 포함되지 않는 수
		int e = 1;
		while (e <= pSize) {
			int val = primeSum[e] - primeSum[s];
			if (val == N) {
				cnt++;
				e++; // 다음 연속합을 찾아야 하므로, 범위를 늘려준다.
			} else if (val > N) { // 범위 줄이기
				s++;
			} else if (val < N) { // 범위 늘리기
				e++;
			}
		}

		System.out.println(cnt);
	}
}