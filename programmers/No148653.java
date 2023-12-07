// 마법의 엘리베이터
public class No148653 {

	public static void main(String[] args) {
		System.out.println(solution(16));
		System.out.println(solution(2554));
		System.out.println(solution(1051));
		System.out.println(solution(100500001));
	}

	static public int solution(int storey) {
		int answer = 0;

		while (storey != 0) {
			int one = storey % 10;
			int ten = (storey / 10) % 10;

			if (one > 5) {
				answer += (10 - one);
				storey += 10;
			} else if (one == 5) {
				answer += one;
				storey += (ten < 5 ? 0 : 10);
			} else {
				answer += one;
			}

			storey /= 10;
		}

		return answer;
	}

	static public int solution_false(int storey) {
		int answer = 0;

		int digit = 1;
		while (storey / digit != 0)
			digit *= 10;

		while (digit > 1) {
			if (storey > digit / 2) {
				storey = digit - storey;
				answer += 1;
			}

			digit /= 10;
			answer += (storey / digit);
			storey %= digit;
		}

		return answer;
	}
}
