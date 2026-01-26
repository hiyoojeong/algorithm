import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 18500 미네랄 2
public class Main {

    static class Node {
        int r, c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int R, C;
    static char[][] map;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            play(Integer.parseInt(st.nextToken()), i % 2 == 0);
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void play(int pos, boolean isLeft) {
        // 1. 파괴된다.
        if (isLeft) {
            for (int i = 0; i < C; i++) {
                if (map[R - pos][i] == 'x') {
                    map[R - pos][i] = '.';
                    break;
                }
            }
        } else {
            for (int i = C - 1; i >= 0; i--) {
                if (map[R - pos][i] == 'x') {
                    map[R - pos][i] = '.';
                    break;
                }
            }
        }

        // 2. 떨어져야 하는 미네랄 탐색한다.
        Queue<Node> q = new ArrayDeque<>();
        int[][] visit = new int[R][C];

        int area = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] != 'x' || visit[i][j] != 0) {
                    continue;
                }

                area++;
                q.add(new Node(i, j));
                visit[i][j] = area;

                while (!q.isEmpty()) {
                    Node cur = q.poll();
                    for (int d = 0; d < 4; d++) {
                        int nr = cur.r + dr[d];
                        int nc = cur.c + dc[d];
                        if (nr < 0 || nc < 0 || nr >= R || nc >= C || map[nr][nc] == '.' || visit[nr][nc] != 0) {
                            continue;
                        }

                        q.add(new Node(nr, nc));
                        visit[nr][nc] = area;
                    }
                }
            }
        }

        // 3. 각 클러스터 별 얼마나 떨어져야 하는지 구한다.
        Map<Integer, Integer> dist = new HashMap<>();
        for (int i = R - 1; i >= 0; i--) {
            for (int j = 0; j < C; j++) {
                if (visit[i][j] == 0) {
                    continue;
                }

                int fallCnt = 0;
                for (int k = i + 1; k < R; k++) {
                    if (visit[k][j] != 0 && visit[k][j] != visit[i][j]) {
                        break;
                    }
                    fallCnt++;
                }
                dist.put(visit[i][j], Math.min(dist.getOrDefault(visit[i][j], Integer.MAX_VALUE), fallCnt));
            }
        }

        // 4. 떨어진다.
        for (int i = R - 1; i >= 0; i--) {
            for (int j = 0; j < C; j++) {
                if (visit[i][j] != 0 && dist.get(visit[i][j]) > 0) {
                    map[i + dist.get(visit[i][j])][j] = 'x';
                    map[i][j] = '.';
                }
            }
        }
    }
}