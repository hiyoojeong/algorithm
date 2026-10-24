import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 요리사
class Solution {

    static int N;
    static int[][] S;
    static int ans;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuffer answer = new StringBuffer();

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {

            N = Integer.parseInt(br.readLine());
            S = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    S[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            ans = Integer.MAX_VALUE;
            dfs(0, 0);

            answer.append(String.format("#%d %d\n", test_case, ans));
        }

        System.out.println(answer);
    }

    public static void dfs(int start, int visit) {
        // 조합완성. 시너지 계산.
        if (Integer.bitCount(visit) == N >> 1) {
            int A = 0, B = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if ((visit & (1 << i)) != 0 && (visit & (1 << j)) != 0) {
                        A += S[i][j];
                    } else if ((visit & (1 << i)) == 0 && (visit & (1 << j)) == 0) {
                        B += S[i][j];
                    }
                }
            }

            int val = Math.abs(A - B);
            if (ans > val) ans = val;

            return;
        }

        // 조합
        for (int i = start; i < N; i++) {
            if ((visit & (1 << i)) != 0) continue;
            dfs(i + 1, visit | (1 << i));
        }
    }
}
/*
1
4
0 5 3 8
4 0 4 1
2 5 0 3
7 2 3 0
 */