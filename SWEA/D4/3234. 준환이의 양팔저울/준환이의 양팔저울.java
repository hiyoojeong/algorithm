import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 준환이의 양팔저울
class Solution {

    public static int N;
    public static int[] w;
    public static boolean[] visit;
    public static int[][] dp;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuffer answer = new StringBuffer();

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {

            // 데이터 입력 받기
            N = Integer.parseInt(br.readLine());
            w = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                w[i] = Integer.parseInt(st.nextToken());
            }

            // 경우의 수 찾기
            visit = new boolean[N];
            dp = new int[512][512];
            int ans = dfs(0, 0, 0, 0, 0);

            answer.append(String.format("#%d %d\n", test_case, ans));
        }

        System.out.println(answer);
    }

    public static int dfs(int cnt, int l, int lw, int r, int rw) {
        if (cnt == N) {
            return 1;
        }
        if (dp[l][r] != 0) {
            return dp[l][r];
        }
        for (int i = 0; i < N; i++) {
            if (visit[i]) continue;

            visit[i] = true;

            // left
            dp[l][r] += dfs(cnt + 1, l | (1 << i), lw + w[i], r, rw);

            //right
            if (lw >= rw + w[i]) {
                dp[l][r] += dfs(cnt + 1, l, lw, r | (1 << i), rw + w[i]);
            }

            visit[i] = false;
        }
        return dp[l][r];
    }
}