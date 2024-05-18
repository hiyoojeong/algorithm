import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 수들의 합
public class Main {

	static long S;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		S = Long.parseLong(br.readLine());

		long answer = 1;
		// 1을 제외하고 결과를 구한다.
		if (S != 1) {
			answer = binarySearch();
		}

		System.out.println(answer);

	}

	public static long binarySearch() {
		long left = 1;
		long right = S;

		while (left < right) {
			long n = (left + right) / 2;

			// 1부터 n까지의 합이 S를 넘는 최소값을 구한다.
			if (n + 1 > (2 * S) / n) {
				right = n;
			} else {
				left = n + 1;
			}
		}

		// 결과의 -1만 하면 된다.
		return right - 1;
	}

}
