import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 호석이 두 마리 치킨
public class Main {

    static class Node {

        int idx, cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    static List<Node>[] graph;
    static int[][] dist;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 건물의 수
        int M = Integer.parseInt(st.nextToken()); // 도로의 수

        // 인접리스트 배열 초기화
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // 인접리스트 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            graph[n1].add(new Node(n2, 1));
            graph[n2].add(new Node(n1, 1));
        }

        // 최단거리 배열 초기화
        dist = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0;
        }

        // 최단거리 구하기
        for (int i = 1; i <= N; i++) { // i가 출발 정점이다.
            dijkstra(new Node(i, 0));
        }

        // 치킨집 2개를 골라, "모든 건물에서 가장 가까운 치킨집까지 왕복하는 최단 시간의 총합"을 최소화할 수 있는지 확인한다.
        // 어떤 두 치킨집을 잡아도 도로를 따라서 오고 가는 방법이 존재함이 보장되므로, INF 처리를 할 필요가 없다.
        int mini = 0, minj = 0;
        int minTime = Integer.MAX_VALUE;
        for (int i = 1; i <= N - 1; i++) { // 치킨집 선택
            for (int j = i + 1; j <= N; j++) { // 치킨집 선택

                int time = 0;
                for (int k = 1; k <= N; k++) { // 다른 건물 간의 최단 거리 선택
                    if (k == i || k == j) {
                        continue;
                    }

                    time += Math.min(dist[i][k], dist[j][k]); // 건물과 가까운 치킨집을 선택한다.
                }

                if(minTime > time) {
                    minTime = time;
                    mini = i;
                    minj = j;
                }
            }
        }

        // 왕복 처리
        minTime *= 2;

        System.out.println(mini + " " + minj + " " + minTime);
    }

    public static void dijkstra(Node start) {
        Queue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        queue.add(start);

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            for (Node next : graph[now.idx]) {
                // start에서 next까지 가는데, now를 거쳐가는 것이 더 가까운 경우
                if (dist[start.idx][next.idx] > dist[start.idx][now.idx] + next.cost) {
                    dist[start.idx][next.idx] = dist[start.idx][now.idx] + next.cost;
                    queue.add(new Node(next.idx, dist[start.idx][next.idx]));
                }
            }
        }
    }
}
