import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 포켓볼
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer answer = new StringBuffer();

		int idx = 1;
		while (true) {

			st = new StringTokenizer(br.readLine());
			int min = Integer.parseInt(st.nextToken());
			int max = Integer.parseInt(st.nextToken());
			int cnt = 0;

			if (min == 0 && max == 0) {
				break;
			}

			// 삼각형
			int pos = 0;
			while (pos * (pos + 1) / 2 < min) {
				pos++;
			}
			while (pos * (pos + 1) / 2 < max - 1) {
				// 사각형
				if (isSquare((pos * (pos + 1) / 2) + 1)) {
//					System.out.println((pos * (pos + 1) / 2) + 1);
					cnt++;
				}
				pos++;
			}

			answer.append("Case " + idx + ": " + cnt + "\n");
			idx++;
		}

		System.out.println(answer);

	}

	public static boolean isSquare(int square) {
		int left = 0;
		int right = square;

		while (left <= right) {
			int mid = (left + right) / 2;
			int value = (int) Math.pow(mid, 2);

			if (value == square) {
				return true;
			} else if (value < square) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		
		return false;
	}
}
