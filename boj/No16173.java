

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 점프왕 쩰리
public class No16173 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		boolean[][] visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { 0, 0 });

		while (!queue.isEmpty()) {
			int[] jelly = queue.poll();
			int r = jelly[0];
			int c = jelly[1];

			if (map[r][c] == -1) {
				System.out.println("HaruHaru");
				return;
			}

			int bottom = r + map[r][c];
			int right = c + map[r][c];

			if (bottom < N && !visited[bottom][c]) {
				queue.add(new int[] { bottom, c });
				visited[bottom][c] = true;
			}

			if (right < N && !visited[r][right]) {
				queue.add(new int[] { r, right });
				visited[r][right] = true;
			}

		}

		System.out.println("Hing");

	}

}
