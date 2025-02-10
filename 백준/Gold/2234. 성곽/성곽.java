import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

// 성곽
public class Main {

    static class Node {

        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int[] dr = {0, -1, 0, 1}; // 서,북,동,남
    static int[] dc = {-1, 0, 1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 영역 나누기
        int[][] visited = new int[M][N];
        Queue<Node> q = null;

        Map<Integer, Integer> roomSize = new HashMap<>();

        int roomId = 0, maxSize = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] == 0) {
                    roomId++;
                    visited[i][j] = roomId;

                    q = new ArrayDeque<>();
                    q.add(new Node(i, j));

                    int size = 0;
                    while (!q.isEmpty()) {
                        Node now = q.poll();
                        size++;
                        for (int d = 0; d < 4; d++) {
                            int nr = now.r + dr[d];
                            int nc = now.c + dc[d];
                            if (nr < 0 || nc < 0 || nr >= M || nc >= N
                                || visited[nr][nc] != 0) { // 범위를 벗어나거나, 이미 방문한 경우
                                continue;
                            }
                            if ((map[now.r][now.c] & (1 << d)) != 0) { // 벽이 있는 경우
                                continue;
                            }
                            q.add(new Node(nr, nc));
                            visited[nr][nc] = roomId;
                        }
                    }

                    roomSize.put(roomId, size);
                    maxSize = Math.max(size, maxSize);
                }
            }
        }

        // 근처영역 합쳐보기
        int ans = roomSize.get(visited[0][0]);
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (i < M - 1 && visited[i][j] != visited[i + 1][j]) { // 아래쪽 확인
                    ans = Math.max(roomSize.get(visited[i][j]) + roomSize.get(visited[i + 1][j]), ans);
                }
                if (j < N - 1 && visited[i][j] != visited[i][j + 1]) { // 오른쪽 확인
                    ans = Math.max(roomSize.get(visited[i][j]) + roomSize.get(visited[i][j + 1]), ans);
                }
            }
        }

        System.out.println(roomSize.size()); // 이 성에 있는 방의 개수
        System.out.println(maxSize); // 가장 넓은 방의 넓이
        System.out.println(ans); // 하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기

    }

}
