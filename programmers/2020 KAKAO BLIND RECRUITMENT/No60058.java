// 괄호 변환
public class No60058 {

	public static void main(String[] args) {
		System.out.println(solution("(()())()"));
		System.out.println(solution(")("));
		System.out.println(solution("()))((()"));
	}

	public static String solution(String p) {
		// 빈 문자열인 경우, 빈 문자열을 반환한다.
		if (p == "")
			return "";

		boolean balance = true;
		int left = 0, right = 0;
		for (int i = 0; i < p.length(); i++) {
			if (p.charAt(i) == '(')
				left++;
			else
				right++;

			if (right > left)
				balance = false;

			if (left == right) {
				// 문자열 w를 두 "균형잡힌 괄호 문자열" u,v로 분리한다.
				String u = p.substring(0, i + 1);
				String v = p.substring(i + 1);

				if (balance) {
					// 문자열 u가 "올바른 괄호 문자열"이라면
					p = u + solution(v);
				} else {
					// 문자열 u가 "올바른 괄호 문자열"이 아니라면
					StringBuffer sb = new StringBuffer();
					sb.append("(");
					sb.append(solution(v));
					sb.append(")");
					if (u.length() > 2)
						for (int j = 1; j < u.length() - 1; j++) {
							if(u.charAt(j) == ')')
								sb.append("(");
							else
								sb.append(")");
						}
					p = sb.toString();
				}

				break;
			}
		}
		return p;
	}

}
