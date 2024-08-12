import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 제곱수의 합
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine()); // 상자의 개수
		int[] box = new int[n + 1]; // 각 상자의 크기

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			box[i] = Integer.parseInt(st.nextToken());
		}

		// dp[i] : i번째 상자에 한 번에 넣을 수 있는 최대의 상자 개수
		int[] dp = new int[n + 1];
		// 최대의 상자 개수
		int maxBox = 0;
		
		for (int i = 1; i <= n; i++) {
			for (int j = i - 1; j > 0; j--) {
				// box[i]가 box[j]를 담을 수 있다? box[j]가 담을 것도 모조리 담을 수 있다.
				if (box[i] > box[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			// 최대의 상자 개수를 담았다면, 업데이트 해준다.
			maxBox = Math.max(dp[i], maxBox);
		}
		
		// 각 상자도 포함해야 하므로 +1 해준다.
		System.out.println(maxBox + 1);
	}

}
