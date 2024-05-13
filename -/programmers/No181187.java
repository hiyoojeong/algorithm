// 두 원 사이의 정수쌍
public class No181187 {

	public static void main(String[] args) {
		System.out.println(solution(2, 3));
	}

	public static long solution(int r1, int r2) {
		long answer = 0;

		for (int x = 1; x <= r2; x++) {
			// y^2 = r^2 - x^2
			long y1_ceil = (long) Math.ceil(Math.sqrt(1.0 * r1 * r1 - 1.0 * x * x)); // 올림
			long y2_floor = (long) Math.floor(Math.sqrt(1.0 * r2 * r2 - 1.0 * x * x)); // 내림
			answer += y2_floor - y1_ceil + 1;
		}

		return answer * 4;
	}

}
