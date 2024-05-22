import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 톱니바퀴 (2)
public class Main {

	static class Task {
		int idx, direction;

		public Task(int idx, int direction) {
			this.idx = idx;
			this.direction = direction;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		String[] gears = new String[N + 1];
		for (int i = 1; i <= N; i++) {
			gears[i] = br.readLine();
		}

		int K = Integer.parseInt(br.readLine());
		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			int pos = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken());

			// 회전 작업을 저장한다.
			Queue<Task> queue = new LinkedList<>();
			queue.add(new Task(pos, direction));

			int nextdirection, left, right;

			// 왼쪽을 확인하며, 회전 작업을 저장한다.
			nextdirection = (-1) * direction;
			right = pos;
			for (left = pos - 1; left >= 1; left--) {
				if (gears[left].charAt(2) != gears[right].charAt(6)) {
					queue.add(new Task(left, nextdirection));
					nextdirection *= -1;
					right = left;
				} else {
					break;
				}
			}

			// 오른쪽을 확인하며, 회전 작업을 저장한다.
			nextdirection = (-1) * direction;
			left = pos;
			for (right = pos + 1; right <= N; right++) {
				if (gears[left].charAt(2) != gears[right].charAt(6)) {
					queue.add(new Task(right, nextdirection));
					nextdirection *= -1;
					left = right;
				} else {
					break;
				}
			}

			// 회전한다.
			while (!queue.isEmpty()) {
				Task task = queue.poll();

				// 시계방향 회전
				if (task.direction == 1) {
					String rear = String.valueOf(gears[task.idx].charAt(7));
					gears[task.idx] = rear + gears[task.idx].substring(0, 7);
				}
				// 반시계방향 회전
				else if (task.direction == -1) {
					String front = String.valueOf(gears[task.idx].charAt(0));
					gears[task.idx] = gears[task.idx].substring(1, 8) + front;
				}
			}
		}

		// 12시 방향이 S극인 톱니바퀴의 개수를 구한다.
		int answer = 0;
		for (int i = 1; i <= N; i++) {
			if (gears[i].charAt(0) == '1') {
				answer++;
			}
		}

		System.out.println(answer);

	}

}
