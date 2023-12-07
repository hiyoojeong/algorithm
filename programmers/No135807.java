// 숫자 카드 나누기
public class No135807 {

	public static void main(String[] args) {
		System.out.println(solution(new int[] { 10, 17 }, new int[] { 5, 20 }));
		System.out.println(solution(new int[] { 10, 20 }, new int[] { 5, 17 }));
		System.out.println(solution(new int[] { 14, 35, 119 }, new int[] { 18, 30, 102 }));
	}

	static public int solution(int[] arrayA, int[] arrayB) {
		// 배열 A를 나눌 수 있고, 배열 B를 나눌 수 없는 최대 양의 정수
		int answerA = getMaxNum(arrayA, arrayB);
		// 배열 B를 나눌 수 있고, 배열 A를 나눌 수 없는 최대 양의 정수
		int answerB = getMaxNum(arrayB, arrayA);

		// 그 중 최대값을 return
		return answerA > answerB ? answerA : answerB;
	}

	static public int getMaxNum(int[] division, int[] non_division) {
		// 배열 division에서 가장 작은 수를 num으로 지정
		int num = division[0];
		for (int i = 1; i < division.length; i++)
			if (num > division[i])
				num = division[i];

		// num을 1씩 감소시켜가면서
		// 배열 division을 나눌 수 있고, 배열 non_division을 나눌 수 없는 지 check
		while (num > 1) {
			boolean check = true;

			for (int i = 0; i < division.length; i++) {
				if (division[i] % num != 0) { // 나눌 수 없다 -> 조건 충족 X
					check = false;
					break;
				}
			}

			if (check) {
				for (int i = 0; i < non_division.length; i++) {
					if (non_division[i] % num == 0) { // 나눌 수 있다 -> 조건 충족 X
						check = false;
						break;
					}
				}
			}

			if (check) // -> 조건 충족 O
				return num;

			num--;
		}

		return 0;
	}

}
