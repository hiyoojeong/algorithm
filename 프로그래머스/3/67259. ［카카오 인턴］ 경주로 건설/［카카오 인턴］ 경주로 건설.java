import java.util.*;

class Solution {
    
    class Node {
        int r, c, d, cost;
        Node(int r, int c, int d, int cost) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.cost = cost;
        }
    }
    
    public int solution(int[][] board) {
        int[] dr = new int[]{1, -1, 0, 0};
        int[] dc = new int[]{0, 0, -1, 1};
        
        int N = board.length;
        int[][][] cost = new int[N][N][4];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                for(int k=0; k<4; k++) {
                    cost[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0,0,-1,0));
        
        int answer = Integer.MAX_VALUE;
        while(!q.isEmpty()) {
            Node now = q.poll();
            
            if(now.r == N-1 && now.c == N-1) {
                answer = Math.min(answer, now.cost);
            }
            
            for(int nd=0; nd<4; nd++) {
                int nr = now.r + dr[nd];
                int nc = now.c + dc[nd];
                
                if(nr < 0 || nc < 0 || nr >= N || nc >= N || board[nr][nc] == 1) continue;
                
                int nCost = now.cost + (now.d == -1 || now.d == nd ? 0 : 500) + 100;
                if(cost[nr][nc][nd] > nCost) {
                    q.add(new Node(nr, nc, nd, nCost));
                    cost[nr][nc][nd] = nCost;
                }
            }
        }
        
        return answer;
    }
   
}