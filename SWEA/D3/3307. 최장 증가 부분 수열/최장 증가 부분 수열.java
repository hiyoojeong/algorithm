import java.util.Scanner;

//  최장 부분 증가 수열 - dp
class Solution {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        StringBuffer answer = new StringBuffer();
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            int[] arr = new int[N];
            for(int i=0; i<N; i++) {
                arr[i] = sc.nextInt();
            }

            int[] dp = new int[N];
            int ans = 1;
            for(int i=0; i<N; i++) {
                dp[i] = 1;
                for(int j=0; j<=i-1; j++) {
                    if(arr[j]<arr[i]) {
                        dp[i] = Math.max(dp[i], dp[j]+1);
                    }
                    ans = Math.max(ans, dp[i]);
                }
            }

            answer.append(String.format("#%d %d\n", test_case, ans));
        }

        System.out.println(answer);

        sc.close();
    }
}