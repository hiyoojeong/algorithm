import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 꽃길
public class Main {

    static int N;
    static int[][] map;
    static int[][] visited;

    static final int CNT = 3;
    static int answer = Integer.MAX_VALUE;

    static int[] dr = {0, -1, 1, 0, 0};
    static int[] dc = {0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(1, 0, 0);

        if (answer == Integer.MAX_VALUE) {
            answer = 0;
        }
        System.out.println(answer);

    }

    public static void dfs(int r, int cost, int cnt) {
        if (cnt == CNT) {
            answer = Math.min(answer, cost);
            return;
        }

        for (int i = r; i < N - 1; i++) {
            for (int j = 1; j < N - 1; j++) {

                // 씨앗을 심을 수 있는 장소라면
                if (visited[i][j] == 0) {

                    // 꽃잎이 펴도 다른 꽃과 겹치지 않는지 확인한다.
                    boolean check = true;

                    for (int k = 0; k < 5; k++) {
                        int nr = i + dr[k];
                        int nc = j + dc[k];

                        if (visited[nr][nc] == 1) {
                            check = false;
                            break;
                        }
                    }

                    // 다른 꽃과 겹치지 않는다면, 심어본다.
                    if (check) {
                        // 심는다.
                        int sum = 0;
                        for (int k = 0; k < 5; k++) {
                            int nr = i + dr[k];
                            int nc = j + dc[k];

                            sum += map[nr][nc];
                            visited[nr][nc] = 1;
                        }

                        // 다른 꽃을 심으러 떠난다.
                        dfs(i, cost + sum, cnt + 1);

                        // 심었던 흔적을 지운다.
                        for (int k = 0; k < 5; k++) {
                            int nr = i + dr[k];
                            int nc = j + dc[k];

                            visited[nr][nc] = 0;
                        }

                    }

                }

            }
        }

    }


}