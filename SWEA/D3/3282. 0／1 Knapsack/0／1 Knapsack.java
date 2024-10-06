import java.util.Scanner;

// 0/1 Knapsack
public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        StringBuffer answer = new StringBuffer();
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt(); // 물건의 개수
            int C = sc.nextInt(); // 가방의 부피

            int[] v = new int[N]; // 물건의 부피
            int[] c = new int[N]; // 물건의 가치
            for (int i = 0; i < N; i++) {
                v[i] = sc.nextInt();
                c[i] = sc.nextInt();
            }

            int[] dp = new int[C + 1];
            for (int i = 0; i < N; i++) {
                for (int j = C; j - v[i] >= 0; j--) {
                    // j번째 물건을 담는 게 더 이득인 경우
                    dp[j] = Math.max(dp[j], c[i] + dp[j - v[i]]);
                }
            }

            answer.append(String.format("#%d %d\n", test_case, dp[C]));
        }

        System.out.println(answer);

        sc.close();
    }
}
