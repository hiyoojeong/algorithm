import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 전력난
public class Main {

    static class Edge {

        int v, u, cost;

        public Edge(int v, int u, int cost) {
            this.v = v;
            this.u = u;
            this.cost = cost;
        }
    }

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder answer = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            if(m == 0 && n == 0) {
                break;
            }

            parent = new int[m];
            for (int i = 0; i < m; i++) {
                parent[i] = i;
            }

            Queue<Edge> edges = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
            int totalCost = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int v = Integer.parseInt(st.nextToken());
                int u = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                edges.add(new Edge(v, u, cost));
                totalCost += cost;
            }

            int cost = 0, edgeCnt = 0;
            while (!edges.isEmpty()) {
                if (edgeCnt == m - 1) {
                    break;
                }
                Edge e = edges.poll();
                if (union(e.v, e.u)) {
                    cost += e.cost;
                    edgeCnt++;
                }
            }

            answer.append(totalCost - cost).append("\n");
        }

        System.out.println(answer);
    }

    private static int find(int v) {
        if (parent[v] == v) {
            return v;
        }
        return parent[v] = find(parent[v]);
    }

    private static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) {
            return false;
        }

        parent[pa] = pb;
        return true;
    }
}