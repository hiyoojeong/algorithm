import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 주난의 난
public class Main {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder answer = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int x1 = Integer.parseInt(st.nextToken()) - 1; // 주난이의 위치
        int y1 = Integer.parseInt(st.nextToken()) - 1;
        int x2 = Integer.parseInt(st.nextToken()) - 1; // 범인의 위치
        int y2 = Integer.parseInt(st.nextToken()) - 1;

        boolean[][] map = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                char ch = input.charAt(j);
                if (ch == '*' || ch == '0') { // 장애물 없음
                    map[i][j] = false;
                } else { // 장애물 있음
                    map[i][j] = true;
                }
            }
        }

        int cnt = 0;
        while (true) {
            cnt++;

            Queue<int[]> q = new ArrayDeque<>();
            boolean[][] visited = new boolean[N][M];
            q.add(new int[]{x1, y1});
            visited[x1][y1] = true;
            while (!q.isEmpty()) {
                int[] now = q.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = now[0] + dx[i];
                    int ny = now[1] + dy[i];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) {
                        continue;
                    }
                    if (nx == x2 && ny == y2) { // 범인
                        System.out.println(cnt);
                        System.exit(0);
                    }
                    visited[nx][ny] = true;
                    if (map[nx][ny]) { // 장애물이 있으면, 없애고 탐색종료
                        map[nx][ny] = false;
                    } else { // 장애물이 없으면, 탐색진행
                        q.add(new int[]{nx, ny});
                    }
                }
            }
        }
    }
}