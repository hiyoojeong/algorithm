// https://school.programmers.co.kr/learn/courses/30/lessons/138476
// https://velog.io/@hiyoojeong/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EA%B7%A4-%EA%B3%A0%EB%A5%B4%EA%B8%B0-JAVA

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        // 귤 크기를 key로 갖는 HashMap을 사용하여 귤 개수를 구한다.
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < tangerine.length; i++) {
			if (map.containsKey(tangerine[i]))
				map.put(tangerine[i], map.get(tangerine[i]) + 1);
			else
				map.put(tangerine[i], 1);
		}

		// 귤 개수에 대해 내림차순으로 정렬한 ArrayList를 구한다.
		ArrayList<Integer> values = new ArrayList<>(map.values());
		values.sort(Collections.reverseOrder());

		// 귤 k개 고르기
		int cnt = 0;
		for (int i = 0; i < values.size(); i++) {
			answer++;
			cnt += values.get(i);
			if (cnt >= k)
				break;
		}
        
        return answer;
    }
}