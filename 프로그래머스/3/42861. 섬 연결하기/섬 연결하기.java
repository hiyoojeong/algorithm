import java.util.*;

class Solution {
    
    class Node {
        int a, b, cost;
        Node(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }
    }
    
    int[] parent = null;
    
    public int solution(int n, int[][] costs) {
        Queue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        
        for(int i=0; i<costs.length; i++) {
            pq.add(new Node(costs[i][0], costs[i][1], costs[i][2]));
        }
        
        parent = new int[n];
        for(int i=0; i<n; i++) {
            parent[i] = i;
        }
        
        int answer = 0;
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            if(union(node.a, node.b)) answer += node.cost;
        }
        return answer;
    }
    
    public int find(int v) {
        if(v == parent[v]) {
            return v;
        }
        return parent[v] = find(parent[v]);
    }
    
    public boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if(pa == pb) {
            return false;
        }
        parent[pa] = pb;
        return true;
    }
}