// https://school.programmers.co.kr/learn/courses/30/lessons/120956
// https://velog.io/@hiyoojeong/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%98%B9%EC%95%8C%EC%9D%B4-JAVA

class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        
		for (String bab : babbling) {
			bab = bab.replaceAll("aya|ye|woo|ma", "");

			if (bab.equals(""))
				answer++;
		}
        
        return answer;
    }
}