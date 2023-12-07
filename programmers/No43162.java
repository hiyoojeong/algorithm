import java.util.ArrayList;

// 네트워크
public class No43162 {

	public static void main(String[] args) {
		System.out.println(solution(3, new int[][] { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } }));
		System.out.println(solution(3, new int[][] { { 1, 1, 0 }, { 1, 1, 1 }, { 0, 1, 1 } }));
	}

	public static int solution(int n, int[][] computers) {
		int answer = 0;

		boolean[] visited = new boolean[n];

		// 네트워크의 개수를 찾는다.
		for (int i = 0; i < n; i++) {
			if (visited[i])
				continue;

			answer++;

			ArrayList<Integer> queue = new ArrayList<>();
			queue.add(i);
			visited[i] = true;

			// 하나의 네트워크 안에서 연결되어 있는 컴퓨터를 찾는다.
			while (queue.size() > 0) {
				int s = queue.get(0);
				queue.remove(0);

				// s에서 j가 연결되어 있으면 해당 컴퓨터를 queue에 넣는다.
				for (int j = 0; j < n; j++) {
					if (visited[j])
						continue;
					if (computers[s][j] == 1) {
						queue.add(j);
						visited[j] = true;
					}
				}
			}
		}

		return answer;
	}

}
