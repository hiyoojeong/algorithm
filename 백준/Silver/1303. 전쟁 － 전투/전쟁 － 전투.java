import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 전쟁 - 전투
public class Main {

    static class Pos {

        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static final int W = 1;
    static final int B = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken()); // 세로크기
        int N = Integer.parseInt(st.nextToken()); // 가로크기

        int[][] map = new int[N][M];
        int[][] visited = new int[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                if (input.charAt(j) == 'W') {
                    map[i][j] = W;
                } else {
                    map[i][j] = B;
                }
            }
        }

        int w = 0, b = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] == 0) {
                    int current = map[i][j];

                    Queue<Pos> q = new LinkedList<>();
                    q.add(new Pos(i, j));

                    visited[i][j] = current;

                    int cnt = 1;
                    while (!q.isEmpty()) {
                        Pos pos = q.poll();

                        for (int k = 0; k < 4; k++) {
                            int nx = pos.x + dx[k];
                            int ny = pos.y + dy[k];

                            if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                                continue;
                            }

                            if (visited[nx][ny] == 0 && map[nx][ny] == current) {
                                q.add(new Pos(nx, ny));
                                visited[nx][ny] = current;

                                cnt++;
                            }
                        }
                    }

                    if (current == W) {
                        w += Math.pow(cnt, 2);
                    } else {
                        b += Math.pow(cnt, 2);
                    }
                }
            }
        }

        System.out.println(w + " " + b);
    }

}