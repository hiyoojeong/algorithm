import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 인터넷 설치
public class Main {

    static class Node {

        int v, cost;

        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }

    static List<Node>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 컴퓨터 수
        int P = Integer.parseInt(st.nextToken()); // 케이블 수
        int K = Integer.parseInt(st.nextToken()); // 무료 케이블 수

        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        int left = 0, right = 0;
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adjList[from].add(new Node(to, cost));
            adjList[to].add(new Node(from, cost));

            right = Math.max(right, cost);
        }

        int min = -1;
        while (left <= right) {
            int mid = (left + right) / 2; // 한계비용 (= 원장이 내는 최대 비용)
            if (dijkstra(mid, N, K)) { // 한계비용으로 케이블 수를 만족함 -> 조금 더 한계비용을 줄여도 됨
                min = mid;
                right = mid - 1;
            } else { // 한계 비용으로 케이블 수가 부족함 -> 한계비용을 늘려야 함
                left = mid + 1;
            }
        }

        System.out.println(min);
    }

    private static boolean dijkstra(int limitCost, int N, int K) {
        Queue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.add(new Node(1, 0));

        int[] cable = new int[N + 1];
        Arrays.fill(cable, Integer.MAX_VALUE);
        cable[1] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (now.cost > cable[now.v]) {
                continue;
            }

            for (Node next : adjList[now.v]) {
                // 한계비용을 넘은 경우, 무료 케이블을 사용한다
                int freeCable = now.cost; // --> 지금 Queue에 저장되고 있는 cost는 사용한 무료 케이블 수이고
                if (next.cost > limitCost) { // --> adjList에 저장되어 있는 cost는 케이블 설치 비용이다
                    freeCable++;
                }
                if (cable[next.v] > freeCable) {
                    cable[next.v] = freeCable;
                    pq.add(new Node(next.v, freeCable));
                }
            }
        }

        return cable[N] <= K;
    }
}