import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 최소 스패닝 트리
public class Main {

    static class Edge {

        int u, v, cost;

        public Edge(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }
    }

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        parent = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }

        Queue<Edge> edges = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges.add(new Edge(u, v, cost));
        }

        int edgeCount = 0;
        long cost = 0;
        while (!edges.isEmpty()) {
            Edge e = edges.poll();
            if (union(e.u, e.v)) {
                edgeCount++;
                cost += e.cost;
            }
            if (edgeCount == V - 1) {
                break;
            }
        }

        System.out.println(cost);
    }

    public static int find(int v) {
        if (v == parent[v]) {
            return v;
        }
        return parent[v] = find(parent[v]);
    }

    public static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa == pb) {
            return false;
        }
        parent[pa] = pb;
        return true;
    }
}