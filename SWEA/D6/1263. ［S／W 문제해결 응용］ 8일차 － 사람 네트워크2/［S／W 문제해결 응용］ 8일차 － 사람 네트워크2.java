import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 사람 네트워크2
class Solution {

    static class Node {
        int v, cost;

        Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }

    static List<Node>[] adj = null;
    static int[] dist = null;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuffer answer = new StringBuffer();

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            adj = new ArrayList[N];
            for (int i = 0; i < N; i++) {
                adj[i] = new ArrayList<>();
                for (int j = 0; j < N; j++) {
                    int cost = Integer.parseInt(st.nextToken());
                    if (cost == 1) {
                        adj[i].add(new Node(j, cost));
                    }
                }
            }

            int minCC = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                dist = new int[N];
                for (int j = 0; j < N; j++) {
                    if (i != j) dist[j] = Integer.MAX_VALUE;
                }
                dijkstra(i);
                int cc = 0;
                for (int j = 0; j < N; j++) {
                    if (i == j) continue;
                    cc += dist[j];
                    if (minCC < cc) break;
                }
                if (minCC > cc) {
                    minCC = cc;
                }
            }

            answer.append(String.format("#%d %d\n", test_case, minCC));
        }

        System.out.println(answer);
    }

    public static void dijkstra(int start) {
        Queue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.add(new Node(start, dist[start]));

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (now.cost > dist[now.v]) continue;

            for (Node next : adj[now.v]) {
                if (dist[next.v] > dist[now.v] + next.cost) {
                    dist[next.v] = dist[now.v] + next.cost;
                    pq.add(new Node(next.v, dist[next.v]));
                }
            }
        }
    }
}
/*
2
5 0 1 1 0 0 1 0 1 1 1 1 1 0 0 0 0 1 0 0 0 0 1 0 0 0
5 0 0 1 1 0 0 0 1 0 0 1 1 0 0 1 1 0 0 0 1 0 0 1 1 0
 */