import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution {

    static int N;
    static int[][] map;

    static int[] dr = {1, 1, -1, -1};
    static int[] dc = {1, -1, -1, 1};

    static Set<Integer> visited;
    static int sRow, sCol;

    static int maxCnt;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuffer answer = new StringBuffer();

        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            // 입력
            N = sc.nextInt();
            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            // 탐색
            maxCnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // i, j가 시작점
                    visited = new HashSet<>(); // 방문 체크
                    visited.add(map[i][j]);

                    sRow = i; // 시작 위치 저장
                    sCol = j;

                    dfs(0, i, j, 0, 0, 1); // 탐색
                }
            }

            if (maxCnt == 0) { // 경로 탐색 실패
                maxCnt = -1;
            }
            answer.append("#" + test_case + " " + maxCnt + "\n");
        }

        System.out.println(answer);
        sc.close();

    }

    public static void dfs(int d, int r, int c, int width, int height, int cnt) {
        if ((r - 1 == sRow && c + 1 == sCol)) { // 지도 상에서 바로 위 바로 오른쪽이 시작점이면, 디저트 투어 성공
            maxCnt = Math.max(cnt, maxCnt);
            return;
        }

        // 현재 방향으로 이동하는 경우
        int nr = r + dr[d];
        int nc = c + dc[d];

        if (nr >= 0 && nc >= 0 && nr < N && nc < N) {
            if (!visited.contains(map[nr][nc])) {
                visited.add(map[nr][nc]);
                // 가로 증가
                if (d == 0) {
                    dfs(d, nr, nc, width + 1, height, cnt + 1);
                }
                // 세로 증가
                else if (d == 1) {
                    dfs(d, nr, nc, width, height + 1, cnt + 1);
                }
                // 가로 감소 (감소할 값이 있어야 한다)
                else if (d == 2 && width > 0) {
                    dfs(d, nr, nc, width - 1, height, cnt + 1);
                }
                // 세로 감소 (감소할 값이 있어야 한다)
                else if (d == 3 && height > 0) {
                    dfs(d, nr, nc, width, height - 1, cnt + 1);
                }
                visited.remove(map[nr][nc]);
            }

        }
        // 방향을 바꾸는 경우
        // 가로로 1번 이상 이동한 경우에만, 방향전환 가능
        if (d == 0 && width > 0) {
            dfs(d + 1, r, c, width, height, cnt);
        }
        // 가로로 1번 이상 세로로 1번 이상 이동한 경우에만, 방향전환 가능
        if (d == 1 && (width > 0 && height > 0)) {
            dfs(d + 1, r, c, width, height, cnt);
        }
        // 이동한 가로만큼 반대방향으로 이동했을 경우(가로가 0이 되는 경우)에만, 방향전환 가능
        if (d == 2 && width == 0) {
            dfs(d + 1, r, c, width, height, cnt);
        }
    }

}
