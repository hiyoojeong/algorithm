

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No16510 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// 처리해야할 일들의 시간 정보
		int[] list = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			list[i + 1] = list[i] + Integer.parseInt(st.nextToken());
		}

		// 주어진 시간에 따라서, 처리할 수 있는 일의 개수
		for (int i = 0; i < M; i++) {
			int time = Integer.parseInt(br.readLine());

			int left = 1;
			int right = N;

			while (left <= right) {
				int middle = (left + right) / 2;

				if (list[middle] > time) {
					right = middle - 1;
				} else {
					left = middle + 1;
				}
			}

			System.out.println(right);

		}
	}
}
