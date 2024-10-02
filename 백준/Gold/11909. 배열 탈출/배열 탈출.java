import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 배열 탈출
class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dist[i][j] : (0,0)에서 (i,j)까지 가는데 최소비용
        int[][] dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                int leftCost = Integer.MAX_VALUE, upCost = Integer.MAX_VALUE;
                if (i > 0) {
                    upCost = dist[i - 1][j] + Math.max(map[i][j] - map[i - 1][j] + 1, 0);
                }
                if (j > 0) {
                    leftCost = dist[i][j - 1] + Math.max(map[i][j] - map[i][j - 1] + 1, 0);
                }
                dist[i][j] = Math.min(leftCost, upCost);
            }
        }

        System.out.println(dist[N - 1][N - 1]);
    }
}