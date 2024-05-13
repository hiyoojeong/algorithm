import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

// 귤 고르기
public class No138476 {

	public static void main(String[] args) {
		System.out.println(solution(6, new int[] { 1, 3, 2, 5, 4, 5, 2, 3 }));
		System.out.println(solution(4, new int[] { 1, 3, 2, 5, 4, 5, 2, 3 }));
		System.out.println(solution(3, new int[] { 1, 1, 1, 1, 2, 2, 2, 3 }));
	}

	public static int solution(int k, int[] tangerine) {
		int answer = 0;

		// 귤 크기를 key로 갖는 HashMap을 사용하여 귤 개수를 구한다.
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < tangerine.length; i++) {
			if (map.containsKey(tangerine[i]))
				map.put(tangerine[i], map.get(tangerine[i]) + 1);
			else
				map.put(tangerine[i], 1);
		}

		// 귤 개수에 대해 내림차순으로 정렬한 ArrayList를 구한다.
		ArrayList<Integer> values = new ArrayList<>(map.values());
		values.sort(Collections.reverseOrder());

		// 귤 k개 고르기
		int cnt = 0;
		for (int i = 0; i < values.size(); i++) {
			answer++;
			cnt += values.get(i);
			if (cnt >= k)
				break;
		}

		return answer;

	}

}
