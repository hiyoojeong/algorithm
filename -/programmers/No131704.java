import java.util.Stack;

// 택배상자
public class No131704 {
	public static void main(String[] args) {
		System.out.println(solution(new int[]{4,3,1,2,5}));
		System.out.println(solution(new int[]{5,4,3,2,1}));
	}
	
	public static int solution(int[] order) {
        int answer = 0;
		int N = order.length;

		Stack<Integer> stack = new Stack<>();

		int orderidx = 0;
		for (int i = 1; i <= N; i++) {
			if (order[orderidx] == i) {
				orderidx++;
				answer++;

				while (stack.size() != 0) {
					int s = stack.peek();
					if (s == order[orderidx]) {
						stack.pop();
						orderidx++;
						answer++;
					} else {
						break;
					}
				}
			} else {
				stack.push(i);
			}
		}

		return answer;

    }
}
