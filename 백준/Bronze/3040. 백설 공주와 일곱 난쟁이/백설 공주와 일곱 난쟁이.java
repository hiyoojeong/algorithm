import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백설공주와 일곱난쟁이
public class Main {

	static int N = 9; // 진짜 난쟁이라고 주장하는 난쟁이의 수
	static int M = 7; // 진짜 난쟁이의 수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 입력
		int[] dwarfs = new int[N];
		int totalSum = 0;
		for (int i = 0; i < N; i++) {
			dwarfs[i] = Integer.parseInt(br.readLine());
			totalSum += dwarfs[i];
		}

		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				// 난쟁이 두 마리를 뺐을 때 합이 100이 되었다면, 해당 난쟁이를 뺀 나머지 난쟁이들이 진짜 난쟁이이다.
				if (totalSum - dwarfs[i] - dwarfs[j] == 100) {
					for (int k = 0; k < N; k++) {
						if (k != i && k != j) {
							System.out.println(dwarfs[k]);
						}
					}
					
					return;
				}
			}
		}
	}

}
