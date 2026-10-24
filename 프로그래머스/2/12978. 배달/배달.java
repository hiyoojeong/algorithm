import java.util.*;

class Solution {
    
    class Node {
        int v, cost;
        Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }
    
    List<Node>[] graph = null;
    int[] dist = null;
    
    public int solution(int N, int[][] road, int K) {
        
        graph = new ArrayList[N];
        dist = new int[N];
        for(int i=0; i<N; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE / 2;
        }
    
        for(int i=0; i<road.length; i++) {
            int a = road[i][0] - 1;
            int b = road[i][1] - 1;
            int c = road[i][2];
            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }
        
        dijkstra(0);
        
        int answer = 0;
        for(int i=0; i<N; i++) {
            if(dist[i] <= K) answer++;
        }
            
        return answer;
    }
    
    public void dijkstra(int start) {
        dist[start] = 0;
        
        Queue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.add(new Node(start, dist[start]));
        
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            if(now.cost > dist[now.v]) {
                continue;
            }
            
            for(Node next : graph[now.v]) {
                if(dist[next.v] > dist[now.v] + next.cost) {
                    dist[next.v] = dist[now.v] + next.cost;
                    pq.add(new Node(next.v, dist[next.v]));
                }
            }
        }
    }
}