
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 1247. 최적 경로
public class Solution {

	static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static int N;
	static Node start, end;
	static List<Node> clients;

	static boolean[] isSelected;

	static int minDist;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		StringBuffer answer = new StringBuffer();
		for (int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt();
			start = new Node(sc.nextInt(), sc.nextInt());
			end = new Node(sc.nextInt(), sc.nextInt());

			clients = new ArrayList<>();
			clients.add(start); // 회사
			for (int i = 1; i <= N; i++) { // 고객
				clients.add(new Node(sc.nextInt(), sc.nextInt()));
			}
			clients.add(end); // 집

			isSelected = new boolean[N + 1];
			minDist = Integer.MAX_VALUE;
			permutation(0, 0, 0);

			answer.append(String.format("#%d %d\n", test_case, minDist));
		}

		System.out.println(answer);

		sc.close();
	}

	public static void permutation(int before, int cnt, int dist) {
		// 이동거리가 이미 최단 이동거리를 넘겼으므로, 탐색 불필요
		if (dist > minDist) {
			return;
		}

		// 이동 종료
		if (cnt == N) {
			// 모두 방문 후 집까지의 거리 추가
			int addDist = Math.abs(end.x - clients.get(before).x) + Math.abs(end.y - clients.get(before).y);
			dist += addDist;

			// 최단 경로 업데이트
			minDist = Math.min(dist, minDist);
			return;
		}

		for (int i = 1; i <= N; i++) {
			if (isSelected[i]) {
				continue;
			}

			isSelected[i] = true;
			int addDist = Math.abs(clients.get(i).x - clients.get(before).x)
					+ Math.abs(clients.get(i).y - clients.get(before).y);
			permutation(i, cnt + 1, dist + addDist);
			isSelected[i] = false;
		}
	}

}
