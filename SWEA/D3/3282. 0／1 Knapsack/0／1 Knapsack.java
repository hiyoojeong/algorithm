import java.util.Scanner;

// 0/1 Knapsack
class Solution {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt(); // 물건의 개수
            int K = sc.nextInt(); // 가방의 부피

            int[] v = new int[N+1]; // 부피
            int[] c = new int[N+1]; // 가치
            for(int i=1; i<=N; i++) {
                v[i] = sc.nextInt();
                c[i] = sc.nextInt();
            }

            int[] dp = new int[K+1];
            for(int i=1; i<=N; i++) {
                for(int j=K; j-v[i]>=0; j--) {
                    dp[j] = Math.max(dp[j], dp[j-v[i]] + c[i]);
                }
            }

            System.out.println("#" + test_case + " " + dp[K]);
        }
    }
}