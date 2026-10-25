class Solution {
    
    int[] parent = null;
    
    public int solution(int n, int[][] computers) {
        parent = new int[n];
        for(int i=0; i<n; i++) {
            parent[i] = i;
        }
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(computers[i][j] == 1) {
                    union(i, j);
                }
            }
        }
        
        int answer = 0;
        for(int i=0; i<n; i++) {
            if(parent[i] == i) {
                answer++;
            }
        }
        return answer;
    }
    
    int find(int v) {
        if(v == parent[v]) {
            return v;
        }
        return parent[v] = find(parent[v]);
    }
    
    boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        
        if(pa == pb) {
            return false;
        }
        parent[pa] = pb;
        return true;
    }
    
}