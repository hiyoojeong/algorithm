import java.util.Scanner;
import java.util.Stack;

// 괄호 짝짓기 - stack
public class Solution {

	static final int TRUE = 1;
	static final int FALSE = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuffer answer = new StringBuffer();

		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			String str = sc.next();

			int res = isPair(N, str);
			answer.append(String.format("#%d %d\n", test_case, res));
		}
		System.out.println(answer);

		sc.close();

	}

	public static int isPair(int N, String str) {
		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < N; i++) {
			char ch = str.charAt(i);

			// 열린 괄호는 stack에 저장한다.
			if (ch == '(' || ch == '[' || ch == '<' || ch == '{') {
				stack.push(ch);
			}
			// 닫힌 괄호는 stack에서 가져온 괄호롸 짝이 맞아야 한다.
			else {
				// stack에서 가져온 괄호가 없는 경우 -> 유효하지 않다고 판단
				if(stack.isEmpty()) {
					return FALSE;
				}
				
				// stack에서 가져온 괄호
				char pair_ch = stack.pop();

				// 짝이 맞는 경우
				if ((pair_ch == '(' && ch == ')') || (pair_ch == '[' && ch == ']') || (pair_ch == '<' && ch == '>')
						|| (pair_ch == '{' && ch == '}')) {
					continue;
				}

				// 짝이 안 맞는 경우 -> 유효하지 않다고 판단
				return FALSE;
			}
		}

		return TRUE;
	}

}
