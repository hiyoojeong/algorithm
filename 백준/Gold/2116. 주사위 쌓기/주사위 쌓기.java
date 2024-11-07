import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 주사위 쌓기
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] dice = new int[N][6];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                dice[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = 0;
        for (int start = 0; start < 6; start++) { // 시작하는 위치
            // 마주보고 있는 면을 찾는다.
            int now = start;
            int next = findNext(now);
            int sum = 0;

            for (int i = 0; i < N; i++) {
                // 마주보고 있지 않은 면 중에서 가장 큰 값을 더한다.
                int tmpMax = 0;
                for (int j = now, k = 0; k < 6; j = (j + 1) % 6, k++) {
                    if (j != now && j != next) {
                        tmpMax = Math.max(dice[i][j], tmpMax);
                    }
                }
                sum += tmpMax;

                // 현재 주사위와 마주보고 있는 다음 주사위의 면을 찾는다.
                if (i < N - 1) {
                    for (int j = 0; j < 6; j++) {
                        if (dice[i][next] == dice[i + 1][j]) {
                            now = j;
                            next = findNext(now);
                            break;
                        }
                    }
                }
            }

            max = Math.max(max, sum);
        }

        System.out.println(max);
    }

    public static int findNext(int i) {
        if (i == 0) {
            return 5;
        } else if (i == 1) {
            return 3;
        } else if (i == 2) {
            return 4;
        } else if (i == 3) {
            return 1;
        } else if (i == 4) {
            return 2;
        } else { // i == 5
            return 0;
        }
    }
}