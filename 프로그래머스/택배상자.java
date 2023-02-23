// https://school.programmers.co.kr/learn/courses/30/lessons/131704
// https://velog.io/@hiyoojeong/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%ED%83%9D%EB%B0%B0%EC%83%81%EC%9E%90-JAVA

import java.util.Stack;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        
        // 상자의 수
		int N = order.length;

		// 보조 컨테이너 벨트
		Stack<Integer> stack = new Stack<>();

		// 현재 트럭에 넣어야하는 상자 번호를 가리키는 인덱스
		int orderidx = 0;
        
		for (int i = 1; i <= N; i++) {
        	// 현재 트럭에 넣어야하는 상자는 트럭에 넣는다.
			if (order[orderidx] == i) {
				orderidx++;
				answer++;
				
                // 보조 컨테이너 벨트에서 트럭에 넣어야하는 상자가 있다면 트럭에 넣는다.
				while (stack.size() != 0) {
					int s = stack.peek();
					if (s == order[orderidx]) {
						stack.pop();
						orderidx++;
						answer++;
					} else {
						break;
					}
				}
			} 
            // 현재 트럭에 넣을 수 없는 상자는 보조 컨테이너 벨트에 넣는다.
            else {
				stack.push(i);
			}
		}

		return answer;

    }
}