import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 계단 수
public class Main {

    static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][][] dp = new int[N][10][(1 << 10)];

        // 처음에 시작하는 수를 지정한다. (0으로 시작할 수 없다.)
        for (int k = 1; k <= 9; k++) {
            dp[0][k][1 << k] = 1;
        }

        // n번째 자리에 k를 사용할 것인데, 직전 k-1, k+1을 넣었을 때 경우의 수(v)를 합산한다.
        for (int n = 1; n < N; n++) {
            for (int k = 0; k <= 9; k++) {
                for (int v = 0; v < (1 << 10); v++) {
                    if (k == 0) {
                        dp[n][k][v | (1 << k)] += dp[n - 1][k + 1][v];
                    } else if (k == 9) {
                        dp[n][k][v | (1 << k)] += dp[n - 1][k - 1][v];
                    } else {
                        dp[n][k][v | (1 << k)] += (dp[n - 1][k + 1][v] + dp[n - 1][k - 1][v]);
                    }
                    dp[n][k][v | (1 << k)] %= MOD;
                }
            }
        }

        // 모든 수를 사용했을 때 N자리 숫자가 만들어지는 경우의 수를 합산한다.
        int ans = 0;
        for (int i = 0; i <= 9; i++) {
            ans = (ans + dp[N - 1][i][(1 << 10) - 1]) % MOD;
        }

        System.out.println(ans);
    }

}