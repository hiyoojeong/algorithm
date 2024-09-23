import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 임계경로
class Main {

    static class Edge {

        int u, cost;

        public Edge(int u, int cost) {
            this.u = u;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder answer = new StringBuilder();

        int N = Integer.parseInt(br.readLine()); // 도시의 수
        int M = Integer.parseInt(br.readLine()); // 도로의 수

        int[] in = new int[N + 1];
        List<Edge>[] adjList = new ArrayList[N + 1];
        List<Edge>[] radjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
            radjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            in[e]++;
            adjList[s].add(new Edge(e, c));
            radjList[e].add(new Edge(s, c));
        }

        st = new StringTokenizer(br.readLine());
        int startCity = Integer.parseInt(st.nextToken());
        int endCity = Integer.parseInt(st.nextToken());

        // 정방향 그래프에서 각 지점에 도착하는 최대시간을 구해준다.
        int[] maxCost = new int[N + 1]; // 각 지점에 도착하는 최대시간

        Queue<Integer> q = new ArrayDeque<>();
        q.add(startCity);

        while (!q.isEmpty()) {
            int v = q.poll();

            for (Edge edge : adjList[v]) {
                in[edge.u]--;
                if (maxCost[edge.u] < maxCost[v] + edge.cost) { // 각 지점에 도착하는 최대시간
                    maxCost[edge.u] = maxCost[v] + edge.cost;
                }
                if (in[edge.u] == 0) { // 진입차수가 0이 된 경우
                    q.add(edge.u);
                }
            }
        }

        // 역방향 그래프에서 도착지점까지의 최대시간으로 오는 경로를 모두 찾는다.
        int visitedCnt = 0;
        boolean[] visited = new boolean[N + 1];

        q.add(endCity);

        while (!q.isEmpty()) {
            int v = q.poll();
            for (Edge edge : radjList[v]) {
                if (maxCost[edge.u] + edge.cost
                    == maxCost[v]) { // 직전도시에서 현재도시까지 도로를 지나는 경우 = 최대시간으로 오는 경로인 경우
                    visitedCnt++;
                    if (!visited[edge.u]) { // 임계경로에 포함되면서 미방문 도시 -> 또 해당도시까지 최대시간으로 오는 경로를 찾기 위해 Queued에 삽입
                        q.add(edge.u);
                        visited[edge.u] = true;
                    }
                }
            }
        }

        System.out.println(maxCost[endCity]);
        System.out.println(visitedCnt);
    }
}