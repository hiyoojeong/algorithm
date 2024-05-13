import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

// 크레인 인형뽑기 게임
public class No64061 {

	public static void main(String[] args) {
		System.out.println(solution(new int[][] { { 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 3 }, { 0, 2, 5, 0, 1 },
				{ 4, 2, 4, 4, 2 }, { 3, 5, 1, 3, 1 } }, new int[] { 1, 5, 3, 5, 1, 2, 1, 4 }));
	}

	public static int solution(int[][] board, int[] moves) {
		int answer = 0;

		int N = board.length;

		Map<Integer, Stack<Integer>> map = new HashMap<>();
		for (int j = 0; j < N; j++) {
			Stack<Integer> stack = new Stack<>();
			for (int i = N - 1; i >= 0; i--) {
				if (board[i][j] == 0) {
					continue;
				}
				stack.push(board[i][j]);
			}
			map.put(j + 1, stack);
		}

		Stack<Integer> items = new Stack<>();

		for (int i = 0; i < moves.length; i++) {
			Stack<Integer> stack = map.get(moves[i]);

			if (stack.size() > 0) {
				int move = stack.pop();
				items.add(move);
			}

			if (items.size() >= 2) {
				int item1 = items.pop();
				int item2 = items.pop();
				if (item1 == item2) {
					answer += 2;
				} else {
					items.push(item2);
					items.push(item1);
				}
			}
		}

		return answer;
	}

}
