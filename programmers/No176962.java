import java.util.Arrays;
import java.util.Stack;

class Assignment implements Comparable<Assignment> {
	String name;
	int start, playtime;

	Assignment(String name, int start, int playtime) {
		this.name = name;
		this.start = start;
		this.playtime = playtime;
	}

	@Override
	public int compareTo(Assignment o) {
		return this.start - o.start;
	}

}

// 과제 진행하기
public class No176962 {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new String[][] { { "korean", "11:40", "30" },
				{ "english", "12:10", "20" }, { "math", "12:30", "40" } })));
		System.out.println(Arrays.toString(solution(new String[][] { { "science", "12:40", "50" },
				{ "music", "12:20", "40" }, { "history", "14:00", "30" }, { "computer", "12:30", "100" } })));
		System.out.println(Arrays.toString(solution(
				new String[][] { { "aaa", "12:00", "20" }, { "bbb", "12:10", "30" }, { "ccc", "12:40", "10" } })));
	}

	public static String[] solution(String[][] plans) {
		String[] answer = new String[plans.length];
		int answer_idx = 0;

		Assignment[] ready = new Assignment[plans.length];
		for (int i = 0; i < plans.length; i++) {
			String name = plans[i][0];

			String[] hhmm = plans[i][1].split(":");
			int hour = Integer.parseInt(hhmm[0]);
			int minute = Integer.parseInt(hhmm[1]);
			int start = hour * 60 + minute;

			int playtime = Integer.parseInt(plans[i][2]);

			ready[i] = new Assignment(name, start, playtime);
		}

		Arrays.sort(ready);

		Stack<Assignment> stop = new Stack<>();
		for (int i = 0; i < ready.length; i++) {
			Assignment current = ready[i];

			if (i == ready.length - 1) {
				// 마지막 과제를 완료된 과제에 저장한다.
				answer[answer_idx++] = current.name;

				while (stop.size() > 0) {
					// stop된 과제들을 모두 완료된 과제에 저장한다.
					Assignment restart = stop.pop();

					answer[answer_idx++] = restart.name;
				}
				break;
			}

			Assignment next = ready[i + 1];
			int term = next.start - current.start;
			int extra = term - current.playtime;

			// 과제를 끝내고 남는 시간이 생긴다.
			if (extra >= 0) {
				// 완료된 과제를 저장한다.
				answer[answer_idx++] = current.name;

				// 남는 시간동안 stop 과제들을 수행한다.
				while (stop.size() > 0 && extra > 0) {
					Assignment restart = stop.pop();
					
					extra -= restart.playtime;

					// 과제를 끝내고 남는 시간이 생긴다.
					if (extra >= 0) {
						// 완료된 과제를 저장한다.
						answer[answer_idx++] = restart.name;
					}
					// 과제를 중단한다.
					else {
						// 남은 시간을 저장한다.
						int playtime = Math.abs(extra);
						restart.playtime = playtime;

						// stop 에 넣는다.
						stop.add(restart);
					}
				}
			}
			// 과제를 중단한다.
			else {
				// 남은 시간을 저장한다.
				int playtime = Math.abs(extra);
				current.playtime = playtime;

				// stop 에 넣는다.
				stop.add(current);
			}

		}

		return answer;
	}

}
