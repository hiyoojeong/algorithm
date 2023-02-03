// https://school.programmers.co.kr/learn/courses/30/lessons/148653
// https://velog.io/@hiyoojeong/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EB%A7%88%EB%B2%95%EC%9D%98-%EC%97%98%EB%A6%AC%EB%B2%A0%EC%9D%B4%ED%84%B0-JAVA

class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        while (storey != 0) {
			int one = storey % 10;
			int ten = (storey / 10) % 10;

			if (one > 5) {
				answer += (10 - one);
				storey += 10;
			} else if (one == 5) {
				answer += one;
				storey += (ten < 5 ? 0 : 10);
			} else {
				answer += one;
			}

			storey /= 10;
		}
        
        return answer;
    }
}