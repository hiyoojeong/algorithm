import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

// 최소비용 구하기 2
public class Main {

    static class Node {

        int v, cost;

        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }

    static List<Node>[] adj;
    static int[] distance, parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder answer = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        distance = new int[N + 1];
        parent = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adj[from].add(new Node(to, cost));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        // start를 시작으로 다른 정점까지의 최단 거리 구하기
        dijkstra(start);
        // end를 끝으로 경유지 구하기
        Stack<Integer> route = new Stack<>();
        int tmp = end;
        while (true) {
            route.push(tmp);
            if (tmp == start) {
                break;
            }
            tmp = parent[tmp];
        }

        answer.append(distance[end] + "\n");
        answer.append(route.size() + "\n");
        while (!route.isEmpty()) {
            answer.append(route.pop() + " ");
        }

        System.out.println(answer);
    }

    public static void dijkstra(int start) {
        Queue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);

        distance[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if(now.cost > distance[now.v]) {
                continue;
            }

            for (Node next : adj[now.v]) {
                if (distance[next.v] > distance[now.v] + next.cost) {
                    distance[next.v] = distance[now.v] + next.cost;
                    parent[next.v] = now.v;
                    pq.add(new Node(next.v, distance[next.v]));
                }
            }
        }
    }
}