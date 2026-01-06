import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 11049 행렬 곱셈 순서

public class Main {

    static int[] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            arr[i] = h;
            arr[i + 1] = w;
        }

        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        int ans = dfs(0, N - 1); // 전체 행렬 대상으로 계산한 최소 곱셈 횟수
        System.out.println(ans);
    }

    public static int dfs(int s, int e) {
        if (s == e) return 0;
        if (dp[s][e] != Integer.MAX_VALUE) return dp[s][e];

        for (int i = s; i < e; i++) {
            dp[s][e] = Math.min(dp[s][e], dfs(s, i) + dfs(i + 1, e) + (arr[s] * arr[i + 1] * arr[e + 1]));
        }

        return dp[s][e];
    }
}