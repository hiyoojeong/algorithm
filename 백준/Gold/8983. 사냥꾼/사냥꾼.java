import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 사냥꾼
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken()); // 사대의 수
		int N = Integer.parseInt(st.nextToken()); // 동물의 수
		int L = Integer.parseInt(st.nextToken()); // 사정거리

		// 사대 위치 정보
		int[] parts = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			parts[i] = Integer.parseInt(st.nextToken());
		}

		// 사대 위치 순서대로 오름차순 정렬
		Arrays.sort(parts);

		// 동물 위치 정보
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			int left = 0, right = M - 1;
			while (left <= right) {
				int mid = (left + right) / 2;
				double dist = Math.sqrt(Math.pow(x - parts[mid], 2) + Math.pow(y - 0, 2));

				// 동물과 사대 사이 거리가 사정거리 내라면, 동물을 잡을 수 있다.
				if (dist <= L) {
					cnt++;
					break;
				}
				// 동물과 사대 사이 거리가 사정거리 밖에라면, 동물을 잡을 수 없다.
				// 사대 거리를 더 좁혀야 한다.
				else {
					// 동물이 사대보다 왼쪽에 있다면, 현대 사대의 왼쪽을 확인해야 한다.
					if (x < parts[mid]) {
						right = mid - 1;
					}
					// 동물이 사대보다 오른쪽에 있다면, 현재 사대의 오른쪽을 확인해야 한다.
					else if (parts[mid] < x) {
						left = mid + 1;
					}
					// 동물이 사대랑 같은 좌표인데도 사정거리 밖이라면, 못잡는 동물이다.
					else if (x == parts[mid]) {
						break;
					}
				}
			}
		}

		System.out.println(cnt);

	}

}
