// https://school.programmers.co.kr/learn/courses/30/lessons/142085
// https://velog.io/@hiyoojeong/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EB%94%94%ED%8E%9C%EC%8A%A4-%EA%B2%8C%EC%9E%84-%EC%9E%90%EB%B0%94

import java.util.PriorityQueue;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int result = 0;

		PriorityQueue<Integer> queue = new PriorityQueue<>();
		queue.add(enemy[0]);
		k--;
		result++;
		for (int i = 1; i < enemy.length; i++) {
			if (k > 0) {
				queue.add(enemy[i]);
				k--;
			} else {
				int tmp = queue.poll();
				if(enemy[i]>tmp) {
					queue.add(enemy[i]);
					n-=tmp;
				} else {
					queue.add(tmp);
					n-=enemy[i];
				}
			}

			if (n < 0)
				return result;
			result++;
		}

		return result;  	
    }
}