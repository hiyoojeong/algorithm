import java.util.Scanner;

// 0/1 Knapsack - 이분탐색
class Solution {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        StringBuffer answer = new StringBuffer();
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            int[] lis = new int[N];
            int val = 0;
            int size = 0;
            int ans = 1;
            for (int i = 0; i < N; i++) {
                val = sc.nextInt();

                int l = 0;
                int r = size;
                while (l < r) {
                    int m = (l + r) / 2;
                    if (lis[m] < val) {
                        l = m + 1;
                    } else {
                        r = m;
                    }
                }
                lis[r] = val;
                if(r==size) {
                    size++;
                }
                ans = Math.max(ans, size);
            }

            answer.append(String.format("#%d %d\n", test_case, ans));
        }

        System.out.println(answer);

        sc.close();
    }
}