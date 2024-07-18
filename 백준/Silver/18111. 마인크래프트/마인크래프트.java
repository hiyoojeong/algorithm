import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 마인크래프트
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int block = Integer.parseInt(st.nextToken()); // 초기 블록 개수

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int minTime = Integer.MAX_VALUE;
        int maxHeight = Integer.MIN_VALUE;

        // 땅의 높이 별로, 해당 높이를 만드는 데 사용하는 블록 개수와 시간을 계산한다.
        // 최소 시간 소요되는 높이가 여러 개라면, 가장 높은 층을 선택하므로 위층에서 아래층으로 내려가며 계산한다.
        for (int height = 256; height >= 0; height--) {

            int time = 0, usingBlock = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] <= height) { // 맵 높이가 땅 높이보다 작거나 같으므로, 블록을 쌓아야 한다.
                        int dist = height - map[i][j];
                        time += dist;
                        usingBlock += dist;
                    } else if (map[i][j] > height) { // 맵 높이가 땅 높이보다 크므로, 블록을 제거해야 한다.
                        int dist = map[i][j] - height;
                        time += dist * 2;
                        usingBlock -= dist;
                    }
                }
            }

            // 사용한 블록 개수가 초기 블록 개수보다 크다면, 해당 높이는 만들 수 없으므로 제외한다.
            if (usingBlock > block) {
                continue;
            }

            // 최소 시간이면 업데이트한다.
            if (time < minTime) {
                minTime = time;
                maxHeight = height;
            }
        }

        System.out.println(minTime + " " + maxHeight);

    }


}