import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 고층 건물
public class Main {

	static class Building {
		int x, y;

		public Building(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		// 각 빌딩의 x좌표, y좌표를 저장한다.
		List<Building> buildings = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int x = 0; x < N; x++) {
			int y = Integer.parseInt(st.nextToken());
			buildings.add(new Building(x, y));
		}

		// 현재 빌딩에서 타겟 빌딩을 볼 수 있는지 확인하여, cnt를 증가시킨다.
		// 타겟 빌딩을 가장 많이 볼 수 있는 경우를 maxCnt에 업데이트한다.
		int maxCnt = 0;
		for (int current = 0; current < N; current++) {
			Building currentBuilding = buildings.get(current);

			int cnt = 0;

			// 왼쪽 확인
			for (int target = current - 1; target >= 0; target--) {
				Building targetBuilding = buildings.get(target);

				boolean isVisible = true;
				for (int i = current - 1; i > target; i--) {
					Building building = buildings.get(i);

					// 현재 건물과 타겟 건물 사이의 해당 위치에서의 기대하는 건물의 최대 높이
					double expected_y = expected_y(currentBuilding, targetBuilding, building.x);

					// 기대하는 건물의 최대 높이보다 높은 건물이 있으므로, 타겟 건물을 볼 수 없다.
					if (expected_y <= building.y) {
						isVisible = false;
						break;
					}
				}

				if (isVisible) {
					cnt++;
				}
			}

			// 오른쪽 확인
			for (int target = current + 1; target < N; target++) {
				Building targetBuilding = buildings.get(target);

				boolean isVisible = true;
				for (int i = current + 1; i < target; i++) {
					Building building = buildings.get(i);

					// 현재 건물과 타겟 건물 사이의 해당 위치에서의 기대하는 건물의 최대 높이
					double expected_y = expected_y(currentBuilding, targetBuilding, building.x);

					// 기대하는 건물의 최대 높이보다 높은 건물이 있으므로, 타겟 건물을 볼 수 없다.
					if (expected_y <= building.y) {
						isVisible = false;
						break;
					}
				}

				if (isVisible) {
					cnt++;
				}
			}

			maxCnt = Math.max(maxCnt, cnt);

		}

		System.out.println(maxCnt);

	}

	public static double expected_y(Building currentBuilding, Building targetBuilding, int x) {
		return ((double) (targetBuilding.y - currentBuilding.y) / (double) (targetBuilding.x - currentBuilding.x))
				* (double) (x - currentBuilding.x) + (double) currentBuilding.y;
	}

}
