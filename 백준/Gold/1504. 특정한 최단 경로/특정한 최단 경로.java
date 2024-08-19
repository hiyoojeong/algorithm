import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 특정한 최단 경로
public class Main {

    static class Node {

        int idx, cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    static List<Node>[] graph; // 인접한 리스트 정보 저장
    static int[] dist; // 출발 지점에서 다른 지점까지의 최단 거리를 저장

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 정점의 수
        int E = Integer.parseInt(st.nextToken()); // 간선의 수

        // 인접한 정점 저장 배열 초기화
        graph = new ArrayList[N + 1];
        dist = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // 인접한 정점 저장
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken()); // 정점
            int n2 = Integer.parseInt(st.nextToken()); // 정점
            int cost = Integer.parseInt(st.nextToken()); // 정점 간 거리

            graph[n1].add(new Node(n2, cost));
            graph[n2].add(new Node(n1, cost));
        }

        // 방문해야 하는 정점 저장
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken()); // 방문해야 하는 정점
        int v2 = Integer.parseInt(st.nextToken()); // 방문해야 하는 정점

        // 최단거리 구하기 - 1번 정점에서 출발
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        dijkstra(1);

        int one_to_v1 = dist[v1];
        int one_to_v2 = dist[v2];

        // 최단거리 구하기 - N번 정점에서 출발
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[N] = 0;
        dijkstra(N);

        int v1_to_N = dist[v1];
        int v2_to_N = dist[v2];

        // 최단거리 구하기 - v1번 정점에서 출발
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[v1] = 0;
        dijkstra(v1);

        int v1_to_v2 = dist[v2];
        int v2_to_v1 = v1_to_v2;

        // 1 -> v1 -> v2 -> N
        int v1v2 = 0;
        if (one_to_v1 == Integer.MAX_VALUE || v1_to_v2 == Integer.MAX_VALUE
            || v2_to_N == Integer.MAX_VALUE) {
            v1v2 = Integer.MAX_VALUE;
        } else {
            v1v2 = one_to_v1 + v1_to_v2 + v2_to_N;
        }
        // 1 -> v2 -> v1 -> N
        int v2v1 = 0;
        if (one_to_v2 == Integer.MAX_VALUE || v2_to_v1 == Integer.MAX_VALUE
            || v1_to_N == Integer.MAX_VALUE) {
            v2v1 = Integer.MAX_VALUE;
        } else {
            v2v1 = one_to_v2 + v2_to_v1 + v1_to_N;
        }

        int answer = Math.min(v1v2, v2v1) == Integer.MAX_VALUE ? -1 : Math.min(v1v2, v2v1);
        System.out.println(answer);
    }

    public static void dijkstra(int startNode) {
        Queue<Node> queue = new PriorityQueue<>(((o1, o2) -> o1.cost - o2.cost)); // 비용이 작은 순으로 정렬
        queue.add(new Node(startNode, 0));

        while (!queue.isEmpty()) {
            Node now = queue.poll();
            int nowNode = now.idx;

            for (Node next : graph[nowNode]) {
                int nextNode = next.idx;
                // 'startNode -> nowNode -> nextNode' vs 'startNode -> nextNode' 중에 비용이 더 작은 것을 선택한다.
                // nowNode를 거쳐가는 비용이 더 작은 경우
                if (dist[nowNode] + next.cost < dist[nextNode]) {
                    dist[nextNode] = dist[nowNode] + next.cost; // nextNode를 가는 방법이 업데이트
                    queue.add(
                        new Node(nextNode, dist[nextNode])); // nextNode를 거쳐서 다른 정점으로 가는 방법도 업데이트
                }
            }
        }
    }
}
