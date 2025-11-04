class Solution {

    boolean[] nums;
    int result = 0;

    public int solution(int n, int[][] q, int[] ans) {
        nums = new boolean[n+1];

        for(int i=1; i<=n; i++) {
            nums[i] = true;
            dfs(1, i, n, q, ans);
            nums[i] = false;
        }

        return result;
    }

    void dfs(int cnt, int start, int n, int[][] q, int[] ans) {
        if(cnt == 5) {
            for(int i=0; i<q.length; i++) {
                int tmp = 0;
                for (int j = 0; j < q[i].length; j++) {
                    if (nums[q[i][j]]) {
                        tmp++;
                    }
                }
                if (tmp != ans[i]) {
                    return;
                }
            }
            result++;
            return;
        }

        for(int i=start+1; i<=n; i++) {
            // 넣기
            nums[i] = true;
            dfs(cnt+1, i, n, q, ans);
            // 안넣기
            nums[i] = false;
        }
    }
}
