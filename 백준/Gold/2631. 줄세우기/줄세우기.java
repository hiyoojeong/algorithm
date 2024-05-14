import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// 줄 세우기
public class Main {

	static List<Integer> lis;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 데이터를 저장한다.
		int n = Integer.parseInt(br.readLine());

		int[] lines = new int[n];
		for (int i = 0; i < n; i++) {
			lines[i] = Integer.parseInt(br.readLine());
		}

		// LIS를 찾는다.
		lis = new ArrayList<>();
		lis.add(lines[0]);

		for (int i = 1; i < n; i++) {
			int index = upperbound(lines[i]);

			if (lis.get(index) < lines[i]) {
				lis.add(lines[i]);
			} else {
				lis.set(index, lines[i]);
			}
		}
		
		// 옮겨야 하는 아이 수 = 전체 아이 수 - LIS 길이
		int move = n - lis.size();
		
		System.out.println(move);

	}

	public static int upperbound(int key) {
		int left = 0;
		int right = lis.size() - 1;

		while (left < right) {
			int mid = (left + right) / 2;

			if (lis.get(mid) <= key) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}

		return right;
	}
}
