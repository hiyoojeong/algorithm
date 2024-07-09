import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1대1 가위바위보
public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

		String winner = null;

		if (A == 1) {
			if (B == 2) {
				winner = "B";
			} else if (B == 3) {
				winner = "A";
			}
		} else if (A == 2) {
			if (B == 1) {
				winner = "A";
			} else if (B == 3) {
				winner = "B";
			}
		} else if (A == 3) {
			if (B == 1) {
				winner = "B";
			} else if (B == 2) {
				winner = "A";
			}
		}

		System.out.println(winner);

	}

}