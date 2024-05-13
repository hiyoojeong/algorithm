import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {

	static boolean isContain, isThree, isSame;
	static int vowelCnt, consonantCnt;
	static char pre;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer answer = new StringBuffer();

		Set<Character> vowels = new HashSet<>();
		vowels.add('a');
		vowels.add('e');
		vowels.add('i');
		vowels.add('o');
		vowels.add('u');

		while (true) {

			String input = br.readLine();
			if (input.equals("end")) {
				break;
			}

			init();

			for (int i = 0; i < input.length(); i++) {
				char ch = input.charAt(i);

				// 조건1: a, e, i, o, u 1개 이상
				if (vowels.contains(ch)) {
					isContain = true;
				}

				// 조건2: 모음, 자음 3연속 X
				if (vowels.contains(ch)) {
					vowelCnt++;
					consonantCnt = 0;
				} else {
					consonantCnt++;
					vowelCnt = 0;
				}

				if (vowelCnt >= 3 || consonantCnt >= 3) {
					isThree = true;
				}

				// 조건3: ee, oo 빼고 연속 X
				if (ch != 'e' && ch != 'o' && pre == ch) {
					isSame = true;
				}
				pre = ch;
			}

			answer.append("<").append(input).append(">");
			if (isContain && !isThree & !isSame) {
				answer.append(" is acceptable.\n");
			} else {
				answer.append(" is not acceptable.\n");
			}
		}

		System.out.println(answer);

	}

	public static void init() {
		isContain = false;
		isThree = false;
		isSame = false;
		vowelCnt = 0;
		consonantCnt = 0;
		pre = '-';
	}

}
