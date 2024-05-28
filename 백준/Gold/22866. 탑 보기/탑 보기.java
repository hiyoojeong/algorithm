import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 탑 보기
public class Main {

	static class Building {
		int index, height;

		public Building(int index, int height) {
			this.index = index;
			this.height = height;
		}

	}

	static class Show {
		int cnt, close;

		public Show(int cnt, int close) {
			this.cnt = cnt;
			this.close = close;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] buildings = new int[N + 1];

		// 건물의 위치와 높이를 저장한다.
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			buildings[i] = Integer.parseInt(st.nextToken());
		}

		Show[] show = new Show[N + 1];

		// '왼->오'로 이동하며, 현재 건물에서 왼쪽에 보이는 건물 정보가 Stack에는 저장한다.
		Stack<Building> left = new Stack<>();
		for (int i = 1; i <= N; i++) {
			int currentHeight = buildings[i];

			// 현재 건물보다 높은 건물빼고 Stack에서 삭제한다.
			while (left.size() > 0) {
				int leftHeight = left.peek().height;
				if (leftHeight <= currentHeight) {
					left.pop();
				} else {
					break;
				}
			}

			// 현재 건물에서 왼쪽에 보이는 건물의 개수와 볼 수 있는 건물 중 가장 가까운 건물의 위치를 저장한다.
			int cnt = left.size();
			int close = left.size() > 0 ? left.peek().index : 0;
			show[i] = new Show(cnt, close);

			// 현재 건물 정보를 Stack에 저장한다.
			left.push(new Building(i, currentHeight));
		}

		// '오->왼'으로 이동하며, 현재 건물에서 오른쪽에 보이는 건물 정보가 Stack에는 저장한다.
		Stack<Building> right = new Stack<>();
		for (int i = N; i >= 1; i--) {
			int currentHeight = buildings[i];

			// 현재 건물보다 높은 건물빼고 Stack에서 삭제한다.
			while (right.size() > 0) {
				int rightHeight = right.peek().height;
				if (rightHeight <= currentHeight) {
					right.pop();
				} else {
					break;
				}
			}

			// 현재 건물에서 왼쪽에 보이는 건물의 개수와 볼 수 있는 건물 중 가장 가까운 건물의 위치를 저장한다.
			int cnt = right.size();
			int close = right.size() > 0 ? right.peek().index : 0;
			show[i].cnt = show[i].cnt + cnt;
			if (show[i].close == 0 && cnt != 0) { // 왼쪽에 보이는 건물이 없는 경우, 오른쪽으로 가장 가까운 건물 번호
				show[i].close = close;
			} else if (show[i].close != 0 && cnt != 0) { // 양쪽에 보이는 건물이 있다면, 더 가까운 건물 번호
				int leftDistance = i - show[i].close;
				int rightDistance = close - i;
				if (leftDistance > rightDistance) {
					show[i].close = close;
				}
			}

			// 현재 건물 정보를 Stack에 저장한다.
			right.push(new Building(i, currentHeight));
		}

		StringBuffer answer = new StringBuffer();
		for (int i = 1; i <= N; i++) {
			int cnt = show[i].cnt;
			int close = show[i].close;

			answer.append(cnt);
			if (close > 0) {
				answer.append(" ").append(close);
			}
			answer.append("\n");
		}

		System.out.println(answer);
	}

}
