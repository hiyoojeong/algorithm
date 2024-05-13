

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 1차원 2048
public class No27514 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		Queue<Long> queue = new PriorityQueue<>();

		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			queue.add(Long.parseLong(st.nextToken()));
		}

		while (queue.size() >= 2) {
			long a1 = queue.poll();
			long a2 = queue.poll();

			if (a1 == a2) {
				queue.add(a1 + a2);
			} else {
				queue.add(Math.max(a1, a2));
			}
		}
		
		System.out.println(queue.poll());
	}

}
