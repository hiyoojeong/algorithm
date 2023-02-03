// https://school.programmers.co.kr/learn/courses/30/lessons/42577
// https://velog.io/@hiyoojeong/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%A0%84%ED%99%94%EB%B2%88%ED%98%B8-%EB%AA%A9%EB%A1%9D-JAVA

import java.util.HashMap;

class Solution {
    public boolean solution(String[] phone_book) {
        int N = phone_book.length;

		HashMap<String, Integer> map = new HashMap<>();
		for (int i = 0; i < N; i++) {
			map.put(phone_book[i], 1);
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<phone_book[i].length(); j++) {
				if(map.containsKey(phone_book[i].substring(0, j)))
					return false;
			}
		}

		return true;
    }
}