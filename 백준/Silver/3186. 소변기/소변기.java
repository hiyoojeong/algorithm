import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 소변기
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		String datas = br.readLine();

		StringBuffer answer = new StringBuffer();
		boolean flush = false;
		int k = 0, l = 0;
		for (int i = 0; i < N; i++) {
			int data = Character.getNumericValue(datas.charAt(i));

			// 사용중일때
			if (k == K) {
				if (data == 0) {
					l++;
				} else if (data == 1) {
					l = 0;
				}
			}

			if (k < K) {
				if (data == 0) {
					k = 0;
				} else if (data == 1) {
					k++;
				}
			}

			if (k == K && l == L) {
				answer.append((i + 1) + "\n");

				k = 0;
				l = 0;

				flush = true;
			}
		}

		if (k == K) {
			answer.append(N + (L - l));
			flush = true;
		}

		if (flush) {
			System.out.println(answer);
		} else {
			System.out.println("NIKAD");
		}

	}

}
