import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 22116 창영이와 퇴근

public class Main {

    static class Node {
        int r, c, cost;

        Node(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        int[][] dist = new int[N][N];
        boolean[][] visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        dist[0][0] = 0;

        Queue<Node> q = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        q.add(new Node(0, 0, dist[0][0]));

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if(visited[cur.r][cur.c]) {
                continue;
            }

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
                    continue;
                }

                int cost = Math.max(dist[cur.r][cur.c], Math.abs(map[cur.r][cur.c] - map[nr][nc]));
                if (cost < dist[nr][nc]) {
                    q.add(new Node(nr, nc, cost));
                    dist[nr][nc] = cost;
                }
            }
        }

        System.out.println(dist[N - 1][N - 1]);
    }
}