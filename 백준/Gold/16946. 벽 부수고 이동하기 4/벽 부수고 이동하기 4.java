import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

// 벽 부수고 이동하기
public class Main {

    static class Node {

        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static Map<Integer, Integer> area = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder answer = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        // 영역번호와 이동할 수 있는 칸의 개수를 map에 저장한다.
        Queue<Node> q = new ArrayDeque<>();
        int[][] visited = new int[N][M];
        int areaNo = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 && visited[i][j] == 0) {
                    q.add(new Node(i, j));
                    visited[i][j] = areaNo;

                    int cnt = 0;
                    while (!q.isEmpty()) {
                        Node now = q.poll();

                        cnt++;

                        for (int k = 0; k < 4; k++) {
                            int nr = now.r + dr[k];
                            int nc = now.c + dc[k];
                            if (nr < 0 || nc < 0 || nr >= N || nc >= M || visited[nr][nc] != 0
                                || map[nr][nc] != 0) {
                                continue;
                            }
                            q.add(new Node(nr, nc));
                            visited[nr][nc] = areaNo;
                        }
                    }

                    area.put(areaNo, cnt);
                    areaNo++;
                }
            }
        }

        // 벽을 중심으로 4방향 영역번호를 찾아, 이동 가능한 칸의 개수를 구한다.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Set<Integer> areaNos = new HashSet<>();
                if (map[i][j] != 0) {
                    for (int k = 0; k < 4; k++) {
                        int nr = i + dr[k];
                        int nc = j + dc[k];
                        if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
                            continue;
                        }
                        if (visited[nr][nc] != 0) {
                            areaNos.add(visited[nr][nc]);
                        }
                    }
                    for (int key : areaNos) {
                        map[i][j] += area.get(key); // 벽이 이미 1이므로, 주변에 이동할 수 있는 칸의 개수만 더한다.
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                answer.append(map[i][j] % 10);
            }
            answer.append("\n");
        }
        System.out.println(answer);
    }
}