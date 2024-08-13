import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 동전
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer answer = new StringBuffer();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            // 입력
            int N = Integer.parseInt(br.readLine()); // 동전의 가지수

            int[] coins = new int[N + 1]; // 동전의 각 금액
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }

            int M = Integer.parseInt(br.readLine()); // 동전으로 만들어야 할 금액

            // dp[i] : i원을 만들기 위한 경우의 수
            long[] dp = new long[M + 1];

            // i 번째까지의 동전을 사용했을 때, j원을 만들 수 있는 경우의 수를 구한다.
            for (int i = 1; i <= N; i++) { // i 번째까지의 동전
                for (int j = 1; j <= M; j++) { // j원
                    if (j > coins[i]) { // 현재 동전을 사용할 수 있다면(j원보다 작은 단위의 동전이라면), 사용한다치고 직전 경우의 수를 불러온다.
                        dp[j] += (dp[j - coins[i]]);
                    }
                    if (j == coins[i]) { // 현재 동전과 동일한 금액이라면, 해당 동전만 사용하는 경우인 1을 추가한다.
                        dp[j]++;
                    }
                }
            }

            System.out.println(dp[M]);
        }
    }
}
