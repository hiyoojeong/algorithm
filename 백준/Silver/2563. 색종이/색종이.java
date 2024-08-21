import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 색종이
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int cnt = 0; // 검은 영역의 넓이

		int N = Integer.parseInt(br.readLine()); // 색종이 개수
		boolean[][] arr = new boolean[100][100]; // 도화지

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			for (int j = x; j < x + 10; j++) {
				for (int k = y; k < y + 10; k++) {
					if (!arr[j][k]) {
						arr[j][k] = true;
						cnt++;
					}
				}
			}
		}
		System.out.println(cnt);

	}

}
