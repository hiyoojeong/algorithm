
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 롤 케이크
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int L = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());

		boolean[] cake = new boolean[L + 1];
		int expectedNum = 0, expectedCnt = 0;
		int num = 0, cnt = 0;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			int ec = e - s + 1, c = 0;
			for (int j = s; j <= e; j++) {
				if (!cake[j]) {
					c++;
					cake[j] = true;
				}
			}

			if (expectedCnt < ec) { // 가장 많은 조각을 받을 것으로 기대하고 있던 방청객 번호 업데이트
				expectedCnt = ec;
				expectedNum = i;
			}
			if (cnt < c) { // 실제로 가장 많은 조각을 받은 방청객의 번호 업데이트
				cnt = c;
				num = i;
			}
		}

		System.out.println(expectedNum);
		System.out.println(num);
	}

}
