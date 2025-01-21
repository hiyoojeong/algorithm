import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 카드게임
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        int[] B = new int[N];
        int[][] dp = new int[N + 1][N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        // 진행될 수 없음을 의미하는 -1
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }
        dp[0][0] = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (dp[i][j] == -1) {
                    continue;
                }
                // 왼쪽, 오른쪽 둘 다 버림
                dp[i + 1][j + 1] = Math.max(dp[i + 1][j + 1], dp[i][j]);
                // 왼쪽만 버림
                dp[i + 1][j] = Math.max(dp[i + 1][j], dp[i][j]);
                // 오른쪽만 버림
                if (A[i] > B[j]) {
                    dp[i][j + 1] = Math.max(dp[i][j + 1], dp[i][j] + B[j]);
                }
            }
        }

        int max = 0;
        for (int i = 0; i <= N; i++) {
            max = Math.max(max, dp[i][N]);
            max = Math.max(max, dp[N][i]);
        }

        System.out.println(max);
    }

}