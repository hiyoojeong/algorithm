import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	static List<Integer> lis;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer sb = new StringBuffer();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 수열의 길이
			int[] arr = new int[N]; // 수열의 원소

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			int answer = solution(N, arr);

			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}

		System.out.println(sb);
	}

	public static int solution(int N, int[] arr) {

		lis = new ArrayList<>();
		lis.add(arr[0]);

	for (int i = 1; i < N; i++) {
			// 현재 값보다 큰 들의 상계 위치를 찾는다.
			int value = arr[i];
			int pos = upperbound(value);

			// 해당 위치에 있는 값이, 현재값보다 크다면 교체한다.
			if (value < lis.get(pos)) {
				lis.set(pos, value);
			}
			// 해당 위치에 있는 값이, 현재값과 같거나 작다면 단순히 추가한다.
			else {
				lis.add(value);
			}
		}

		return lis.size();
	}

	public static int upperbound(int value) {
		int left = 0;
		int right = lis.size() - 1;

		while (left < right) {
			int mid = (left + right) / 2;

			if (lis.get(mid) <= value) { // 동일한 값도 넘겨버림
				left = mid + 1;
			} else {
				right = mid; // 큰 값만 선택
			}
		}

		return right;
	}

}
