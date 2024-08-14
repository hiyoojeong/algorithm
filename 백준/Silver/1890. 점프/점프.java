import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 점프
public class Main {

    static int[] dx = {0, 1};
    static int[] dy = {1, 0};

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력
        int N = Integer.parseInt(br.readLine()); // 게임 판의 크기
        int[][] move = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                move[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 갈 수 있는 경로
        long [][] dp = new long[N][N];
        dp[0][0] = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == N - 1 && j == N - 1) {
                    break;
                }

                for (int k = 0; k < 2; k++) {
                    int nx = i + dx[k] * move[i][j];
                    int ny = j + dy[k] * move[i][j];

                    if (nx >= N || ny >= N) {
                        continue;
                    }

                    dp[nx][ny] += dp[i][j];
                }
            }
        }

        System.out.println(dp[N - 1][N - 1]);
    }

}
