import java.util.Arrays;

// 입국심사
public class No43238 {
	
	public static void main(String[] args) {
		System.out.println(solution(6, new int[] {7, 10}));
	}
	
	public static long solution(int n, int[] times) {
		long answer = 0;

		Arrays.sort(times);

		long left = 0; // 모든 사람을 심사하는데 걸리는 최소 시간
		long right = (long) n * times[times.length - 1]; // 모든 사람을 심사하는데 걸리는 최대 시간
		long middle;

		// 이분탐색
		while (left <= right) {
			middle = (left + right) / 2;

			long cnt = 0; // 심사한 사람의 수
			for (int i = 0; i < times.length; i++) {
				cnt += middle / times[i]; // 심사하는데 걸리는 시간이 적은 심사관부터 사람 할당
			}

			if (cnt < n) { // 심사한 사람의 수 < 심사할 사람의 수 : 시간이 더 필요하다.
				left = middle + 1;
			} else { // 심사한 사람의 수 >= 심사한 사람의 수 : 더 적은 시간으로도 모든 사람을 심사할 수 있는지 추가 확인
				answer = middle;
				right = middle - 1;
			}
		}

		return answer;
	}

}
