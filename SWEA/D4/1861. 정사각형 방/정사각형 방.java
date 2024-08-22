import java.util.Scanner;

// 1861. 정사각형 방
public class Solution {

    static int N, arr[][];
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int maxRoomNo, maxCnt;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        StringBuffer answer = new StringBuffer();
        for (int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();
            arr = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }

            maxRoomNo = N * N + 1;
            maxCnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    dfs(arr[i][j], i, j, 1);
                }
            }

            answer.append(String.format("#%d %d %d\n", test_case, maxRoomNo, maxCnt));
        }

        System.out.println(answer);

        sc.close();
    }

    public static void dfs(int roomNo, int r, int c, int cnt) {
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            // 이동하려는 방이 존재하지 않거나, 이동하려는 방에 적힌 숫자가 현재 방에 적힌 숫자보다 정확히 1 더 큰 경우가 아니다.
            if ((nr < 0 || nc < 0 || nr >= N || nc >= N) || arr[nr][nc] != arr[r][c] + 1) {
                // 이동할 수 있는 개수가 최대값을 넘겼다.
                // 이동할 수 있는 개수가 최대값과 같다면, 시작방에 적힌 수가 더 작다.
                if ((cnt > maxCnt) || (cnt == maxCnt && roomNo < maxRoomNo)) {
                    maxCnt = cnt;
                    maxRoomNo = roomNo;
                }
                continue;
            }

            // 이동하려는 방으로 이동
            dfs(roomNo, nr, nc, cnt + 1);
        }
    }

}
