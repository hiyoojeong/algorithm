import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] count = new int[10000];
		for (int i = 0; i < N; i++) {
			count[Integer.parseInt(br.readLine()) - 1]++;
		}
		br.close();

		// 풀이
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 10000; i++) {
			for (int j = 0; j < count[i]; j++) {
				sb.append(i+1).append("\n");
			}
		}
		
		// 출력
		System.out.println(sb.toString());
	}
}