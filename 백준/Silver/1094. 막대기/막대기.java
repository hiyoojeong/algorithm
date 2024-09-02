
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 막대기
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int X = Integer.parseInt(br.readLine());
		int cnt = 0;
		for (int i = 0; i <= 6; i++) {
			if ((X & (1 << i)) != 0) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}
