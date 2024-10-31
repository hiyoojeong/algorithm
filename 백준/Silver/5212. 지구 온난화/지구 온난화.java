import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 지구 온난화
public class Main {

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer answer = new StringBuffer();

        st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        char[][] map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            map[i] = input.toCharArray();
        }

        int minR = 9, maxR = 0, minC = 9, maxC = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'X') {
                    int cnt = 0;
                    for (int k = 0; k < 4; k++) {
                        int nr = dr[k] + i;
                        int nc = dc[k] + j;
                        if (nr < 0 || nc < 0 || nr >= R || nc >= C || map[nr][nc] == '.') {
                            cnt++;
                        }
                    }
                    if (cnt >= 3) {
                        map[i][j] = '-';
                    } else {
                        minR = Math.min(minR, i);
                        maxR = Math.max(maxR, i);
                        minC = Math.min(minC, j);
                        maxC = Math.max(maxC, j);
                    }
                }
            }
        }

        for (int i = minR; i <= maxR; i++) {
            for (int j = minC; j <= maxC; j++) {
                if(map[i][j]=='-') {
                    map[i][j] = '.';
                }
                answer.append(map[i][j]);
            }
            answer.append("\n");
        }

        System.out.println(answer);
    }
}