// 올바른 괄호
public class No12909 {

	public static void main(String[] args) {
		String s = "()()";
//		String s = "(())()";
//		String s = ")()(";
//		String s = "(()(";

		System.out.println(solution(s));
	}

	public static boolean solution(String s) {
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
