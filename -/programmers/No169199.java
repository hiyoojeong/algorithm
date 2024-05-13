import java.util.ArrayList;

// 리코쳇 로봇
class Node {
	int x;
	int y;
	int cnt;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
		this.cnt = 0;
	}

	public Node(int x, int y, int cnt) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}
}

public class No169199 {

	static int N, M;

	public static void main(String[] args) {
		System.out.println(solution(new String[] { "...D..R", ".D.G...", "....D.D", "D....D.", "..D...." }));
		System.out.println(solution(new String[] { ".D.R", "....", ".G..", "...D" }));
	}

	public static int solution(String[] board) {
		// 게임판 크기
		N = board.length;
		M = board[0].length();
		
		// R: 로봇의 처음 위치
		Node R = null;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i].charAt(j) == 'R')
					R = new Node(i, j);
			}
		}
		
		// 이동 방향은 상, 하, 좌, 우이다.
		int[] mx = { -1, 1, 0, 0 };
		int[] my = { 0, 0, -1, 1 };
		
		// 이미 방문한 좌표는 다시 갈 수 없다.
		boolean[][] visited = new boolean[N][M];

		// BFS
		ArrayList<Node> queue = new ArrayList<>();
		queue.add(R);
		while (queue.size() > 0) {
			// queue에서 노드를 가져온다.
			Node node = queue.get(0);
			queue.remove(0);

			// 가져온 노드가 목표 지점이라면, 이것이 최소 움직임 cnt를 가지고 있다.
			if (board[node.x].charAt(node.y) == 'G') {
				return node.cnt;
			}

			// 상하좌우로 이동한다.
			for (int i = 0; i < 4; i++) {
				// 이동할 수 있는 노드를 확인한다.
				Node next = new Node(node.x, node.y, node.cnt + 1);
				next = nextXY(board, mx[i], my[i], next);

				// 이미 방문한 좌표는 다시 갈 수 없다.
				if (visited[next.x][next.y])
					continue;

				// queue에 이동할 수 있는 노드를 저장한다.
				queue.add(next);
				// 방문했다고 표시한다.
				visited[next.x][next.y] = true;
			}
		}

		return -1;
	}

	// 장애물이나 맨 끝에 부딪힐 때까지 이동한 좌표를 반환
	public static Node nextXY(String[] board, int mx, int my, Node next) {
		int x, y;
		while (true) {
			x = next.x + mx;
			y = next.y + my;

			if (x >= N || y >= M || x < 0 || y < 0)
				return next;

			if (board[x].charAt(y) == 'D')
				return next;

			next.x = x;
			next.y = y;
		}
	}

}
