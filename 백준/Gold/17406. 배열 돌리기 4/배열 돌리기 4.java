import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 배열 돌리기 4
public class Main {

    static class Rotate {

        int r, c, s;

        public Rotate(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }

    static int N, M, K, arr[][], copy[][], tmp[][], result = Integer.MAX_VALUE;
    static List<Rotate> rotates;

    static int[] dr = {0, 1, 0, -1}; // 우하좌상
    static int[] dc = {1, 0, -1, 0};

    static boolean[] visited;
    static int[] order;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][M + 1];
        tmp = new int[N + 1][M + 1];
        visited = new boolean[K];
        order = new int[K];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        rotates = new ArrayList<>();
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            rotates.add(new Rotate(r, c, s));
        }

        dfs(0);
        System.out.println(result);
    }

    private static void dfs(int cnt) {
        if (cnt == K) {
            copy = getCopyArr(); // 원본배열복사
            for (int i = 0; i < K; i++) { // 순서대로 회전
                Rotate now = rotates.get(order[i]);
                rotate(now.r, now.c, now.s);
            }
            result = Math.min(getMinRowSum(), result); // 최소 행 총합
            return;
        }

        for (int i = 0; i < K; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            order[cnt] = i;
            dfs(cnt + 1);
            visited[i] = false;
        }
    }

    private static int[][] getCopyArr() {
        copy = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                copy[i][j] = arr[i][j];
            }
        }
        return copy;
    }

    private static void rotate(int r, int c, int s) {
        // 회전 후 정보를 tmp 배열에 저장
        for (int i = 1; i <= s; i++) {
            int nr = r - i;
            int nc = c - i;
            for (int d = 0; d < 4; d++) {
                for (int j = 0; j < i * 2; j++) {
                    tmp[nr + dr[d]][nc + dc[d]] = copy[nr][nc];
                    nr += dr[d];
                    nc += dc[d];
                }
            }
        }

        // tmp배열 -> arr배열
        for (int i = r - s; i <= r + s; i++) {
            for (int j = c - s; j <= c + s; j++) {
                if (i == r && j == c) {
                    continue;
                }
                copy[i][j] = tmp[i][j];
            }
        }
    }

    private static int getMinRowSum() {
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            int sum = 0;
            for (int j = 1; j <= M; j++) {
                sum += copy[i][j];
            }
            min = Math.min(sum, min);
        }

        return min;
    }
}
