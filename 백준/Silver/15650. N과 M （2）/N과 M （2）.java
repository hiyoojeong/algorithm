import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] numbers;
	static boolean[] isSelected;
	static StringBuffer answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		answer = new StringBuffer();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 자연수
		M = Integer.parseInt(st.nextToken()); // 수열의 길이

		numbers = new int[M];
		isSelected = new boolean[N + 1];

		combination(0, 1);
		System.out.println(answer);
	}

	public static void combination(int cnt, int start) {
		if (cnt == M) {
			for (int i = 0; i < M; i++) {
				answer.append(numbers[i]).append(" ");
			}
			answer.append("\n");
			return;
		}

		for (int i = start; i <= N; i++) {
			numbers[cnt] = i;
			combination(cnt + 1, i + 1); // '현재 수 다음 수'부터 시작 = 중복 제거
		}
	}
}
