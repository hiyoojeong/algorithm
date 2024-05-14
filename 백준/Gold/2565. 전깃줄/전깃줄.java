import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

// 전깃줄
public class Main {

	static class Line {
		int left, right;

		public Line(int left, int right) {
			this.left = left;
			this.right = right;
		}

	}

	static List<Integer> lis;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 데이터를 저장한다.
		List<Line> lines = new ArrayList<>();

		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());

			lines.add(new Line(left, right));
		}

		// 왼쪽 전봇대를 기준으로 정렬한다.
		Collections.sort(lines, new Comparator<Line>() {
			public int compare(Line l1, Line l2) {
				return l1.left - l2.left;
			}

		});

		// 오른쪽 전봇대를 기준으로 '가장 긴 증가하는 수열'의 길이를 찾는다. - 이진탐색으로 LIS를 만드는 방법 활용
		lis = new ArrayList<>();
		lis.add(lines.get(0).right);

		for (int i = 1; i < n; i++) {
			int value = lines.get(i).right;
			int index = binarySearch(value);

			if (lis.get(index) < value) {
				lis.add(value);
			} else {
				lis.set(index, value);
			}
		}
		int maxLength = lis.size();

		// 없애야 하는 전깃줄의 개수 = 전체 전깃줄의 개수 - 있어야 하는 전깃줄의 개수(가장 긴 증가하는 수열의 개수)
		int answer = n - maxLength;

		System.out.println(answer);

	}

	public static int binarySearch(int value) {
		int left = 0;
		int right = lis.size() - 1;

		// Upperbound(상계): 찾으려는 값보다 큰 값(초과)이 나타나는 시점을 찾는다.
		while (left < right) {
			int mid = (left + right) / 2;

			if (lis.get(mid) < value) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}

		return right;
	}

}
