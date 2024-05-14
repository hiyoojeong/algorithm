import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

		// 오른쪽 전봇대를 기준으로 '가장 긴 증가하는 수열'의 길이를 찾는다.
		int[] length = new int[n];
		int maxLength = 1;
		for (int k = 0; k < n; k++) {
			length[k] = 1;
			for (int i = 0; i < k; i++) {
				if (lines.get(i).right < lines.get(k).right) {
					length[k] = Math.max(length[k], length[i] + 1);
				}
			}
			maxLength = Math.max(maxLength, length[k]);
		}

		// 없애야 하는 전깃줄의 개수 = 전체 전깃줄의 개수 - 있어야 하는 전깃줄의 개수(가장 긴 증가하는 수열의 개수)
		int answer = n - maxLength;

		System.out.println(answer);

	}

}
