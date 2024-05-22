import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 빈도 정렬
public class Main {

	static class Number {
		int number, cnt, order;

		public Number(int number, int cnt, int order) {
			this.number = number;
			this.cnt = cnt;
			this.order = order;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 숫자와 숫자의 빈도를 저장한다.
		Map<Integer, Number> map = new HashMap<>();

		// 숫자의 빈도가 많은 순, 먼저 나온 순으로 정렬한다.
		Queue<Number> pq = new PriorityQueue<>(new Comparator<Number>() {
			public int compare(Number n1, Number n2) {
				return n2.cnt != n1.cnt ? n2.cnt - n1.cnt : n1.order - n2.order;
			}
		});

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		// 숫자와 숫자의 빈도를 저장한다.
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int number = Integer.parseInt(st.nextToken());
			if (map.containsKey(number)) {
				map.get(number).cnt++;

			} else {
				Number newNumber = new Number(number, 1, i);
				map.put(number, newNumber);
			}
		}

		// 숫자의 빈도가 많은 순, 먼저 나온 순으로 정렬한다.
		for (int number : map.keySet()) {
			pq.add(new Number(number, map.get(number).cnt, map.get(number).order));
		}

		// 정렬된 정보대로 수를 저장한다.
		StringBuffer answer = new StringBuffer();
		while (!pq.isEmpty()) {
			Number number = pq.poll();
			for (int i = 0; i < number.cnt; i++) {
				answer.append(number.number).append(" ");
			}
		}

		System.out.println(answer);

	}

}
