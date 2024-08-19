import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 알고스팟
public class Main {

    static class Node {

        int x, y, cnt;

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

    }

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken()); // 가로 = 열
        int N = Integer.parseInt(st.nextToken()); // 세로 = 행

        int[][] map = new int[N + 1][M + 1]; // 미로 정보
        for (int i = 1; i <= N; i++) {
            String input = br.readLine();
            for (int j = 1; j <= M; j++) {
                map[i][j] = input.charAt(j - 1) == '0' ? 0 : 1;
            }
        }

        // dist[i][j] : (1,1)에서 (i,j)까지 가는 최소 벽뿌 횟수
        int[][] dist = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (i == 1 && j == 1) {
                    continue;
                }

                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        // dijkstra
        Queue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.cnt - o2.cnt);
        queue.add(new Node(1, 1, 0));

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx <= 0 || ny <= 0 || nx > N || ny > M) {
                    continue;
                }

                // (1,1)에서 (nx,ny)까지 가는데 (now.x, now.y)를 거쳐서 가는 게 벽뿌 횟수가 적을 경우
                if(dist[nx][ny] > dist[now.x][now.y] + map[nx][ny]) {
                    dist[nx][ny] = dist[now.x][now.y] + map[nx][ny];
                    queue.add(new Node(nx, ny, dist[nx][ny]));
                }
            }
        }

        System.out.println(dist[N][M]);
    }
}
