import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

// DSLR
public class Main {

	static class Number {
		int number;
		String route;

		Number(int number, String route) {
			this.number = number;
			this.route = route;
		}
	}

	static Set<Integer> numbers;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer answer = new StringBuffer();

		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			Number start = new Number(Integer.parseInt(st.nextToken()), "");
			Number end = new Number(Integer.parseInt(st.nextToken()), "");

			// 이미 만들어졌던 숫자인 경우, 생략해야 한다.
			numbers = new HashSet<>();

			answer.append(getRoute(start, end)).append("\n");
		}

		System.out.println(answer);
	}

	public static String getRoute(Number start, Number end) {
		Queue<Number> queue = new LinkedList<>();
		queue.add(start);

		String answer = "";
		while (!queue.isEmpty()) {
			Number now = queue.poll();

			// 타켓 숫자가 완성되어 반복을 종료한다.
			if (now.number == end.number) {
				answer = now.route;
				break;
			}

			// DSLR 숫자를 만든다.
			int D = (now.number * 2) % 10000;
			int S = (10000 + (now.number - 1)) % 10000;

			int front = now.number / 1000;
			int L = ((now.number % 1000) * 10) + front;

			int rear = now.number % 10;
			int R = (rear * 1000) + (now.number / 10);

			// DSLR 숫자를 queue에 저장한다.
			if (!numbers.contains(D)) {
				queue.add(new Number(D, now.route + "D"));
				numbers.add(D);
			}
			if (!numbers.contains(S)) {
				queue.add(new Number(S, now.route + "S"));
				numbers.add(S);
			}
			if (!numbers.contains(L)) {
				queue.add(new Number(L, now.route + "L"));
				numbers.add(L);
			}
			if (!numbers.contains(R)) {
				queue.add(new Number(R, now.route + "R"));
				numbers.add(R);
			}

		}

		return answer;
	}

}
