import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 벽 부수고 이동하기
class Main {

    static class Pos {

        int r, c, cnt, left;

        public Pos(int r, int c, int cnt, int left) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.left = left;
        }

    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder answer = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        Queue<Pos> q = new ArrayDeque<>();
        q.add(new Pos(0, 0, 1, 1));
        boolean[][][] visited = new boolean[2][N][M];
        visited[1][0][0] = true;

        while (!q.isEmpty()) {
            Pos now = q.poll();
            if (now.r == N - 1 && now.c == M - 1) {
                System.out.println(now.cnt);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];
                int ncnt = now.cnt + 1;

                // 범위를 벗어난 경우
                if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
                    continue;
                }

                int nleft = map[nr][nc] == 1 ? now.left - 1 : now.left;

                // 벽이 있는데 벽을 부술 수 없는 경우
                if (nleft == -1) {
                    continue;
                }
                // 같은 상황으로 같은 위치에 방문한 적 있는 경우
                if (visited[nleft][nr][nc]) {
                    continue;
                }

                q.add(new Pos(nr, nc, ncnt, nleft));
                visited[nleft][nr][nc] = true;
            }
        }

        System.out.println(-1);
    }
}