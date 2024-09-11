import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 연료 채우기
class Main {

	static class Station {
		int idx, fuel;

		public Station(int idx, int fuel) {
			super();
			this.idx = idx;
			this.fuel = fuel;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine()); // 주유소의 개수
		Station[] station = new Station[N + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken()); // 주유소 위치
			int fuel = Integer.parseInt(st.nextToken()); // 채울 수 있는 연료의 양
			station[i] = new Station(idx, fuel);
		}

		st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken()); // 마을까지의 거리
		int P = Integer.parseInt(st.nextToken()); // 트럭에 원래 있던 원료의 양

		station[N] = new Station(L, 0);
		Arrays.sort(station, (o1, o2) -> o1.idx - o2.idx); // 위치 순으로 정렬

		int cnt = 0, preIdx = 0;
		Queue<Integer> fuels = new PriorityQueue<>(Collections.reverseOrder()); // 충전하지 않고 지나온 모든 주유소에서 충전할 수 있는 연료양
		for (int i = 0; i <= N; i++) {
			P -= station[i].idx - preIdx;
			preIdx = station[i].idx;

			if (P < 0) { // 도달할 수 없는 주유소
				while (P < 0 && !fuels.isEmpty()) { // 이전에 방문했던 주유소 중에서 최대 연료양부터 채우기
					P += fuels.poll();
					cnt++;
				}

				if (P < 0) { // 마을에 도착하지 못하는 경우(지나온 모든 주유소에서 충전해도 차가 멈춤)
					System.out.println(-1);
					return;
				}
			}

			fuels.add(station[i].fuel); // 현재 주유소의 연료정보 저장
		}

		System.out.println(cnt);
	}
}