

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 점프 게임
class Pos {
	int line;
	int time, i;

	public Pos(int line, int time, int i) {
		this.line = line;
		this.time = time;
		this.i = i;
	}

}

public class No15558 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		String[] map = new String[2];
		map[0] = br.readLine(); // 왼쪽 줄
		map[1] = br.readLine(); // 오른쪽 줄
		
		boolean[][] visited = new boolean[2][N]; // 방문했던 지점 체크

		Queue<Pos> queue = new LinkedList<>();
		queue.add(new Pos(0, 0, 0));

		while (!queue.isEmpty()) {
			Pos pos = queue.poll();

			// 한 칸 앞으로 이동한다.
			if (pos.i + 1 >= N) {
				System.out.println(1);
				return;
			}
			if (map[pos.line].charAt(pos.i + 1) == '1' && !visited[pos.line][pos.i + 1]) {
				queue.add(new Pos(pos.line, pos.time + 1, pos.i + 1));
				visited[pos.line][pos.i + 1] = true;
			}

			// 한 칸 뒤로 이동한다.
			if (pos.i != 0 && map[pos.line].charAt(pos.i - 1) == '1' && pos.time < pos.i - 1 && !visited[pos.line][pos.i - 1]) {
				queue.add(new Pos(pos.line, pos.time + 1, pos.i - 1));
				visited[pos.line][pos.i - 1] = true;
			}

			// 반대편 줄로 k칸 앞으로 이동한다.
			if (pos.i + K >= N) {
				System.out.println(1);
				return;
			}
			if (map[(pos.line + 1) % 2].charAt(pos.i + K) == '1' && !visited[(pos.line + 1) % 2][pos.i + K]) {
				queue.add(new Pos((pos.line + 1) % 2, pos.time + 1, pos.i + K));
				visited[(pos.line + 1) % 2][pos.i + K] = true;
			}

		}

		System.out.println(0);

	}

}
