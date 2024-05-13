import java.util.HashSet;

// 숫자 변환하기
public class No154538 {

	public static void main(String[] args) {
		System.out.println(solution(10, 40, 5));
		System.out.println(solution(10, 40, 30));
		System.out.println(solution(2, 5, 4));
	}

	public static int solution(int x, int y, int n) {
		int cnt = 0;
		HashSet<Integer> set = new HashSet<>();
		HashSet<Integer> next = null;

		set.add(x);
		while (!set.isEmpty()) {
			if (set.contains(y))
				return cnt;

			next = new HashSet<>();
			for (int val : set) {
				if (val + n <= y)
					next.add(val + n);
				if (val * 2 <= y)
					next.add(val * 2);
				if (val * 3 <= y)
					next.add(val * 3);
			}
			
			set = next;
			cnt++;
		}

		return -1;
	}

}
