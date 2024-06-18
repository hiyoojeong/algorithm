import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 교수가 된 현우
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder answer = new StringBuilder();
		for (int t = 0; t < T; t++) {
			int n = Integer.parseInt(br.readLine());
			answer.append(countZero(n)).append("\n");
		}

		System.out.println(answer);
	}

	public static int countZero(int n) {
		int two = 0, five = 0;

		for (int i = 2; i <= n; i *= 2) {
			two += n / i;
		}

		for (int i = 5; i <= n; i *= 5) {
			five += n / i;
		}

		return Math.min(two, five);
	}

}
