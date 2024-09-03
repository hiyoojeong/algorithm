import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

// 방향 전환
public class Solution {

    static class Pos {

        int x, y, cnt;
        boolean isHorizontal;

        public Pos(int x, int y, int cnt, boolean isHorizontal) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.isHorizontal = isHorizontal;
        }
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        StringBuffer answer = new StringBuffer();
        for (int test_case = 1; test_case <= T; test_case++) {
            int result = bfs(sc.nextInt() + 100, sc.nextInt() + 100, sc.nextInt() + 100,
                sc.nextInt() + 100);
            answer.append(String.format("#%d %d\n", test_case, result));
        }

        System.out.println(answer);

        sc.close();
    }

    public static int bfs(int sx, int sy, int ex, int ey) {
        Queue<Pos> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[201][201][2]; // visited[][][0]: 가로이동 체크, visited[][][1]: 세로이동 체크

        q.add(new Pos(sx, sy, 0, true)); // 가로이동
        visited[sx][sy][0] = true;

        q.add(new Pos(sx, sy, 0, false)); // 세로이동
        visited[sx][sy][1] = false;

        while (!q.isEmpty()) {
            Pos now = q.poll();

            if (now.x == ex && now.y == ey) {
                return now.cnt;
            }

            // 이전에 가로이동을 해서 현재 세로이동을 해야한다.
            if (now.isHorizontal) {
                for (int i = 2; i < 4; i++) {
                    int nx = now.x + dx[i];
                    int ny = now.y + dy[i];

                    // 범위를 벗어난 경우
                    if (nx < 0 || ny < 0 || nx > 200 || ny > 200) {
                        continue;
                    }
                    // 현재위치에서 세로이동을 이미 했던 경우
                    if (visited[nx][ny][1]) {
                        continue;
                    }

                    q.add(new Pos(nx, ny, now.cnt + 1, false));
                    visited[nx][ny][1] = true;
                }
            }

            // 이전에 세로이동을 해서 현재 가로이동을 해야한다.
            if (!now.isHorizontal) {
                for (int i = 0; i < 2; i++) {
                    int nx = now.x + dx[i];
                    int ny = now.y + dy[i];

                    // 범위를 벗어난 경우
                    if (nx < 0 || ny < 0 || nx > 200 || ny > 200) {
                        continue;
                    }
                    // 현재위치에서 가로이동을 이미 했던 경우
                    if (visited[nx][ny][0]) {
                        continue;
                    }

                    q.add(new Pos(nx, ny, now.cnt + 1, true));
                    visited[nx][ny][0] = true;
                }
            }
        }

        return -1;
    }
}
