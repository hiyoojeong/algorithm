import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 에너지 드링크
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		Queue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			queue.add(Integer.parseInt(st.nextToken()));
		}

		double answer = queue.poll();

		while (!queue.isEmpty()) {
			int num = queue.poll();
			answer += (double) num / 2;
		}

		System.out.println(answer);
	}

}
