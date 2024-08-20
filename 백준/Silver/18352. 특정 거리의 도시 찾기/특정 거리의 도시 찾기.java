import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 특정 거리의 도시 찾기
public class Main {

    static class Node {

        int idx, cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    static List<Node>[] graph;
    static int[] dist;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer answer = new StringBuffer();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 도시 수
        int M = Integer.parseInt(st.nextToken()); // 도로 수
        int K = Integer.parseInt(st.nextToken()); // 제한거리
        int X = Integer.parseInt(st.nextToken()); // 출발

        // 초기화
        graph = new ArrayList[N + 1];
        dist = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        // 간선 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph[s].add(new Node(e, 1));
        }

        // 최단 거리 정보 저장
        dist[X] = 0;
        dijkstra(X);

        // 최단거리가 제한거리(K)인 도시 출력
        Queue<Integer> pq = new PriorityQueue<>();
         for (int i = 1; i <= N; i++) {
            if (dist[i] == K) {
                pq.add(i);
            }
        }

        if(pq.size() == 0) {
            System.out.println(-1);
        } else {
            while(!pq.isEmpty()) {
                System.out.println(pq.poll());
            }
        }
    }

    public static void dijkstra(int startNode) {
        Queue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.add(new Node(startNode, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            for (Node next : graph[now.idx]) {
                if (dist[next.idx] > dist[now.idx] + next.cost) {
                    dist[next.idx] = dist[now.idx] + next.cost;
                    pq.add(new Node(next.idx, dist[next.idx]));
                }
            }
        }
    }
}
