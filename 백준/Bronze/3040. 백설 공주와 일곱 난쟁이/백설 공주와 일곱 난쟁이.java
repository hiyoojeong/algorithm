import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백설공주와 일곱난쟁이
public class Main {

	static int N = 9; // 진짜 난쟁이라고 주장하는 난쟁이의 수
	static int M = 7; // 진짜 난쟁이의 수
	static int[] dwarfs;
	static int[] selectedDwarfs;
	static StringBuffer answer;
	static boolean flag = false; // 진짜 난쟁이를 찾았는지 확인하는 플래그 변수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		answer = new StringBuffer();

		// 입력
		dwarfs = new int[N];
		selectedDwarfs = new int[M];
		for (int i = 0; i < N; i++) {
			dwarfs[i] = Integer.parseInt(br.readLine());
		}

		// N = 9이므로, 조합으로 풀이가 가능하다.
		// 난쟁이 조합을 구한 뒤, 합이 100이 되는지 확인한다.
		combination(0, 0);
		
		// 출력
		System.out.println(answer);
	}

	public static void combination(int cnt, int start) {
		// 이미 일곱 난쟁이를 찾았다면, 더 이상 탐색하지 않는다.
		if (flag) {
			return;
		}

		if (cnt == M) {
			// 해당 조합의 난쟁이의 모자 수 합이 100인지 확인한다.
			int sum = 0;
			for (int i = 0; i < M; i++) {
				sum += selectedDwarfs[i];
			}

			if (sum == 100) {
				for (int i = 0; i < M; i++) {
					answer.append(selectedDwarfs[i]).append("\n");
				}
				flag = true; // 진짜 일곱 난쟁이를 찾았음을 표시한다.
			}

			return;
		}

		for (int i = start; i < N; i++) {
			selectedDwarfs[cnt] = dwarfs[i];
			combination(cnt + 1, i + 1); // '현재 수 다음 수'부터 시작
		}
	}

}
