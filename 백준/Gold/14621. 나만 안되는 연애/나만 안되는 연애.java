import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 나만 안되는 연애
class Main {

    static class Edge implements Comparable<Edge> {

        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }


        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder answer = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 학교의 수
        int M = Integer.parseInt(st.nextToken()); // 도로의 수

        // 정점의 학교성별 저장
        boolean[] types = new boolean[N + 1]; // 1 : man , -1 : woman
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            types[i] = (st.nextToken()).equals("M");
        }

        // 정점 저장
        Queue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if (types[from] == types[to]) { // 같은 학교성별이면 연결하지 않음
                continue;
            }
            pq.add(new Edge(from, to, cost));
        }

        // kruskal
        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        int result = 0;
        int edgeCount = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (union(edge.from, edge.to)) {
                result += edge.cost;
                edgeCount++;
            }
        }

        if (edgeCount == N - 1) {
            System.out.println(result);
        } else {
            System.out.println(-1);
        }
    }

    public static int find(int v) {
        if (parents[v] == v) {
            return v;
        }
        return parents[v] = find(parents[v]);
    }

    public static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) {
            return false;
        }

        parents[pa] = pb;
        return true;
    }
}