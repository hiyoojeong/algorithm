import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 이항계수 2
public class Main {

    static final int VALUE = 10_007;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 자연수
        int K = Integer.parseInt(st.nextToken()); // 정수
        K = Math.min(K, N - K);

        int[][] dp = new int[N + 1][K + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= i && j <= K; j++) {
                if (j == 0 || j == i) {
                    dp[i][j] = 1;
                    continue;
                }

                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                dp[i][j] %= VALUE;
            }
        }
        
        System.out.println(dp[N][K]);
    }

}
