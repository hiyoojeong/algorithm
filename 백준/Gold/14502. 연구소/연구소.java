import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 연구소
class Main {

    static class Pos {

        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, M, map[][];
    static List<Pos> virus;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int maxSafeArea;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 세로
        M = Integer.parseInt(st.nextToken()); // 가로

        map = new int[N][M];
        virus = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) { // 바이러스 저장
                    virus.add(new Pos(i, j));
                }
            }
        }

        subset(0);
        System.out.println(maxSafeArea);
    }

    public static void subset(int wallCnt) {
        if (wallCnt == 3) {
            int safeArea = getSafeArea();
            if (maxSafeArea < safeArea) {
                maxSafeArea = safeArea;
            }
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    subset(wallCnt + 1);
                    map[i][j] = 0;
                }
            }
        }
    }

    public static int getSafeArea() {
        // 바이러스 전염
        Queue<Pos> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        for (Pos v : virus) {
            q.add(v);
            visited[v.x][v.y] = true;
        }

        while (!q.isEmpty()) {
            Pos now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                // 범위를 벗어났거나, 방문했던 칸
                if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) {
                    continue;
                }

                // 빈칸이면 바이러스가 퍼짐
                if (map[nx][ny] == 0) {
                    q.add(new Pos(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }

        // 안전영역 구하기
        int safeArea = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] != 1) { // 방문한적 없고(바이러스가 아니고), 벽이 아닌 경우
                    safeArea++;
                }
            }
        }
        return safeArea;
    }

}