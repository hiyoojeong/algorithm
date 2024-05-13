import java.util.HashSet;

// 연속 부분 수열 합의 개수
public class No131701 {

	public static void main(String[] args) {
		System.out.println(solution(new int[] { 7, 9, 1, 1, 4 }));
	}

	public static int solution(int[] elements) {
		int answer = 0;

		HashSet<Integer> set = new HashSet<>();
		int N = elements.length;

		for (int pos = 0; pos < N; pos++) {
			for (int n = 1; n <= N; n++) {
				int sum = 0;
				for (int i = pos; i != (pos + n) % N; i = (i + 1) % N) {
					sum += elements[i];
				}
				set.add(sum);
			}
		}

		answer = set.size();
		return answer;
	}

}
