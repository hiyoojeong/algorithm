import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 보이저 1호
public class Main {

    static class Pos {

        int r, c, d;

        public Pos(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static char[] direct = {'U', 'R', 'D', 'L'};

    static char[][] map;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        st = new StringTokenizer(br.readLine());
        int PR = Integer.parseInt(st.nextToken()) - 1;
        int PC = Integer.parseInt(st.nextToken()) - 1;

        int maxCnt = 0;
        String res = null;
        for (int i = 0; i < 4; i++) {
            int r = PR;
            int c = PC;

            boolean[][][] visited = new boolean[N][M][4];
            visited[r][c][i] = true;

            Pos next = new Pos(r + dr[i], c + dc[i], i);
            int cnt = 0;
            boolean isInfinite = false;
            while (true) {
                cnt++;

                // 시그널이 블랙홀이 있는 칸을 만나거나 항성계를 벗어난 경우
                if (next.r < 0 || next.c < 0 || next.r >= N || next.c >= M
                    || map[next.r][next.c] == 'C') {
                    if (maxCnt < cnt) {
                        res = direct[i] + "\n" + cnt;
                        maxCnt = cnt;
                    }
                    break;
                }

                // 같은 조건으로 방문한 적 있는 경우, 무한히 전파되는 상황으로 판단
                if (visited[next.r][next.c][next.d]) {
                    res = direct[i] + "\nVoyager";
                    isInfinite = true;
                    break;
                }

                // 진행
                r = next.r;
                c = next.c;

                // 다음 칸 정보
                next = getNextPos(r, c, next.d);
            }

            if (isInfinite) {
                break;
            }
        }

        System.out.println(res);
    }

    private static Pos getNextPos(int pr, int pc, int d) {
        if (map[pr][pc] == '/') {
            if (d == 0) {
                d = 1;
            } else if (d == 1) {
                d = 0;
            } else if (d == 2) {
                d = 3;
            } else if (d == 3) {
                d = 2;
            }
        } else if (map[pr][pc] == '\\') {
            if (d == 0) {
                d = 3;
            } else if (d == 1) {
                d = 2;
            } else if (d == 2) {
                d = 1;
            } else if (d == 3) {
                d = 0;
            }
        }
        return new Pos(pr + dr[d], pc + dc[d], d);
    }

}