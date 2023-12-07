// 문자열 압축
public class No60057 {

	public static void main(String[] args) {
		String s = "aabbaccc";
//		String s = "ababcdcdababcdcd";
//		String s = "abcabcdede";
//		String s = "abcabcabcabcdededededede";
//		String s = "xababcdcdababcdcd";

//		System.out.println(solution(s));
		System.out.println(solution_second(s));
	}

	public static int solution(String s) {
		int answer = Integer.MAX_VALUE, tmp = 0;

		int N = s.length(), loop = 1;
		String str = "", preStr = "";

		// n개 단위로 잘라 압축
		for (int n = 1; n < N / 2; n++) {
			// 가장 처음에 오는 단어는 preStr으로 저장한다.
			preStr = s.substring(0, n);

			// n개의 단위로 잘라 preStr과 비교한다.
			// => 같으면 loop를 증가시켜준다.
			// => 다르면 tmp에 압축된 길이를 업데이트해준다. (loop가 1이면 숫자 생략)
			for (int start = n; start < (N / n) * n; start += n) {
				str = s.substring(start, start + n);
				if (str.equals(preStr)) {
					loop++;
				} else {
					if (loop == 1) {
						tmp += n;
					} else {
						tmp += (n + Integer.toString(loop).length());
						loop = 1;
					}
				}
				preStr = str;
			}
			// 가장 마지막에 오는 단어에 대해서 tmp 업데이트
			if (loop == 1) {
				tmp += n;
			} else {
				tmp += (n + Integer.toString(loop).length());
			}

			// n개의 단위로 잘랐을 때, 나머지에 해당하는 부분
			tmp += (N % n);

			// answer 업데이트
			if (tmp < answer)
				answer = tmp;
			tmp = 0;
		}

		return answer;
	}

	public static int solution_second(String s) {
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
