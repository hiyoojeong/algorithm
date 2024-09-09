
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 프로세서 연결하기
public class Solution {

    static class Pos {

        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Pos [x=" + x + ", y=" + y + "]";
        }
    }

    static int[] dx = {-1, 0, 1, 0}; // 상하좌우
    static int[] dy = {0, -1, 0, 1};

    static int N, map[][];
    static List<Pos> cores;

    static int selected[];
    static int visited[][];

    static int maxCore, minLen;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuffer answer = new StringBuffer();

        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();
            map = new int[N][N];
            cores = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();
                    if (map[i][j] == 1) {
                        cores.add(new Pos(i, j));
                    }
                }
            }

            minLen = Integer.MAX_VALUE;
            maxCore = 0;
            selected = new int[cores.size() + 1];
            visited = new int[N][N];
            combination(1, 0);

            answer.append(String.format("#%d %d\n", test_case, minLen));

        }

        System.out.println(answer);
        sc.close();
    }

    public static void combination(int cnt, int start) {
        // 코어 순서가 만들어졌다.
        if (cnt > cores.size()) {
            dfs(1, 0, 0);
            return;
        }

        for (int i = start; i < cores.size(); i++) {
            selected[cnt] = i;
            map[cores.get(i).x][cores.get(i).y] = cnt; // 코어 번호를 저장
            combination(cnt + 1, i + 1);
        }
    }

    public static void dfs(int cnt, int connect, int len) {
        // 남은 코어수를 모두 저해도 최대 코어 연결수보다 작으면 탐색하지 않는다.
        if (connect + (cores.size() - cnt + 1) < maxCore) {
            return;
        }

        // 최대 코어 연결수, 최소 전선 길이를 업데이트 한다.
        if (maxCore < connect) {
            maxCore = connect;
            minLen = len;
        } else if (maxCore == connect && minLen > len) {
            minLen = len;
        }

        // 모든 코어가 전원에 연결되었다.
        if (cnt > cores.size()) {
            return;
        }

        Pos core = cores.get(selected[cnt]);
        for (int d = 0; d < 4; d++) {
            // 현재 코어를 전원에 연결하는 경우
            int currentLen = connect(core.x, core.y, d);
            if (currentLen != -1) {
                dfs(cnt + 1, connect + 1, len + currentLen);
            }
            // 현재 코어를 전원에 연결하지 않는 경우
            disconnect(core.x, core.y, d);
            dfs(cnt + 1, connect, len);
        }
    }

    public static int connect(int x, int y, int d) {
        int coreNo = map[x][y];
        int len = 0;
        while (true) {
            x += dx[d];
            y += dy[d];

            // 범위를 벗어났다면 전원까지 전선 연결이 완료된 것이다.
            if (x < 0 || y < 0 || x >= N || y >= N) {
                break;
            }

            // 코어가 있거나, 다른 코어의 전선이 있는 경우에는 전원에 연결될 수 없다.
            if (map[x][y] != 0 || visited[x][y] != 0) {
                return -1;
            }

            visited[x][y] = coreNo; // 현재 코어의 전선 연결
            len++;
        }
        return len;
    }

    public static void disconnect(int x, int y, int d) {
        int coreNo = map[x][y];
        while (true) {
            x += dx[d];
            y += dy[d];

            // 범위를 벗어났다면 전원까지 전선 제거가 완료된 것이다.
            if (x < 0 || y < 0 || x >= N || y >= N) {
                break;
            }

            // 코어가 있거나, 다른 코어의 전선이 있는 경우에는 전선 제거가 완료된 것이다.
            if (map[x][y] != 0 || visited[x][y] != coreNo) {
                break;
            }

            visited[x][y] = 0; // 전선 제거
        }
    }

}
