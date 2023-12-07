import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 경비원
public class No2564 {

	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); // 가로 길이
		N = Integer.parseInt(st.nextToken()); // 세로 길이
		int[][] map = new int[N + 1][M + 1]; // 장소 위치정보

		// 장소 위치정보 저장
		int storeNum = Integer.parseInt(br.readLine());
		for (int i = 0; i < storeNum; i++) {
			st = new StringTokenizer(br.readLine());
			int pos = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());

			int[] position = getPosition(pos, distance); // 주어진 조건을 2차원배열 좌표로 변환하여 저장
			map[position[0]][position[1]] = 1; // 장소는 1
		}

		// 시작 위치정보 저장
		st = new StringTokenizer(br.readLine());
		int spos = Integer.parseInt(st.nextToken());
		int sdistance = Integer.parseInt(st.nextToken());

		int[] sposition = getPosition(spos, sdistance); // 주어진 조건을 2차원배열 좌표로 변환하여 저장
		map[sposition[0]][sposition[1]] = -1; // 시작은 -1

		// 2차원배열의 가장자리를 시계방향으로 이동하며 cnt 세기
		int totalCnt = 0, cnt = 0;
		int r = sposition[0], c = sposition[1];
		while (true) {
			
			// 꼭짓점의 이동방향
			if (r == 0 && c == 0) {
				c++;
			} else if (r == N && c == 0) {
				r--;
			} else if (r == 0 && c == M) {
				r++;
			} else if (r == N && c == M) {
				c--;
			}

			// 네변의 이동방향
			else if (r == 0) {
				c++;
			} else if (c == 0) {
				r--;
			} else if (r == N) {
				c--;
			} else if (c == M) {
				r++;
			}

			cnt++;
			
//			System.out.println("r: " + r);
//			System.out.println("c: " + c);
//			System.out.println("cnt: " + cnt);

			if (map[r][c] == 1) {
				// 반시계방향이 더 가까운 경우
				if (cnt > (N + M)) {
					totalCnt += 2 * (N + M) - cnt;
				} 
				// 시계방향이 더 가까운 경우
				else {
					totalCnt += cnt;
				}
			}
			
			// 처음 지점으로 도착한 경우
			if (map[r][c] == -1) {
				break;
			}
		}

		System.out.println(totalCnt);

	}

	// 2차원배열 좌표로 변환
	public static int[] getPosition(int pos, int distance) {
		int r = 0, c = 0;
		if (pos == 1) {
			r = 0;
			c = distance;

		} else if (pos == 2) {
			r = N;
			c = distance;

		} else if (pos == 3) {
			r = distance;
			c = 0;

		} else if (pos == 4) {
			r = distance;
			c = M;
		}

		return new int[] { r, c };
	}

}
