import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 달이 차오른다, 가자.
class Main {

    static class Pos {

        int r, c, cnt, key;

        public Pos(int r, int c, int cnt, int key) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.key = key;
        }
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] map = new char[N][M];
        Queue<Pos> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[N][M][64];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j] == '0') {
                    q.add(new Pos(i, j, 0, 0));
                    visited[i][j][0] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            Pos now = q.poll();
            if (map[now.r][now.c] == '1') {
                System.out.println(now.cnt);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];
                int ncnt = now.cnt + 1;
                int nkey = now.key;

                if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
                    continue;
                }
                // 벽인 경우
                if (map[nr][nc] == '#') {
                    continue;
                }
                // 문인데 대응하는 열쇠가 없는 경우
                if (map[nr][nc] >= 'A' && map[nr][nc] <= 'F') {
                    if ((nkey & (1 << (map[nr][nc] - 'A'))) == 0) {
                        continue;
                    }
                }
                // 열쇠인 경우 -> 열쇠를 줍는다.
                if (map[nr][nc] >= 'a' && map[nr][nc] <= 'f') {
                    nkey |= (1 << (map[nr][nc] - 'a'));
                }
                // 같은 상태로 같은 위치에 방문한 적 있는 경우
                if (visited[nr][nc][nkey]) {
                    continue;
                }

                q.add(new Pos(nr, nc, ncnt, nkey));
                visited[nr][nc][nkey] = true;
            }
        }
        System.out.println(-1);
    }
}