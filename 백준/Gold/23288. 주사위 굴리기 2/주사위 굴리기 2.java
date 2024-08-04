import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 주사위 굴리기 2
public class Main {

    static class Node {

        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[][] dice = {
        {0, 2, 0},
        {4, 1, 3},
        {0, 5, 0},
        {0, 6, 0}
    };
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static Node current = new Node(0, 0);
    static int d = 0;

    static int[][] map;
    static int N, M, K;

    static int score = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // K번 이동하며 점수를 합산한다.
        for (int i = 0; i < K; i++) {
            move();
            getScore();
            changeDistance();
        }

        System.out.println(score);
    }

    public static void move() {
        // 범위를 벗어난 경우에는 방향을 반대로 변경한다.
        if (!isRange(current.x + dx[d], current.y + dy[d])) {
            d = (d + 2) % 4;
        }

        // 이동한 좌표 업데이트
        current.x += dx[d];
        current.y += dy[d];

        // 이동한 주사위 도면 업데이트
        // 동
        if (d == 0) {
            int tmp = dice[1][2];
            dice[1][2] = dice[1][1];
            dice[1][1] = dice[1][0];
            dice[1][0] = dice[3][1];
            dice[3][1] = tmp;
        }
        // 남
        else if (d == 1) {
            int tmp = dice[3][1];
            dice[3][1] = dice[2][1];
            dice[2][1] = dice[1][1];
            dice[1][1] = dice[0][1];
            dice[0][1] = tmp;
        }
        // 서
        else if (d == 2) {
            int tmp = dice[1][0];
            dice[1][0] = dice[1][1];
            dice[1][1] = dice[1][2];
            dice[1][2] = dice[3][1];
            dice[3][1] = tmp;
        }
        // 북
        else if (d == 3) {
            int tmp = dice[0][1];
            dice[0][1] = dice[1][1];
            dice[1][1] = dice[2][1];
            dice[2][1] = dice[3][1];
            dice[3][1] = tmp;
        }
    }

    public static void getScore() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(current.x, current.y));

        boolean[][] visited = new boolean[N][M];
        visited[current.x][current.y] = true;

        int num = map[current.x][current.y];
        int cnt = 1;

        // 이동할 수 있는 칸에는 모두 정수 num이 있어야 한다.
        while (!queue.isEmpty()) {
            Node now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                // 범위를 벗어났거나, 이미 방문했거나, 갈 수 없는 곳(현재 놓여져있는 숫자와 다른 숫자)인 경우 넘긴다.
                if (!isRange(nx, ny) || visited[nx][ny] || map[nx][ny] != num) {
                    continue;
                }

                queue.add(new Node(nx, ny));
                visited[nx][ny] = true;

                cnt++;
            }
        }

        // 점수는 num와 cnt를 곱한 값이다.
        score += num * cnt;
    }

    public static void changeDistance() {
        int under = dice[3][1]; // 아랫면에 있는 정수
        int num = map[current.x][current.y]; // 주사위가 있는 칸에 있는 정수

        // 아랫면에 있는 정수가 더 크다면, 이동방향을 90도 시계방향으로 회전한다.
        if (under > num) {
            d = (d + 1) % 4;
        }
        // 주사위가 있는 칸에 있는 정수 정수가 더 크다면, 이동방향을 90도 반시계방향으로 회전한다.
        else if (under < num) {
            d = ((d - 1) + 4) % 4;
        }
    }

    public static boolean isRange(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }

}
