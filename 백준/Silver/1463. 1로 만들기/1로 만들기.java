
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

// 1로 만들기
public class Main {

	static class Num {
		int n, cnt;

		public Num(int n, int cnt) {
			this.n = n;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		Queue<Num> queue = new ArrayDeque<>();
		Set<Integer> visited = new HashSet<>();

		queue.add(new Num(N, 0));
		visited.add(N);

		while (!queue.isEmpty()) {
			Num num = queue.poll();

			if (num.n == 1) {
				System.out.println(num.cnt);
				break;
			}

			// 3으로 나누기
			if (num.n % 3 == 0 && !visited.contains(num.n / 3)) {
				queue.add(new Num(num.n / 3, num.cnt + 1));
				visited.add(num.n / 3);
			}
			// 2로 나누기
			if (num.n % 2 == 0 && !visited.contains(num.n / 2)) {
				queue.add(new Num(num.n / 2, num.cnt + 1));
				visited.add(num.n / 2);
			}
			// 1 빼기
			if (!visited.contains(num.n - 1)) {
				queue.add(new Num(num.n - 1, num.cnt + 1));
				visited.add(num.n - 1);
			}
		}
	}
}
