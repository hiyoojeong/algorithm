// https://school.programmers.co.kr/learn/courses/30/lessons/140107
// https://velog.io/@hiyoojeong/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%A0%90-%EC%B0%8D%EA%B8%B0-JAVA

class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        
		long maxy = 0; // 최대로 가질 수 있는 y
		for (long x = 0; x <= d; x += k) {
			// 좌표 x와 최대 거리 d에 대하여 최대로 가질 수 있는 y를 구한다.
			maxy = (long) Math.sqrt(Math.pow(d, 2) - Math.pow(x, 2));
			// k배 단위로 y값을 가질 수 있으므로 k로 나눈 뒤 1을 더한다.
			answer += (maxy / k) + 1;
		}
        
        return answer;
    }
}