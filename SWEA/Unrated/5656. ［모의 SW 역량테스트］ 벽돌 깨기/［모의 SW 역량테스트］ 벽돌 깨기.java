import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

// 벽돌 깨기
class Solution {

    static class Pos {

        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Pos [r=" + r + ", c=" + c + "]";
        }
    }

    static int N, W, H;
    static int[][] map;
    static int[] selected;

    static int totalCnt, minCnt;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        StringBuffer answer = new StringBuffer();
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();
            W = sc.nextInt();
            H = sc.nextInt();
            map = new int[H][W];
            selected = new int[N];
            totalCnt = 0;
            minCnt = Integer.MAX_VALUE;

            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    map[i][j] = sc.nextInt();
                    if (map[i][j] != 0) {
                        totalCnt++;
                    }
                }
            }

            permutation(0);

            answer.append(String.format("#%d %d\n", test_case, minCnt));
        }
        System.out.println(answer);
        sc.close();
    }

    private static void permutation(int n) {
        if (n == N) {
            // 순열 만들어짐
            int cnt = totalCnt - getCnt(copy());
            if (minCnt > cnt) {
                minCnt = cnt;
            }
            return;
        }

        for (int i = 0; i < W; i++) {
            selected[n] = i;
            permutation(n + 1);
        }
    }

    private static int[][] copy() {
        int[][] copy = new int[H][W];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                copy[i][j] = map[i][j];
            }
        }
        return copy;
    }

    private static int getCnt(int[][] map) {
        int score = 0;

        for (int i = 0; i < N; i++) {
            int c = selected[i];
            for (int r = 0; r < H; r++) {
                // 맨 위층에 있는 벽돌을 발견했다.
                if (map[r][c] != 0) {
                    // 그 위치부터 벽돌을 제거한다.
                    score += remove(map, r, c);
                    // 벽돌을 아래로 내린다.
                    gravity(map);
                    break;
                }
            }
        }

        return score;
    }

    private static int remove(int[][] map, int r, int c) {
        int cnt = 0;

        Queue<Pos> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[H][W];
        q.add(new Pos(r, c));
        while (!q.isEmpty()) {
            Pos now = q.poll();
            cnt++;

            int power = map[now.r][now.c];
            map[now.r][now.c] = 0;

            for (int p = 0; p < power; p++) {
                for (int i = 0; i < 4; i++) {
                    int nr = now.r + dr[i] * p;
                    int nc = now.c + dc[i] * p;
                    if (nr < 0 || nc < 0 || nr >= H || nc >= W || visited[nr][nc]) {
                        continue;
                    }
                    if (map[nr][nc] != 0) { // 벽돌을 발견하면 다시 큐에 넣기
                        q.add(new Pos(nr, nc));
                        visited[nr][nc] = true;
                    }
                }
            }
        }

        return cnt;
    }

    private static void gravity(int[][] map) {
        for (int c = 0; c < W; c++) {
            int br = -1;
            for (int r = H - 1; r >= 0; r--) {
                if (map[r][c] == 0 && br == -1) { // 빈칸 발견
                    br = r;
                }
                if (map[r][c] != 0 && br != -1) { // 공중에 떠있는 벽돌 발견
                    int tmp = map[r][c];
                    map[r][c] = map[br][c];
                    map[br][c] = tmp;
                    br--;
                }
            }
        }
    }
}