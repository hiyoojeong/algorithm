// 옹알이(1)
public class No120956 {

	public static void main(String[] args) {
		String[] babbling = { "aya", "yee", "u", "maa", "wyeoo" };
//		String[] babbling = { "ayaye", "uuuma", "ye", "yemawoo", "ayaa" };
		System.out.println(solution(babbling));
	}

	public static int solution(String[] babbling) {
		int answer = 0;

		for (String bab : babbling) {
			bab = bab.replaceAll("aya|ye|woo|ma", "");

			if (bab.equals(""))
				answer++;
		}

		return answer;
	}

}
