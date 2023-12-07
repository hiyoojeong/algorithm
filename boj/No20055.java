

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 컨베이어 벨트 위의 로봇
public class No20055 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 컨베이어 밸트 가로 크기
		int K = Integer.parseInt(st.nextToken()); // 종료되는 단계

		int[] belt = new int[2 * N];
		boolean[] robot = new boolean[2 * N];
		ArrayList<Integer> robotOrder = new ArrayList<>();

		int start = 0;
		int end = N - 1;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2 * N; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}

		int cnt = 0;
		while (!isMaxZero(belt, K)) {
			cnt++;
//			System.out.println("cnt >> " + cnt);

			// 1. 컨베이어 벨트 회전
			start = ((start - 1) + (2 * N)) % (2 * N);
			end = ((end - 1) + (2 * N)) % (2 * N);
//			System.out.println("컨베이어 벨트 회전");
//			print(belt, robot, start, end);

			// 내리는 위치에 로봇 내리기
			if (robot[end]) {
				robot[end] = false;
				for (int i = 0; i < robotOrder.size(); i++) {
					int pos = robotOrder.get(i);
					if (pos == end) {
						robotOrder.remove(i);
					}
				}
			}
//			System.out.println("내리는 위치에 로봇 내리기");
//			print(belt, robot, start, end);

			// 2. 이동 가능한 로봇 이동시키기
			for (int i = 0; i < robotOrder.size(); i++) {
				int pos = robotOrder.get(i);
				int nextpos = (pos + 1) % (2 * N);
//				System.out.println("pos : " + pos);
//				System.out.println("nextpos : " + nextpos);

				if (belt[nextpos] >= 1 && !robot[nextpos]) {
					belt[nextpos]--;
					robot[pos] = false;
					robot[nextpos] = true;
					robotOrder.set(i, nextpos);
				}
			}
//			System.out.println("이동 가능한 로봇 이동시키기");
//			print(belt, robot, start, end);

			// 3. 올리는 위치에 로봇 올리기
			if (belt[start] >= 1 && !robot[start]) {
				belt[start]--;
				robot[start] = true;
				robotOrder.add(start); // 올라간 로봇 순서 정보 저장
			}
//			System.out.println("올리는 위치에 로봇 올리기");
//			print(belt, robot, start, end);

			// 내리는 위치에 로봇 내리기
			if (robot[end]) {
				robot[end] = false;
				for (int i = 0; i < robotOrder.size(); i++) {
					int pos = robotOrder.get(i);
					if (pos == end) {
						robotOrder.remove(i);
					}
				}
			}
//			System.out.println("내리는 위치에 로봇 내리기");
//			print(belt, robot, start, end);
		}

		System.out.println(cnt);

	}

	public static boolean isMaxZero(int[] belt, int K) {
		int cnt = 0;
		for (int i = 0; i < belt.length; i++) {
			if (belt[i] == 0) {
				cnt++;
			}
		}

		return cnt >= K ? true : false;
	}

	public static void print(int[] belt, boolean[] robot, int start, int end) {

		System.out.println("start > " + start);
		System.out.println("end > " + end);

		int N = belt.length / 2;

		for (int i = 0; i < N; i++) {
			System.out.print(belt[i] + "(" + robot[i] + ")  ");
		}
		System.out.println();
		for (int i = 2 * N - 1; i >= N; i--) {
			System.out.print(belt[i] + "(" + robot[i] + ")  ");
		}
		System.out.println();
		System.out.println();

	}

}
