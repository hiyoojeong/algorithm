class Solution {
    static char[] ch = {'A', 'E', 'I', 'O', 'U'};
    static int cnt = 0, result = 0;
    public int solution(String word) {
        dfs(word, "", 0);
        return result;
    }
    
    public void dfs(String word, String curWord, int pos) {
        if(result != 0) {
            return;
        }
        
        if(curWord.equals(word)) {
            result = cnt;
        }
        
        if(pos == 5) {
            return;
        }
        
        for(int i=0; i<5; i++) {
            cnt++;
            dfs(word, curWord+ch[i], pos+1);
        }
    }
}