import java.util.*;

// 키 순서
class Solution {

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        StringBuffer answer = new StringBuffer();
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            boolean[][] taller = new boolean[N][N];
            for (int i = 0; i < M; i++) {
                int s = sc.nextInt() - 1;
                int e = sc.nextInt() - 1;
                taller[s][e] = true;
            }

            for (int k = 0; k < N; k++) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (taller[i][k] && taller[k][j]) {
                            taller[i][j] = true;
                        }
                    }
                }
            }


            int ans = 0;
            for (int i = 0; i < N; i++) {
                int cnt = 0;
                for (int j = 0; j < N; j++) {
                    if (taller[i][j]) cnt++;
                    if (taller[j][i]) cnt++;

                }
                if (cnt == N - 1) ans++;
            }

            answer.append(String.format("#%d %d\n", test_case, ans));
        }

        System.out.println(answer);

        sc.close();
    }
}