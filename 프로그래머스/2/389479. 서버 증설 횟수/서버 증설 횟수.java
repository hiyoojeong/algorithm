class Solution {
    
    public int solution(int[] players, int m, int k) {
        
        int result = 0; 
        
        int[] server = new int[24]; // 시간별 서버 반납 정보
        int n = 0; // 서버 개수
        
        for(int i=0; i<24; i++) {
            n -= server[i]; // 서버 반남
            
            if((n+1)*m <= players[i]) {
                // 추가로 필요한 서버 개수
                int need = players[i] / m;
                need-=n;
                
                // 서버 증설
                n+=need;
                result+=need;
                
                if(i+k < 24) {
                    server[i+k]+=need; // k 시간 이후 반납
                }
            }
            
        }
        
        return result;
    }
}