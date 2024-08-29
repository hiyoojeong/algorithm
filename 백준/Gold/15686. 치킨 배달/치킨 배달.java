
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 치킨 배달
class Main {

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static int N, M;
	static List<Pos> chicken, house;

	static int[] selected;
	static int minChickenDist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 도시의 크기
		M = Integer.parseInt(st.nextToken()); // 치킨집의 수

		chicken = new ArrayList<>();
		house = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int type = Integer.parseInt(st.nextToken());
				if (type == 1) {
					house.add(new Pos(i, j));
				} else if (type == 2) {
					chicken.add(new Pos(i, j));
				}
			}
		}

		selected = new int[M];
		minChickenDist = Integer.MAX_VALUE;
		combi(0, 0);

		System.out.println(minChickenDist);

	}

	public static void combi(int cnt, int start) {
		if (cnt == M) {
			int chickenDist = 0; // 현재 조합에서 치킨 거리

			for (Pos h : house) {
				int d = Integer.MAX_VALUE; // 해당 집에서 가장 가까운 치킨집과의 거리

				for (int i = 0; i < M; i++) {
					Pos c = chicken.get(selected[i]);
					d = Math.min(d, Math.abs(h.x - c.x) + Math.abs(h.y - c.y));
				}

				chickenDist += d;
			}

			minChickenDist = Math.min(chickenDist, minChickenDist);
			return;
		}

		for (int i = start; i < chicken.size(); i++) {
			selected[cnt] = i;
			combi(cnt + 1, i + 1);
		}
	}

}