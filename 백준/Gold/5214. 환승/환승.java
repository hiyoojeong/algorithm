import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Pos {
		int station, cnt;

		public Pos(int station, int cnt) {
			this.station = station;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 역 개수
		int K = Integer.parseInt(st.nextToken()); // 하이퍼튜브가 연결하는 역 개수
		int M = Integer.parseInt(st.nextToken()); // 하이퍼튜브 개수

		// 역, 하이퍼튜브 정보 저장
		Map<Integer, List<Integer>> stations = new HashMap<>();
		int[][] hypertube = new int[M][K];

		for (int i = 1; i <= N; i++) {
			stations.put(i, new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < K; j++) {
				int station = Integer.parseInt(st.nextToken());
				hypertube[i][j] = station;
				stations.get(station).add(i);
			}
		}

		// dfs
		Queue<Pos> queue = new LinkedList<>();
		queue.add(new Pos(1, 1));

		boolean[] visitedStation = new boolean[N + 1];
		boolean[] visitedHyper = new boolean[M];
		visitedStation[1] = true;

		int answer = -1;
		while (!queue.isEmpty()) {
			Pos pos = queue.poll();

			if (pos.station == N) {
				answer = pos.cnt;
				break;
			}

			for (int connectHypertube : stations.get(pos.station)) {
				for (int connectStation : hypertube[connectHypertube]) {
					if (!visitedHyper[connectHypertube] && !visitedStation[connectStation]) {
						visitedStation[connectStation] = true;
						queue.add(new Pos(connectStation, pos.cnt + 1));
					}
				}
				visitedHyper[connectHypertube] = true;
			}
		}

		System.out.println(answer);
	}
}
