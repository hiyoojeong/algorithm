import java.util.Comparator;
import java.util.PriorityQueue;

// 디스크 컨트롤러
public class No42627 {

	public static void main(String[] args) {
		System.out.println(solution(new int[][] { { 0, 3 }, { 1, 9 }, { 2, 6 } }));
	}

	public static int solution(int[][] jobs) {
		int answer = 0;

		// 작업
		PriorityQueue<int[]> all = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] < o2[0])
					return -1;
				else if (o1[0] > o2[0])
					return 1;
				else {
					return Integer.compare(o1[1], o2[1]);
				}
			}
		});

		for (int i = 0; i < jobs.length; i++) {
			all.add(jobs[i]);
		}

		// 작업의 대기 큐
		PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> (o1[1] - o2[1]));

		int time = 0; // 현재시간
		int curJob = 0; // 현재 실행중인 작업의 남은 시간

		while (!all.isEmpty() || queue.size() > 0) {
			// 요청된 작업은 대기 큐에 들어간다.
			while (!all.isEmpty() && time >= all.peek()[0]) {
				queue.add(all.poll());
			}

			// 현재 실행중인 작업이 없고, 대기중인 작업이 있다면 실행한다.
			if (curJob <= 0 && queue.size() > 0) {
				if (time >= queue.peek()[0]) {
					int[] job = queue.poll();
					curJob = job[1];
					answer += (time - job[0]) + job[1];
				}
			}

			curJob--;
			time++;

		}

		return answer / jobs.length;
	}
}
