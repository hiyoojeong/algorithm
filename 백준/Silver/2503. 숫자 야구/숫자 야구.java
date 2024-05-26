import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 숫자 야구
public class Main {

	static List<Integer> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 기대값을 모두 저장해둔다.
		for (int i = 123; i <= 987; i++) {
			// 0을 포함한 숫자는 제외해야 한다.
			if (String.valueOf(i).charAt(0) == '0' || String.valueOf(i).charAt(1) == '0'
					|| String.valueOf(i).charAt(2) == '0') {
				continue;
			}

			// 동일한 숫자를 포함한 숫자는 제외해야 한다.
			if (String.valueOf(i).charAt(0) == String.valueOf(i).charAt(1)
					|| String.valueOf(i).charAt(1) == String.valueOf(i).charAt(2)
					|| String.valueOf(i).charAt(0) == String.valueOf(i).charAt(2)) {
				continue;
			}

			list.add(i);
		}

		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			// 스트라이크, 볼 개수를 만족하지 않는 값들을 지운다.
			filter(num, s, b);
		}

		// 남아 있는 기대값들의 개수를 출력한다.
		System.out.println(list.size());

	}

	public static void filter(int num, int s, int b) {
		for (int idx = 0; idx < list.size(); idx++) {
			// 기대값을 가져온다.
			int expectedNum = list.get(idx);

			// 스트라이크, 볼 개수를 확인한다.
			int ns = 0;
			int nb = 0;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (i == j && String.valueOf(num).charAt(i) == String.valueOf(expectedNum).charAt(j)) {
						ns++;
						continue;
					}
					if (i != j && String.valueOf(num).charAt(i) == String.valueOf(expectedNum).charAt(j)) {
						nb++;
						continue;
					}
				}
			}

			// 스트라이크, 볼 개수를 만족하지 않는 값들을 지운다.
			if (ns != s || nb != b) {
				list.remove(idx--);
			}
		}
	}

}
