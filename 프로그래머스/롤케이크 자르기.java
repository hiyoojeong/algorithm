// https://school.programmers.co.kr/learn/courses/30/lessons/132265?language=java
// https://velog.io/@hiyoojeong/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EB%A1%A4%EC%BC%80%EC%9D%B4%ED%81%AC-%EC%9E%90%EB%A5%B4%EA%B8%B0-JAVA

import java.util.HashMap;
import java.util.HashSet;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;

		int N = topping.length; // 배열 topping의 길이
		HashSet<Integer> part1 = new HashSet<>();
		HashMap<Integer, Integer> part2 = new HashMap<>();

		// 일단 롤케이크를 "철수=1, 동생=N-1" 로 나누어준다.
		part1.add(topping[0]);
		for (int i = 1; i < N; i++)
			part2.put(topping[i], part2.getOrDefault(topping[i], 0) + 1);

		// 동생이 철수에게 토핑을 하나씩 주면서 토핑 개수가 같아지는 지점을 찾는다.
		// 철수=1, 동생=N-1
		// 철수=2, 동생=N-2
		// 철수=3, 동생=N-3
		for (int i = 1; i < N; i++) {
			part1.add(topping[i]); // 찔끔 얻기
			part2.put(topping[i], part2.get(topping[i]) - 1); // 찔끔 떼주기
			if (part2.get(topping[i]) == 0)
				part2.remove(topping[i]);

			if (part1.size() == part2.size()) // 토핑 개수가 같아지는지 확인
				answer++;
		}

		return answer;
    }
}