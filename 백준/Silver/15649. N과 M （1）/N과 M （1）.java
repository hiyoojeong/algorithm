import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// N과 M (1)
public class Main {

	static int N, M;
	static int[] selected;
	static boolean[] isSelected;

	static StringBuffer result;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		result = new StringBuffer();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		selected = new int[M];
		isSelected = new boolean[N + 1];
		
		peremutation(0);
		
		System.out.println(result);
	}

	public static void peremutation(int cnt) {
		if (cnt == M) {
			for (int i = 0; i < M; i++) {
				result.append(selected[i]).append(" ");
			}
			result.append("\n");

			return;
		}

		for (int i = 1; i <= N; i++) {
			if (isSelected[i]) {
				continue;
			}

			selected[cnt] = i;
			isSelected[i] = true;
			peremutation(cnt + 1);
			isSelected[i] = false;
		}
	}

}
