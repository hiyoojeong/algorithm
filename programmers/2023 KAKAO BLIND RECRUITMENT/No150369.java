// 택배 배달과 수거
public class No150369 {

	public static void main(String[] args) {
		System.out.println(solution(4, 5, new int[] { 1, 0, 3, 1, 2 }, new int[] { 0, 3, 0, 4, 0 }));
		System.out.println(solution(2, 7, new int[] { 1, 0, 2, 0, 1, 0, 2 }, new int[] { 0, 2, 0, 1, 0, 2, 0 }));

	}

	public static long solution(int cap, int n, int[] deliveries, int[] pickups) {
		long answer = 0;

		int delivery = 0, pickup = 0;
		for (int i = n - 1; i >= 0; i--) {
			int move = 0;

			while (delivery < deliveries[i] || pickup < pickups[i]) {
				move++;
				delivery += cap;
				pickup += cap;
			}

			delivery -= deliveries[i];
			pickup -= pickups[i];
			answer += ((i + 1) * move) * 2;
		}

		return answer;
	}
}
