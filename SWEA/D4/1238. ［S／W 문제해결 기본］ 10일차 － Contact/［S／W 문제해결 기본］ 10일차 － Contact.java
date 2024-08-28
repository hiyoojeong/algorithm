
// 1238. Contact
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

	static class Pos {
		int pos;

		public Pos(int pos) {
			this.pos = pos;
		}
	}

	static List<Integer>[] list;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuffer answer = new StringBuffer();

		for (int test_case = 1; test_case <= 10; test_case++) {
			// 입력
			int N = sc.nextInt(); // 데이터의 길이
			int start = sc.nextInt(); // 데이터의 시작점

			// 간선 초기화 및 연결
			list = new ArrayList[101];
			for (int i = 1; i <= 100; i++) {
				list[i] = new ArrayList<>();
			}

			for (int i = 0; i < N / 2; i++) {
				int from = sc.nextInt();
				int to = sc.nextInt();
				list[from].add(to);
			}

			// 탐색
			int last = bfs(start);

			answer.append("#" + test_case + " " + last + "\n");
		}

		System.out.println(answer);
		sc.close();
	}

	public static int bfs(int start) {
		// 마지막에 동시에 연락받은 사람
		int last = 0;

		// BFS
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(start);

		boolean[] visited = new boolean[101];
		visited[start] = true;

		while (!queue.isEmpty()) {

			int size = queue.size();
			int temp = 0;

			for (int i = 0; i < size; i++) {
				int now = queue.poll();

				for (int next : list[now]) {
					if (visited[next]) {
						continue;
					}
					queue.add(next);
					visited[next] = true;

					temp = Math.max(next, temp);
				}
			}

			last = temp == 0 ? last : temp;
		}

		return last;
	}

}
