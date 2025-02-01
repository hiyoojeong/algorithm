import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 트리와 쿼리
public class Main {

    static class Node {

        int v, depth;

        public Node(int v, int depth) {
            this.v = v;
            this.depth = depth;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 정점의 수
        int R = Integer.parseInt(st.nextToken()); // 루트의 번호
        int Q = Integer.parseInt(st.nextToken()); // 쿼리의 수

        List<Integer>[] adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adjList[u].add(v);
            adjList[v].add(u);
        }

        // parent, depth 기록
        int[] parent = new int[N + 1];
        int[] depth = new int[N + 1];
        Queue<Integer> q = new ArrayDeque<>();
        Queue<Node> pq = new PriorityQueue<>((o1, o2) -> o2.depth - o1.depth);

        parent[R] = -1;
        depth[R] = 1;
        q.add(R);
        pq.add(new Node(R, depth[R]));

        while (!q.isEmpty()) {
            int now = q.poll();
            for (int next : adjList[now]) {
                if (parent[next] != 0) {
                    continue;
                }
                parent[next] = now;
                depth[next] = depth[now] + 1;
                q.add(next);
                pq.add(new Node(next, depth[next]));
            }
        }

        // 서브쿼리에 속한 정점 수 기록
        int[] subCnt = new int[N + 1];
        while (!pq.isEmpty()) {
            int now = pq.poll().v;
            subCnt[now]++;
            if (parent[now] != -1) {
                subCnt[parent[now]] += subCnt[now];
            }
        }

        for(int i=0; i<Q; i++) {
            int v = Integer.parseInt(br.readLine());
            sb.append(subCnt[v] + "\n");
        }

        System.out.println(sb);
    }

}