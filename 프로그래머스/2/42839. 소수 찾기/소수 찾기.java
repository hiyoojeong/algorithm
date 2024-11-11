import java.util.*;

class Solution {
    
    Set<Integer> notPrime = new HashSet<>();
    Set<Integer> prime = new HashSet<>();
    int MAX = 1;
    
    public int solution(String numbers) {
        // 최대값 만들기
        MAX = (int) Math.pow(10, numbers.length());
        
        // 소수가 아닌 수 만들기 : 에라토스테네스의 체
        notPrime.add(0);
        notPrime.add(1);
        for(int i=2; i*i<MAX; i++) {
            if(notPrime.contains(i)) {
                continue;
            }
            for(int j=i*i; j<MAX; j+=i) {
                notPrime.add(j);
            }
        }
        
        // 수를 조합하며 소수인지 아닌지 확인
        combi(numbers, "", new boolean[numbers.length()]);
        return prime.size();
    }
    
    public void combi(String numbers, String tmp, boolean[] visited) {
        if(tmp.length() > 0) {
            int value = Integer.parseInt(tmp);
            if(!notPrime.contains(value)) {
                prime.add(value);
            }
        }
        
        if(tmp.length() == numbers.length()) {
            return;
        }
        
        for(int i=0; i<numbers.length(); i++) {
            if(visited[i]) {
                continue;
            }
            visited[i] = true;
            tmp += numbers.charAt(i);
            combi(numbers, tmp, visited);
            tmp = tmp.substring(0, tmp.length()-1);
            visited[i] = false;
        }
    }
}