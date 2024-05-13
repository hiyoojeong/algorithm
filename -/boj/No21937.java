

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 작업
public class No21937 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 민상이가 작업할 개수
		int M = Integer.parseInt(st.nextToken()); // 작업 순서 정보의 개수

		// 작업 순서 정보 저장
		List<Integer>[] list = new LinkedList[N];
		for (int i = 0; i < N; i++) {
			list[i] = new LinkedList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			list[end].add(start);
		}

		int X = Integer.parseInt(br.readLine()) - 1; // 민상이가 오늘 반드시 끝내야하는 작업

		Queue<Integer> queue = new LinkedList<>(); // 민상이가 끝내야하는 작업 리스트
		queue.add(X);

		boolean[] visited = new boolean[N]; // 민상이가 끝낸 작업 정보 (중복 제거)
		visited[X] = true;

		int cnt = 0; // 민상이가 끝내야하는 작업의 개수

		while (queue.size() > 0) {
			int task = queue.poll();

			for (int preTask : list[task]) {
				if (!visited[preTask]) {
					cnt++;
					visited[preTask] = true;
					queue.add(preTask);
				}
			}
		}

		System.out.println(cnt);

	}

}
