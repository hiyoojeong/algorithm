package 부분합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 구간 합 구하기 4
public class No11659 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] numbers = new int[N + 1];
		numbers[0] = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int number = Integer.parseInt(st.nextToken());
			numbers[i] = numbers[i - 1] + number;
		}

		StringBuffer answer = new StringBuffer();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cnt = numbers[end] - numbers[start - 1];
			answer.append(cnt + "\n");
		}

		System.out.println(answer);

	}

}
