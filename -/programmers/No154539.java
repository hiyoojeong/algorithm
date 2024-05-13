import java.util.*;

// 뒤에 있는 큰 수 찾기
public class No154539 {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new int[] { 2, 3, 3, 5 })));
		System.out.println(Arrays.toString(solution(new int[] { 9, 1, 5, 3, 6, 2 })));
	}

	public static int[] solution(int[] numbers) {
		int[] answer = new int[numbers.length];
		Arrays.fill(answer, -1);
		
		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < numbers.length; i++) {
			while (!stack.isEmpty() && numbers[i] > numbers[stack.peek()]) {
				answer[stack.pop()] = numbers[i];
			}
			stack.push(i);
		}

		return answer;
	}

}
