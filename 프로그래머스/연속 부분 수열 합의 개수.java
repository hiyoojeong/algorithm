// https://school.programmers.co.kr/learn/courses/30/lessons/131701
// https://velog.io/@hiyoojeong/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%97%B0%EC%86%8D-%EB%B6%80%EB%B6%84-%EC%88%98%EC%97%B4-%ED%95%A9%EC%9D%98-%EA%B0%9C%EC%88%98-JAVA

import java.util.HashSet;

class Solution {
	public int solution(int[] elements) {
		int answer = 0;

		HashSet<Integer> set = new HashSet<>();
		int N = elements.length;

		for (int pos = 0; pos < N; pos++) {
			for (int n = 1; n <= N; n++) {
				int sum = 0;
				for (int i = pos; i != (pos + n) % N; i = (i + 1) % N) {
					sum += elements[i];
				}
				set.add(sum);
			}
		}

		answer = set.size();
		return answer;
	}
}