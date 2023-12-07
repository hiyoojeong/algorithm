import java.util.HashMap;
import java.util.HashSet;

// 롤케이크 자르기
public class No132265 {

	public static void main(String[] args) {
		System.out.println(solution(new int[] { 1, 2, 1, 3, 1, 4, 1, 2 }));
		System.out.println(solution(new int[] { 1, 2, 3, 1, 4 }));
	}

	static public int solution(int[] topping) {
		int answer = 0;

		int N = topping.length; // 배열 topping의 길이
		HashSet<Integer> part1 = new HashSet<>();
		HashMap<Integer, Integer> part2 = new HashMap<>();

		// 일단 롤케이크를 "철수=1, 동생=N-1" 로 나누어준다.
		part1.add(topping[0]);
		for (int i = 1; i < N; i++)
			part2.put(topping[i], part2.getOrDefault(topping[i], 0) + 1);

		// 동생이 철수에게 토핑을 하나씩 주면서 토핑 개수가 같아지는 지점을 찾는다.
		// 철수=1, 동생=N-1
		// 철수=2, 동생=N-2
		// 철수=3, 동생=N-3
		for (int i = 1; i < N; i++) {
			part1.add(topping[i]); // 찔끔 얻기
			part2.put(topping[i], part2.get(topping[i]) - 1); // 찔끔 떼주기
			if (part2.get(topping[i]) == 0)
				part2.remove(topping[i]);

			if (part1.size() == part2.size()) // 토핑 개수가 같아지는지 확인
				answer++;
		}

		return answer;
	}

	static public int solution_fail(int[] topping) {
		int answer = 0;

		int N = topping.length;
		HashSet<Integer> set1;
		HashSet<Integer> set2;
		for (int i = 0; i < N - 1; i++) {
			set1 = new HashSet<>();
			set2 = new HashSet<>();
			for (int j = 0; j <= i; j++) {
				if (!set1.contains(topping[j]))
					set1.add(topping[j]);
			}
			for (int j = i + 1; j < N; j++) {
				if (!set2.contains(topping[j]))
					set2.add(topping[j]);
			}

			if (set1.size() == set2.size()) {
				answer++;
			}
		}

		return answer;
	}

}
