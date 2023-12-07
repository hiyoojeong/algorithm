import java.util.Arrays;

// 연속된 부분 수열의 합
public class No178870 {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new int[] { 1, 2, 3, 4, 5 }, 7)));
		System.out.println(Arrays.toString(solution(new int[] { 1, 1, 1, 2, 3, 4, 5 }, 5)));
		System.out.println(Arrays.toString(solution(new int[] { 2, 2, 2, 2, 2 }, 6)));
	}

	public static int[] solution(int[] sequence, int k) {
		// answer: 부분 수열의 길이가 가장 길도록 초기화
		int[] answer = new int[2];
		answer[0] = 0;
		answer[1] = sequence.length;

		int sum = 0, start = 0;
		// 마지막 인덱스를 늘려가며 부분 수열의 합 늘리기
		for (int end = 0; end < sequence.length; end++) {
			sum += sequence[end];

			// 부분 수열의 합이 k를 넘었을 경우, 시작 인덱스를 늘려가며 부분 수열의 합 줄이기
			if (sum > k) {
				while (sum > k) {
					sum -= sequence[start++];
				}
			}

			// 부분 수열의 합이 k일 경우, 부분 수열의 길이가 짧은 것을 선택
			if (sum == k) {
				if (end - start < answer[1] - answer[0]) {
					answer[0] = start;
					answer[1] = end;
				}
			}
		}

		return answer;
	}

}
