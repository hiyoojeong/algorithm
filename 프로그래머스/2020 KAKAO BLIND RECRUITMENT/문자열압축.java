// https://school.programmers.co.kr/learn/courses/30/lessons/60057
// https://velog.io/@hiyoojeong/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EB%AC%B8%EC%9E%90%EC%97%B4-%EC%95%95%EC%B6%95-JAVA

class Solution {
    public int solution(String s) {
        int answer = s.length();

		for (int i = 1; i <= s.length() / 2; i++) {
			// 압축된 문자열
			StringBuilder result = new StringBuilder();

			// i개 단위로 잘라온 문자열
			String preStr = s.substring(0, i);
			// i개 단위로 잘라온 문자열 - preStr과 비교하여 반복되는 문자열인지 판단한다.
			String str = "";

			// 문자열 반복 횟수
			int loop = 1;

			for (int start = i; start < s.length(); start += i) {
				// 남은 문자열 길이가 i보다 작은 경우를 구분한다.
				str = start + i > s.length() ? s.substring(start, s.length()) : s.substring(start, start + i);

				// str과 preStr을 비교하여 반복되는 문자열인지 판단한다.
				if (str.equals(preStr)) {
					loop++;
				} else {
					if (loop != 1) {
						result.append(loop);
					}
					result.append(preStr);
					loop = 1;
				}

				// preStr 업데이트
				preStr = str;
			}

			if (loop != 1) {
				result.append(loop);
			}
			result.append(preStr);
			
			answer = Math.min(answer, result.length());
		}

		return answer;
    }
}