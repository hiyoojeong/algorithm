import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

// 탈출
public class Main {

	static class Number {
		int number, cnt;

		public Number(int number, int cnt) {
			this.number = number;
			this.cnt = cnt;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int G = Integer.parseInt(st.nextToken());

		Queue<Number> queue = new LinkedList<>();
		queue.add(new Number(N, 0));

		Set<Integer> numbers = new HashSet<>();
		numbers.add(N);

		while (!queue.isEmpty()) {
			Number now = queue.poll();

			if (now.number == G) {
				System.out.println(now.cnt);
				return;
			}

			if (now.cnt < T) {
				int next;

				// A 버튼을 누른다.
				next = now.number + 1;
				if (next < 100000 && !numbers.contains(next)) {
					queue.add(new Number(next, now.cnt + 1));
					numbers.add(next);
				}

				// B 버튼을 누른다.
				if (now.number != 0) {
					next = now.number * 2;
					if (next < 100000) {
						String nextStr = String.valueOf(next);
						int front = nextStr.charAt(0) - '0';
						next = Integer.parseInt(String.valueOf(front - 1) + nextStr.substring(1, nextStr.length()));
						if (next < 100000 && !numbers.contains(next)) {
							queue.add(new Number(next, now.cnt + 1));
							numbers.add(next);
						}
					}
				}
			}
		}

		System.out.println("ANG");
	}

}
