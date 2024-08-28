import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

// 불우이웃돕기
class Main {

    static class Edge implements Comparable<Edge> {

        int from, to, len;

        public Edge(int from, int to, int len) {
            this.from = from;
            this.to = to;
            this.len = len;
        }


        @Override
        public int compareTo(Edge o) {
            return this.len - o.len;
        }
    }

    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 컴퓨터 개수

        // 모든 랜선 정보 저장
        int[][] connect = new int[N + 1][N + 1];
        int result = 0;
        for (int i = 1; i <= N; i++) {
            String input = br.readLine();
            for (int j = 1; j <= N; j++) {
                char lan = input.charAt(j - 1);

                if (lan == '0') { // 랜선이 없음
                    connect[i][j] = Integer.MAX_VALUE;
                    continue;
                }

                if (Character.isLowerCase(lan)) {
                    connect[i][j] = lan - 'a' + 1;
                } else {
                    connect[i][j] = lan - 'A' + 27;
                }
                result += connect[i][j];
            }
        }

        // 랜선 정보 저장
        Queue<Edge> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                if (connect[i][j] == Integer.MAX_VALUE && connect[j][i] == Integer.MAX_VALUE) {
                    continue;
                }
                pq.add(new Edge(i, j, Math.min(connect[i][j], connect[j][i])));
            }
        }

        // kruskal
        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        int edgeCount = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (union(edge.from, edge.to)) {
                result -= edge.len;
                edgeCount++;
            }

            if (edgeCount == N - 1) {
                break;
            }
        }

        System.out.println(edgeCount == N - 1 ? result : -1);
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