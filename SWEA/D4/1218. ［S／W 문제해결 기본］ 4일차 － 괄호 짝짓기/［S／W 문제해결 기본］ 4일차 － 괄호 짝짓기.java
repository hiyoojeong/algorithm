import java.util.Scanner;

// 괄호 짝짓기
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
		int cnt1 = 0; // ()
		int cnt2 = 0; // <>
		int cnt3 = 0; // {}
		int cnt4 = 0; // []

		for (int i = 0; i < N; i++) {
			char ch = str.charAt(i);

			// ()
			if (ch == '(') {
				cnt1++;
			} else if (ch == ')') {
				cnt1--;
				// 열린 괄호가 없는데 닫힌 괄호를 사용함
				if (cnt1 < 0) {
					return FALSE;
				}
			}

			// <>
			if (ch == '<') {
				cnt2++;
			} else if (ch == '>') {
				cnt2--;
				// 열린 괄호가 없는데 닫힌 괄호를 사용함
				if (cnt2 < 0) {
					return FALSE;
				}
			}

			// {}
			if (ch == '{') {
				cnt3++;
			} else if (ch == '}') {
				cnt3--;
				// 열린 괄호가 없는데 닫힌 괄호를 사용함
				if (cnt3 < 0) {
					return FALSE;
				}
			}

			// []
			if (ch == '[') {
				cnt4++;
			} else if (ch == ']') {
				cnt4--;
				// 열린 괄호가 없는데 닫힌 괄호를 사용함
				if (cnt4 < 0) {
					return FALSE;
				}
			}
		}

		// 열린 괄호가 있는데 닫힌 괄호를 사용하지 않음
		if (cnt1 > 0 || cnt2 > 0 || cnt3 > 0 || cnt4 > 0) {
			return FALSE;
		}

		return TRUE;
	}

}
