import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// RGB 거리
public class Main {

    static final int INF = Integer.MAX_VALUE - 1001; // 'INF + 집을 색칠하는 비용'을 계산해야 하므로 Integer.MAX_VALUE로 설정하면 오버플로우가 발생한다.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] cost = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int minCost = INF;
        for (int i = 0; i < 3; i++) { // 첫 번째 집을 색칠하고 시작한다.
            int[][] dp = new int[N][3];
            dp[0][i] = cost[0][i];
            dp[0][(i + 1) % 3] = INF; // 선택되지 않은 색은 INF 비용이 든다고 가정한다.
            dp[0][(i + 2) % 3] = INF;

            for (int j = 1; j < N; j++) { // 두 번째 집부터 색칠비용을 계산한다.
                for (int k = 0; k < 3; k++) {
                    dp[j][k] = Math.min(dp[j - 1][(k + 1) % 3], dp[j - 1][(k + 2) % 3]) + cost[j][k];
                }
            }

            // 첫 번째 집에 칠한 색과 마지막 집에 칠한 색이 달라야 한다.
            minCost = Math.min(minCost, Math.min(dp[N - 1][(i + 1) % 3], dp[N - 1][(i + 2) % 3]));
        }

        System.out.println(minCost);
    }
}