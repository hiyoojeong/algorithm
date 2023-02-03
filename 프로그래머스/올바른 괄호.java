// https://school.programmers.co.kr/learn/courses/30/lessons/12909
// https://velog.io/@hiyoojeong/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%98%AC%EB%B0%94%EB%A5%B8-%EA%B4%84%ED%98%B8-JAVA

class Solution {
    boolean solution(String s) {
        int l = 0, r = 0;

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(')
				l++;
			else
				r++;

			if (r > l)
				return false;
		}

		if (l != r)
			return false;

		return true;
    }
}