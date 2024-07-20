import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 치즈
public class Main {

    static class Pos {

        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, M;
    static int[][] map;
    static int[][] visited;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static final int CHEESE = 1;
    static final int BLANK = 0;
    static final int OUTSIDE = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 2][M + 2];
        visited = new int[N + 2][M + 2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        checkOutside(0, 0);

        int time = 0;
        while (true) {
            // 녹은 치즈 개수를 저장한다.
            List<Pos> list = new ArrayList<>();

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    if (map[i][j] == CHEESE) {

                        // 외부 공기와 접촉하는 변의 개수를 센다.
                        int cnt = 0;

                        for (int k = 0; k < 4; k++) {
                            int nx = i + dx[k];
                            int ny = j + dy[k];

                            if (map[nx][ny] == OUTSIDE) {
                                cnt++;
                            }
                        }

                        // 외부 공기와 접촉하는 변의 개수가 2개 이상이라면, 녹는다.
                        if (cnt >= 2) {
                            map[i][j] = BLANK;
                            list.add(new Pos(i, j));
                        }
                    }
                }
            }

            // 녹은 치즈가 없다면, 탐색을 종료한다.
            if(list.size() == 0) {
                break;
            }

            // 녹은 치즈 영역에서부터 외부 영역을 체크해준다.
            for (Pos pos : list) {
                checkOutside(pos.x, pos.y);
            }

            time++;
        }

        System.out.println(time);

    }

    public static void checkOutside(int x, int y) {
        Queue<Pos> queue = new LinkedList<>();
        queue.add(new Pos(x, y));

        visited[x][y] = 1;

        map[x][y] = OUTSIDE;

        while (!queue.isEmpty()) {

            Pos pos = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = pos.x + dx[i];
                int ny = pos.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N + 2 || ny >= M + 2) {
                    continue;
                }

                // 아직 확인하지 않은 영역에서 외부로 노출된 부분이 있다면, 체크해준다.
                if (visited[nx][ny] == 0 && map[nx][ny] == BLANK) {
                    map[nx][ny] = OUTSIDE;
                    visited[nx][ny] = 1;

                    queue.add(new Pos(nx, ny));
                }

            }

        }
    }

}