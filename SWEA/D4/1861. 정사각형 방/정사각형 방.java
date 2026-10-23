import java.util.*;

// 정사각형 방
class Solution {

    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};

    static int[][] map = null;
    static int[][] move = null;
    static int N, ansS, ansMove;

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        StringBuffer answer = new StringBuffer();
        for (int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();
            map = new int[N][N];
            move = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            ansS = Integer.MAX_VALUE;
            ansMove = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int m = dfs(i, j);
                    if (ansMove < m) {
                        ansS = map[i][j];
                        ansMove = m;
                    }
                    if (ansMove == m) {
                        ansS = Math.min(ansS, map[i][j]);
                    }
                }
            }

            answer.append(String.format("#%d %d %d\n", test_case, ansS, ansMove));
        }

        System.out.println(answer);

        sc.close();
    }

    public static int dfs(int x, int y) {
        if (move[x][y] != 0) {
            return move[x][y];
        }
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx < 0 || ny < 0 || nx >= N || ny >= N
                    || map[nx][ny] != map[x][y] + 1) {
                continue;
            }

            move[x][y] += dfs(nx, ny);
        }

        return ++move[x][y];
    }
}