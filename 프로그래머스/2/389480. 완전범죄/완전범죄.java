class Solution {
    
    int amin = Integer.MAX_VALUE;
    
    int[][] info;
    int n;
    int m;
    
    boolean[][][] dp;
    
    public int solution(int[][] info, int n, int m) {
        this.info = info;
        this.n = n;
        this.m = m;
        this.dp = new boolean[this.info.length+1][121][121];
        
        dfs(0, 0, 0);
        return this.amin == Integer.MAX_VALUE ? -1 : this.amin;
    }
    
    public void dfs(int i, int a, int b) { 
        if(this.dp[i][a][b]) return;
        if(a > this.amin) return;
        if(a >= this.n) return;
        if(b >= this.m) return;
        
        if(i == this.info.length) {
            if(a < this.amin) this.amin = a;
            return;
        }
        
        this.dp[i][a][b] = true;
        
        dfs(i+1, a + info[i][0], b);
        dfs(i+1, a, b + info[i][1]);
    }
}