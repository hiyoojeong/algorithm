import java.util.PriorityQueue;

// 디펜스 게임
public class No142085 {

	public static void main(String[] args) {
		int n = 7, k = 3;
		int[] enemy = { 4, 2, 4, 5, 3, 3, 1 };

//		int n = 2, k = 4;
//		int[] enemy = { 3, 3, 3, 3 };

		System.out.println(solution(n, k, enemy));
	}

	public static int solution(int n, int k, int[] enemy) {
		int result = 0;

		// 무적권을 소모한 라운드 정보
		PriorityQueue<Integer> queue = new PriorityQueue<>();

		// 무적권은 1번 이상이므로 1 라운드에 무적권을 쓴다.
		queue.add(enemy[0]);
		k--;
		result++;

		// 라운드를 진행하며
		// 무적권을 사용할 수 있다면, 무적권을 사용한다.
		// 무적권을 사용할 수 없다면, "현재 라운드의 적 수 > 무적권을 소모한 라운드의 적 수"인 경우 swap 한다.
		for (int i = 1; i < enemy.length; i++) {
			if (k > 0) {
				queue.add(enemy[i]);
				k--;
			} else {
				int tmp = queue.poll();
				if (enemy[i] > tmp) {
					queue.add(enemy[i]);
					n -= tmp;
				} else {
					queue.add(tmp);
					n -= enemy[i];
				}
			}

			if (n < 0) //  현재 라운드를 버티지 못한 경우
				return result;
			result++; // 현재 라운드를 버틴 경우
		}

		return result;
	}

}
