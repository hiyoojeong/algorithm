

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Cart {
	int x, y, cnt;

	public Cart(int x, int y, int cnt) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}
}

public class No17391 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(getMinMove(map, N, M));
	}

	public static int getMinMove(int[][] map, int N, int M) {
		// 오른쪽, 아래쪽 이동하기
		int[][] move = { { 0, 1 }, { 1, 0 } };

		boolean[][] visit = new boolean[N][M];

		Queue<Cart> queue = new LinkedList<>();
		queue.add(new Cart(0, 0, 0));

		while (!queue.isEmpty()) {
			Cart cart = queue.poll();

			if (cart.x == N - 1 && cart.y == M - 1) {
				return cart.cnt;
			}

			for (int i = 0; i < 2; i++) {
				for (int j = 1; j <= map[cart.x][cart.y]; j++) {
					int nextx = cart.x + move[i][0] * j;
					int nexty = cart.y + move[i][1] * j;
					if (nextx >= 0 && nexty >= 0 && nextx < N && nexty < M) {
						if (!visit[nextx][nexty]) {
							visit[nextx][nexty] = true;
							queue.add(new Cart(nextx, nexty, cart.cnt + 1));
						}
					}
				}
			}
		}

		return -1;
	}

}
