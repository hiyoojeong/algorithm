import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 정복자
class Main {

    static class Edge {

        public Edge(int edge, int cost) {
            this.edge = edge;
            this.cost = cost;
        }

        int edge, cost;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder answer = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 도시의 개수
        int M = Integer.parseInt(st.nextToken()); // 도로의 개수
        int t = Integer.parseInt(st.nextToken()); // 한 번 정복할 때마 증가하는 도로의 비용

        // 간선 입력
        List<Edge>[] adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adj[to].add(new Edge(from, cost)); // 양방향
            adj[from].add(new Edge(to, cost));
        }

        // prim
        Queue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        boolean[] visited = new boolean[N + 1];

        visited[1] = true;
        for (Edge e : adj[1]) { // 1번 정점과 인접한 정점 저장
            pq.add(e);
        }

        int cnt = 0, cost = 0;
        while (!pq.isEmpty()) {
            Edge now = pq.poll();

            if (visited[now.edge]) {
                continue;
            }

            visited[now.edge] = true; // 현재 정점 방문 체크
            cost += now.cost + (t * cnt++); // 현재 정점 정복하기 위한 '도로 비용 + 증가하는 도로 비용'

            for (Edge e : adj[now.edge]) { // 인접한 정점 저장
                if (visited[e.edge]) {
                    continue;
                }
                pq.add(e);
            }

        }

        System.out.println(cost);
    }
}