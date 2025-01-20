import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 로봇 청소기
public class Main {

    static class Pos {

        int r, c, cnt, cleanInfo;

        public Pos(int r, int c, int cnt, int cleanInfo) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.cleanInfo = cleanInfo;
        }
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static final int clean = 0, wall = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int[][] map = null;
        boolean[][][] visited = null;

        while (true) {
            // 입력
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            if (w == 0 && h == 0) {
                break;
            }

            map = new int[h][w];
            Pos start = null;

            int dirtyCnt = 0;
            for (int i = 0; i < h; i++) {
                String input = br.readLine();
                for (int j = 0; j < w; j++) {
                    char ch = input.charAt(j);
                    if (ch == 'o') {
                        start = new Pos(i, j, 0, 0);
                        map[i][j] = clean;
                    } else if (ch == '.') {
                        map[i][j] = clean;
                    } else if (ch == '*') {
                        map[i][j] = ++dirtyCnt;
                    } else if (ch == 'x') {
                        map[i][j] = wall;
                    }
                }
            }

            // bfs + 비트마스킹
            Queue<Pos> q = new ArrayDeque<>();
            q.add(start);

            visited = new boolean[h][w][1 << dirtyCnt];
            visited[start.r][start.c][0] = true;

            int res = -1;
            while (!q.isEmpty()) {
                Pos p = q.poll();

                if (p.cleanInfo == (1 << dirtyCnt) - 1) { // 더러운 칸을 모두 깨끗한 칸으로 바꾼 경우
                    res = p.cnt;
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int nr = p.r + dr[i];
                    int nc = p.c + dc[i];
                    int ncnt = p.cnt + 1;

                    if (nr < 0 || nc < 0 || nr >= h || nc >= w
                        || map[nr][nc] == wall) { // 범위를 벗어났거나 벽인 경우 continue
                        continue;
                    }

                    if (visited[nr][nc][p.cleanInfo]) { // 같은 조건으로 방문한 적 있는 경우 continue
                        continue;
                    }

                    int nextCleanInfo = p.cleanInfo;
                    if (map[nr][nc] > 0) { // 깨끗하게 만든 더러운 칸의 번호를 기록!
                        nextCleanInfo |= (1 << (map[nr][nc] - 1));
                    }

                    q.add(new Pos(nr, nc, ncnt, nextCleanInfo));
                    visited[nr][nc][nextCleanInfo] = true;
                }
            }

            sb.append(res + "\n");

        }

        System.out.println(sb);
    }

}