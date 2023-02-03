// https://school.programmers.co.kr/learn/courses/30/lessons/147354
// https://velog.io/@hiyoojeong/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%ED%85%8C%EC%9D%B4%EB%B8%94-%ED%95%B4%EC%8B%9C-%ED%95%A8%EC%88%98-JAVA

import java.util.ArrayList;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;

		// col 번째 컬럼을 기준으로 오름차순 정렬, 동일한 경우 첫 번째 컬럼을 기준으로 내림차순 정렬
		ArrayList<Integer> order = new ArrayList<Integer>();
		order.add(0);
		for (int i = 1; i < data.length; i++) {
			boolean isAdd = false;
			for (int j = 0; j < order.size(); j++) {
				if (data[i][col - 1] < data[order.get(j)][col - 1]) {
					order.add(j, i);
					isAdd = true;
					break;
				} else if (data[i][col - 1] == data[order.get(j)][col - 1]) {
					if (data[i][0] > data[order.get(j)][0]) {
						order.add(j, i);
						isAdd = true;
						break;
					}
				}
			}
			if (!isAdd) {
				order.add(i);
			}
		}

		// S_i를 누적하여 bitwise XOR 한 값
		for (int i = row_begin; i <= row_end; i++) {
			int sum = 0;
			for (int j = 0; j < data[order.get(i - 1)].length; j++) {
				sum += (data[order.get(i - 1)][j] % i);
			}
			answer ^= sum;
		}
        
        return answer;
    }
}