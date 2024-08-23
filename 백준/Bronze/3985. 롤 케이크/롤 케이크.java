
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 3985. 롤 케이크
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int L = Integer.parseInt(br.readLine()); // 롤 케이크의 길이
		int N = Integer.parseInt(br.readLine()); // 방청객의 번호

		int[] cake = new int[L + 1];
		int expectedMax = 0, expectedMaxNo = 0;
		int max = 0, maxNo = 0;

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int P = Integer.parseInt(st.nextToken()); // 시작 조각
			int K = Integer.parseInt(st.nextToken()); // 끝 조각
			int len = K - P + 1;

			if (expectedMax < len) { // 가장 많은 조각을 받을 것으로 기대하고 있던 방청객의 번호 업데이트
				expectedMax = len;
				expectedMaxNo = i;
			}

			int cnt = 0; // 못먹은 조각
			for (int j = P; j <= K; j++) {
				if (cake[j] != 0) {
					cnt++;
					continue;
				}
				cake[j] = i;
			}

			if (max < len - cnt) { // 실제로 가장 많은 조각을 받은 방청객의 번호 업데이트
				max = len - cnt;
				maxNo = i;
			}
		}

		System.out.println(expectedMaxNo);
		System.out.println(maxNo);
	}

}
