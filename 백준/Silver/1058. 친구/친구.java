import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

// 친구
public class Main {

    static class Node {

        int idx, cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    static int N;
    static List<Node>[] graph;
    static int[] dist;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력
        N = Integer.parseInt(br.readLine()); // 사람 수

        graph = new ArrayList[N];
        dist = new int[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                if (input.charAt(j) == 'Y') {
                    graph[i].add(new Node(j, 1));
                }
            }
        }

        int cnt = 0; // 가장 유명한 사람의 친구 수
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[i] = 0;

            dijkstra(i);

            int currentCnt = 0; // 현재 사람의 친구 수
            for (int j = 0; j < N; j++) {
                if (i != j && (dist[j] == 1 || dist[j] == 2)) { // 2-친구 카운팅
                    currentCnt++;
                }
            }
            cnt = Math.max(currentCnt, cnt);
        }

        System.out.println(cnt);
    }

    public static void dijkstra(int startNode) {
        Queue<Node> q = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        q.add(new Node(startNode, 0));

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (Node next : graph[now.idx]) {
                if (dist[next.idx] > dist[now.idx] + next.cost) { // next까지 now 친구를 거쳐서 가는게 더 가까운 사이이다.
                    dist[next.idx] = dist[now.idx] + 1;
                    q.add(next);
                }
            }
        }
    }
}
