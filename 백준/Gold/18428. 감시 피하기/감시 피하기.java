import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 감시 피하기
public class Main {

    static class Pos {

        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int N;
    static char map[][];
    static List<Pos> teachers = new ArrayList<>();
    static boolean answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = st.nextToken().charAt(0);
                if (map[i][j] == 'T') {
                    teachers.add(new Pos(i, j));
                }
            }
        }

        dfs(0, 0);
        System.out.println(answer ? "YES" : "NO");
    }

    private static void dfs(int pos, int cnt) {
        if (answer) {
            return;
        }

        if (cnt == 3) {
            // 정확히 3개의 장애물을 설치한 경우, 감시를 피했는지 확인
            answer = isPossible();
            return;
        }

        for (int i = pos; i < N * N; i++) {
            int r = i / N;
            int c = i % N;

            // 장애물을 놓는 경우
            if (map[r][c] == 'X') {
                char tmp = map[r][c];
                map[r][c] = 'O';
                dfs(i + 1, cnt + 1);
                map[r][c] = tmp;
            }

//            // 장애물을 놓지 않는 경우
//            dfs(i + 1, cnt);
        }
    }

    private static boolean isPossible() {
        for (Pos t : teachers) {
            for (int i = 0; i < 4; i++) {
                int r = t.r;
                int c = t.c;
                while (r < N && c < N && r >= 0 && c >= 0 && map[r][c] != 'O') {
                    if (map[r][c] == 'S') {
                        return false;
                    }
                    r += dr[i];
                    c += dc[i];
                }
            }
        }
        return true;
    }
}