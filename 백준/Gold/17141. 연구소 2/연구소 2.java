import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

// 연구소 2
public class Main {

    static class Pos {

        int x, y, time;

        public Pos(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }

        @Override
        public boolean equals(Object obj) {
            Pos pos = (Pos) obj;
            return this.x == pos.x && this.y == pos.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static int N, M;
    static int[][] map;
    static int[][] visited;
    static List<Pos> virus;

    static int minTime = Integer.MAX_VALUE;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static final int BLANK = 0;
    static final int WALL = 1;
    static final int VIRUS = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        virus = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int val = Integer.parseInt(st.nextToken());
                map[i][j] = val;

                // 바이러스를 놓을 수 있는 칸을 저장해둔다.
                if (val == VIRUS) {
                    virus.add(new Pos(i, j, 0));
                }
            }
        }

        // M개로 이루어진 바이러스 조합을 찾고, 모든 빈 칸에 바이러스를 퍼뜨리는 최소 시간을 구한다.
        dfs(0, 0, new ArrayList<>());

        if (minTime == Integer.MAX_VALUE) {
            minTime = -1;
        }
        System.out.println(minTime);
    }

    public static void dfs(int s, int n, List<Pos> choose) {
        // M개로 이루어진 바이러스 조합을 찾았다면
        // 해당 조합으로 모든 빈 칸에 바이러스를 퍼뜨리는 최소 시간을 구한다.
        if (n == M) {
            int res = bfs(choose);
            minTime = Math.min(res, minTime);
            return;
        }

        // 바이러스 조합을 찾는다.
        for (int i = s; i < virus.size(); i++) {
            if (!choose.contains(virus.get(i))) {
                choose.add(virus.get(i));
                dfs(i, n + 1, choose);
                choose.remove(choose.size() - 1);
            }
        }
    }

    private static int bfs(List<Pos> choose) {
        Queue<Pos> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        for (Pos p : choose) {
            queue.add(new Pos(p.x, p.y, p.time));
            visited[p.x][p.y] = true;
        }

        int time = 0; // 바이러스를 퍼뜨린 시간
        while (!queue.isEmpty()) {
            Pos now = queue.poll();

            // 바이러스를 퍼뜨린 시간을 업데이트한다.
            time = Math.max(time, now.time);

            // 바이러스를 퍼뜨리는 최소 시간보다 크다면
            // 더이상 탐색할 필요가 없다.
            if (minTime < time) {
                break;
            }

            // 상하좌우 인접한 빈 칸으로 바이러스를 복제한다.
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                int nt = now.time + 1;

                // 범위를 벗어난 경우이다.
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                    continue;
                }

                // 아직 퍼지지 않은 빈칸이 있다면, 바이러스를 복제한다.
                if (!visited[nx][ny] && map[nx][ny] != WALL) {
                    queue.add(new Pos(nx, ny, nt));
                    visited[nx][ny] = true;
                }
            }
        }

        // 모든 빈 칸에 바이러스가 퍼졌다면, 바이러스를 퍼뜨린 시간을 반환한다.
        if (isFull(visited)) {
            return time;
        }
        return Integer.MAX_VALUE;
    }

    public static boolean isFull(boolean[][] visited) {
        // 모든 빈 칸에 바이러스가 퍼졌는지 확인한다.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != WALL && !visited[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

}