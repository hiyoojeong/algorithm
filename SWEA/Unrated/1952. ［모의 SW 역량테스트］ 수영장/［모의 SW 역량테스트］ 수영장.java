import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 수영장
class Solution {

    static int[] dp;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuffer answer = new StringBuffer();

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            int day = Integer.parseInt(st.nextToken());
            int month = Integer.parseInt(st.nextToken());
            int month3 = Integer.parseInt(st.nextToken());
            int year = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            dp = new int[13];

            for (int i = 1; i <= 12; i++) {
                int d = dp[i - 1] + Integer.parseInt(st.nextToken()) * day; // 1일 이용권
                int m1 = dp[i - 1] + month; // 1달 이용권
                int m3 = (i - 3 < 0 ? 0 : dp[i - 3]) + month3; // 3달 이용권
                dp[i] = Math.min(d, Math.min(m1, Math.min(m3, year)));
            }

            answer.append(String.format("#%d %d\n", test_case, dp[12]));
        }

        System.out.println(answer);
    }
}
/*
2
10 40 100 300
0 0 2 9 1 5 0 0 0 0 0 0
10 100 50 300
0 0 0 0 0 0 0 0 6 2 7 8
 */